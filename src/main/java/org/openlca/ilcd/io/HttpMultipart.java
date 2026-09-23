package org.openlca.ilcd.io;

import java.io.File;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/// Builds the body of a `multipart/form-data` request (RFC 7578) as it is
/// required by the `sources/withBinaries` end-point of a soda4LCA server.
///
/// The layout of the parts (order of the part headers, part content types, and
/// not adding a `filename` parameter) is identical to what the previous Jersey
/// based implementation sent. Note that all parts are built from publishers
/// with a known content length which means that the resulting request has an
/// exact `Content-Length` and is not sent in chunks.
final class HttpMultipart {

	private static final String CRLF = "\r\n";

	private record Part(
		String name, String contentType, HttpRequest.BodyPublisher body, String probe) {
	}

	/**
	 * The request body and the value of the {@code Content-Type} header.
	 */
	record Payload(HttpRequest.BodyPublisher publisher, String contentType) {
	}

	private final List<Part> parts = new ArrayList<>();
	private String boundary;

	/**
	 * Adds a text field.
	 */
	HttpMultipart addText(String name, String value) {
		var bytes = value.getBytes(StandardCharsets.UTF_8);
		return addPart(name, "text/plain", bytes, value);
	}

	/**
	 * Adds a part with the given binary content.
	 */
	HttpMultipart addPart(String name, String contentType, byte[] data) {
		return addPart(name, contentType, data,
			new String(data, StandardCharsets.ISO_8859_1));
	}

	private HttpMultipart addPart(
		String name, String contentType, byte[] data, String probe) {
		parts.add(new Part(
			name, contentType, HttpRequest.BodyPublishers.ofByteArray(data), probe));
		return this;
	}

	/**
	 * Adds a part with the content of the given file.
	 */
	HttpMultipart addFile(String name, String contentType, File file) {
		if (file == null || !file.isFile())
			throw new IllegalArgumentException("not a file: " + file);
		HttpRequest.BodyPublisher body;
		try {
			body = HttpRequest.BodyPublishers.ofFile(file.toPath());
		} catch (Exception e) {
			throw new IllegalArgumentException("cannot read file: " + file, e);
		}
		// the file content is not checked for the boundary. As the boundary
		// contains a random UUID this collision is practically impossible.
		parts.add(new Part(name, contentType, body, null));
		return this;
	}

	/**
	 * Creates the request body and the corresponding {@code Content-Type}
	 * header value.
	 */
	Payload build() {
		boundary = newBoundary();
		// make sure the boundary is not part of the parts that we can check
		// without reading the content of the referenced files
		for (int i = 0; i < 10 && containsBoundary(); i++) {
			boundary = newBoundary();
		}

		var publishers = new ArrayList<HttpRequest.BodyPublisher>();
		for (var part : parts) {
			publishers.add(headOf(part));
			publishers.add(part.body());
			publishers.add(HttpRequest.BodyPublishers.ofString(CRLF));
		}
		publishers.add(HttpRequest.BodyPublishers.ofString("--" + boundary + "--" + CRLF));
		var publisher = HttpRequest.BodyPublishers.concat(
			publishers.toArray(HttpRequest.BodyPublisher[]::new));
		return new Payload(publisher, "multipart/form-data;boundary=" + boundary);
	}

	private HttpRequest.BodyPublisher headOf(Part part) {
		String head = "--" + boundary + CRLF +
			"Content-Type: " + part.contentType() + CRLF +
			"Content-Disposition: form-data; name=\"" +
			nameOf(part.name()) + '"' + CRLF +
			CRLF;
		return HttpRequest.BodyPublishers.ofString(head, StandardCharsets.UTF_8);
	}

	private boolean containsBoundary() {
		for (var part : parts) {
			var probe = part.probe();
			if (probe != null && probe.contains(boundary))
				return true;
		}
		return false;
	}

	private static String newBoundary() {
		return "Boundary_" + UUID.randomUUID();
	}

	private static String nameOf(String name) {
		if (name == null)
			return "part";
		var escaped = new StringBuilder(name.length());
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (c == '"') {
				escaped.append("%22");
			} else if (c == '\r' || c == '\n') {
				// do not allow header injection via file names
				escaped.append('_');
			} else {
				escaped.append(c);
			}
		}
		return escaped.toString();
	}
}
