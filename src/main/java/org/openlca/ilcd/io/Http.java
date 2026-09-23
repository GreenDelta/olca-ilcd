package org.openlca.ilcd.io;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/// Utility methods for our soda4LCA HTTP client.
class Http {

	private static final char[] HEX = "0123456789ABCDEF".toCharArray();

	static String trimTrailingSlash(String url) {
		var u = url == null
			? ""
			: url.trim();
		while (u.endsWith("/")) {
			u = u.substring(0, u.length() - 1);
		}
		return u;
	}

	/**
	 * Encodes a single URL path segment as defined in RFC 3986. Note that
	 * {@link URLEncoder} must not be used here as it implements the rules of
	 * {@code application/x-www-form-urlencoded} (a space becomes a {@code +}, a
	 * {@code ~} becomes {@code %7E}).
	 */
	static String encodePathSegment(String segment) {
		var bytes = segment.getBytes(StandardCharsets.UTF_8);
		var encoded = new StringBuilder(bytes.length);
		for (var b : bytes) {
			int c = b & 0xff;
			if (isUnreserved(c)) {
				encoded.append((char) c);
			} else {
				encoded.append('%')
					.append(HEX[(c >> 4) & 0xf])
					.append(HEX[c & 0xf]);
			}
		}
		return encoded.toString();
	}

	private static boolean isUnreserved(int c) {
		return (c >= 'a' && c <= 'z')
			|| (c >= 'A' && c <= 'Z')
			|| (c >= '0' && c <= '9')
			|| c == '-' || c == '.' || c == '_' || c == '~';
	}

	static String encodeQuery(String value) {
		return URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8);
	}

}
