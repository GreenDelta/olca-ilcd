package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.net.http.HttpRequest;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class HttpMultipartTest {

	private static final String CRLF = "\r\n";

	@Test
	public void testBody() throws Exception {
		var file = Files.createTempFile("multipart-test", ".txt").toFile();
		Files.writeString(file.toPath(), "hello file content");

		var payload = new HttpMultipart()
			.addText("stock", "test-stock")
			.addPart("file", "multipart/form-data", "<source/>".getBytes(StandardCharsets.UTF_8))
			.addFile("my file.txt", "multipart/form-data", file)
			.build();

		var boundary = boundaryOf(payload.contentType());
		var expected = "--" + boundary + CRLF +
			"Content-Type: text/plain" + CRLF +
			"Content-Disposition: form-data; name=\"stock\"" + CRLF +
			CRLF +
			"test-stock" + CRLF +
			"--" + boundary + CRLF +
			"Content-Type: multipart/form-data" + CRLF +
			"Content-Disposition: form-data; name=\"file\"" + CRLF +
			CRLF +
			"<source/>" + CRLF +
			"--" + boundary + CRLF +
			"Content-Type: multipart/form-data" + CRLF +
			"Content-Disposition: form-data; name=\"my file.txt\"" + CRLF +
			CRLF +
			"hello file content" + CRLF +
			"--" + boundary + "--" + CRLF;

		var body = read(payload.publisher());
		assertEquals(expected, new String(body, StandardCharsets.UTF_8));
	}

	@Test
	public void testContentLengthIsExact() throws Exception {
		var file = Files.createTempFile("multipart-test", ".txt").toFile();
		Files.writeString(file.toPath(), "hello file content");

		var payload = new HttpMultipart()
			.addText("stock", "test-stock")
			.addPart("file", "multipart/form-data", "<source/>".getBytes(StandardCharsets.UTF_8))
			.addFile("my file.txt", "multipart/form-data", file)
			.build();

		var body = read(payload.publisher());
		// a known content length means that the request is not sent in chunks
		assertEquals(body.length, payload.publisher().contentLength());
		assertEquals("multipart/form-data;boundary="
			+ boundaryOf(payload.contentType()), payload.contentType());
	}

	@Test
	public void testMissingFile() {
		var missing = new java.io.File("no-such-file.txt");
		assertThrows(IllegalArgumentException.class,
			() -> new HttpMultipart().addFile("f", "text/plain", missing));
	}

	@Test
	public void testNameIsSanitized() throws Exception {
		var payload = new HttpMultipart()
			.addPart("a\"b\r\nc", "text/plain", new byte[]{1})
			.build();
		var body = new String(read(payload.publisher()), StandardCharsets.ISO_8859_1);
		assertTrue(body.contains("name=\"a%22b__c\""));
	}

	private static String boundaryOf(String contentType) {
		var idx = contentType.indexOf("boundary=");
		if (idx < 0) {
			fail("no boundary in " + contentType);
		}
		return contentType.substring(idx + "boundary=".length());
	}

	private static byte[] read(HttpRequest.BodyPublisher publisher) throws Exception {
		var out = new ByteArrayOutputStream();
		var error = new AtomicReference<Throwable>();
		var done = new CountDownLatch(1);
		publisher.subscribe(new Flow.Subscriber<>() {

			@Override
			public void onSubscribe(Flow.Subscription subscription) {
				subscription.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(ByteBuffer buffer) {
				var bytes = new byte[buffer.remaining()];
				buffer.get(bytes);
				out.writeBytes(bytes);
			}

			@Override
			public void onError(Throwable t) {
				error.set(t);
				done.countDown();
			}

			@Override
			public void onComplete() {
				done.countDown();
			}
		});
		if (!done.await(10, TimeUnit.SECONDS)) {
			fail("timeout while reading the body");
		}
		if (error.get() != null) {
			throw new AssertionError("failed to read body", error.get());
		}
		return out.toByteArray();
	}
}
