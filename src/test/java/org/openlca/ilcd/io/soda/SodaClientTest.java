package org.openlca.ilcd.io.soda;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.SampleSource;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.SodaConnection;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.util.Sources;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/// Tests the HTTP layer of the {@link SodaClient} against a local test server.
/// These tests run without a soda4LCA instance.
public class SodaClientTest {

	private record Req(String method, String uri, String headers, byte[] body) {

		String header(String name) {
			for (var line : headers.split("\n")) {
				var idx = line.indexOf(": ");
				if (idx > 0 && line.substring(0, idx).equalsIgnoreCase(name)) {
					return line.substring(idx + 2);
				}
			}
			return null;
		}
	}

	private HttpServer server;
	private final List<Req> requests = new ArrayList<>();
	private int status = 200;
	private byte[] response = "ok".getBytes(StandardCharsets.UTF_8);
	private String sessionCookie;
	private final List<String> loginCookies = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
		server.createContext("/", this::handle);
		server.start();
	}

	@After
	public void tearDown() {
		server.stop(0);
	}

	private void handle(HttpExchange exchange) throws IOException {
		var body = exchange.getRequestBody().readAllBytes();
		var headers = new StringBuilder();
		for (var entry : exchange.getRequestHeaders().entrySet()) {
			headers.append(entry.getKey())
				.append(": ")
				.append(String.join(",", entry.getValue()))
				.append('\n');
		}
		requests.add(new Req(
			exchange.getRequestMethod(),
			exchange.getRequestURI().toString(),
			headers.toString(),
			body));

		if (exchange.getRequestURI().getPath().contains("authenticate/login")) {
			if (sessionCookie != null) {
				exchange.getResponseHeaders().add("Set-Cookie", sessionCookie);
			}
			for (var cookie : loginCookies) {
				exchange.getResponseHeaders().add("Set-Cookie", cookie);
			}
		}

		if (exchange.getRequestMethod().equals("HEAD")) {
			exchange.sendResponseHeaders(status, -1);
			exchange.close();
			return;
		}
		var out = exchange.getRequestURI().getPath().contains("authenticate/status")
			? authInfo()
			: response;
		exchange.sendResponseHeaders(status, out.length);
		try (var os = exchange.getResponseBody()) {
			os.write(out);
		}
	}

	private static byte[] authInfo() {
		try (var in = SodaClientTest.class.getResourceAsStream(
			"/org/openlca/ilcd/auth_info.xml")) {
			assertNotNull(in);
			return in.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private SodaClient client() {
		return SodaClient.of("http://localhost:" + server.getAddress().getPort())
			.useDataStock("test-stock");
	}

	@Test
	public void testLoginStoresAndSendsCookie() {
		sessionCookie = "JSESSIONID=abc123; Path=/; HttpOnly";
		try (var client = client()) {
			client.login("admin", "default");
			client.contains(Source.class, "some-id");
		}
		assertTrue(requests.size() >= 2);
		var login = requests.getFirst();
		assertEquals("POST", login.method());
		assertEquals("/authenticate/login", login.uri());
		assertEquals("application/x-www-form-urlencoded",
			login.header("Content-Type"));
		assertEquals("username=admin&password=default",
			new String(login.body(), StandardCharsets.UTF_8));
		var contains = requests.get(1);
		assertEquals("JSESSIONID=abc123", contains.header("Cookie"));
	}

	@Test
	public void testCookieNameIsCaseInsensitive() {
		// the login response sets two cookies that only differ in the case of
		// their name; the second one must replace the first one
		loginCookies.add("JSESSIONID=first");
		loginCookies.add("jsessionid=second");
		try (var client = client()) {
			client.login("admin", "default");
			client.contains(Source.class, "some-id");
		}
		assertEquals("jsessionid=second", requests.get(1).header("Cookie"));
	}

	@Test
	public void testBearerTokenIsSent() {
		try (var client = client()) {
			client.withAuthenticationToken("token-123")
				.contains(Source.class, "some-id");
		}
		assertEquals("Bearer token-123",
			requests.getFirst().header("Authorization"));
	}

	@Test
	public void testGetTokenPostsFormAndReturnsToken() {
		response = "a.b.c".getBytes(StandardCharsets.UTF_8);
		try (var client = client()) {
			var res = client.getAuthenticationToken("admin", "default");
			assertTrue(res.isOk());
			assertEquals("a.b.c", res.value());
		}
		var req = requests.getFirst();
		assertEquals("POST", req.method());
		assertEquals("/authenticate/getToken", req.uri());
		assertEquals("application/x-www-form-urlencoded",
			req.header("Content-Type"));
		assertEquals("username=admin&password=default",
			new String(req.body(), StandardCharsets.UTF_8));
	}

	@Test
	public void testGetTokenErrorReturnsResError() {
		status = 401;
		response = "permission denied".getBytes(StandardCharsets.UTF_8);
		try (var client = client()) {
			var res = client.getAuthenticationToken("admin", "wrong");
			assertTrue(res.isError());
			assertTrue(res.error(), res.error().contains("permission denied"));
		}
	}

	@Test
	public void testTokenFromConnectionIsUsedInsteadOfLogin() {
		var con = new SodaConnection();
		con.url = "http://localhost:" + server.getAddress().getPort();
		con.user = "admin";
		con.password = "default";
		con.token = "token-from-connection";
		try (var client = SodaClient.of(con)) {
			client.contains(Source.class, "some-id");
		}
		// no login request, only the actual resource request
		assertEquals(1, requests.size());
		assertEquals("Bearer token-from-connection",
			requests.getFirst().header("Authorization"));
	}

	@Test
	public void testMultipartUpload() throws Exception {
		var file = Files.createTempFile("soda-client-test", ".txt").toFile();
		Files.writeString(file.toPath(), "hello file content");

		try (var client = client()) {
			client.put(SampleSource.create(), new File[]{file});
		}

		var req = requests.getFirst();
		assertEquals("POST", req.method());
		assertEquals("/sources/withBinaries", req.uri());
		assertEquals("test-stock", req.header("Stock"));

		var contentType = req.header("Content-Type");
		assertNotNull(contentType);
		assertTrue(contentType.startsWith("multipart/form-data;boundary="));
		var boundary = contentType.substring(contentType.indexOf("boundary=") + 9);

		var body = new String(req.body(), StandardCharsets.UTF_8);
		assertTrue(body.startsWith("--" + boundary + "\r\n"
			+ "Content-Type: text/plain\r\n"
			+ "Content-Disposition: form-data; name=\"stock\"\r\n"
			+ "\r\n"
			+ "test-stock\r\n"));
		assertTrue(body.contains("Content-Disposition: form-data; name=\"file\""));
		assertTrue(body.contains("Content-Disposition: form-data; name=\""
			+ file.getName() + "\""));
		assertTrue(body.contains("hello file content"));
		assertTrue(body.endsWith("--" + boundary + "--\r\n"));

		assertEquals(req.body().length,
			Integer.parseInt(Objects.requireNonNull(req.header("Content-Length"))));
	}

	@Test
	public void testUploadDataSet() {
		var source = SampleSource.create();
		try (var client = client()) {
			client.put(source);
		}
		var req = requests.getFirst();
		assertEquals("POST", req.method());
		assertEquals("/sources", req.uri());
		assertEquals("application/xml", req.header("Content-Type"));
		assertEquals("application/xml", req.header("Accept"));
		assertEquals("test-stock", req.header("Stock"));

		var uploaded = Xml.read(Source.class, req.body());
		assertNotNull(uploaded);
		assertEquals(Sources.getUUID(source), Sources.getUUID(uploaded));
	}

	@Test
	public void testQueryParameterEncoding() throws Exception {
		response = fixture("/org/openlca/ilcd/sapi_sample_process_list.xml");
		try (var client = client()) {
			client.search(Source.class, "a name with space & umlaut ä");
		}
		assertEquals(
			"/datastocks/test-stock/sources"
				+ "?pageSize=500&startIndex=0&search=true"
				+ "&name=a+name+with+space+%26+umlaut+%C3%A4",
			requests.getFirst().uri());
	}

	@Test
	public void testPathSegmentEncoding() throws Exception {
		try (var client = client()) {
			try (InputStream in = client.getExternalDocument(
				"some-id", "my file ä.txt")) {
				in.readAllBytes();
			}
		}
		var req = requests.getFirst();
		assertEquals(
			"/datastocks/test-stock/sources/some-id/my%20file%20%C3%A4.txt",
			req.uri());
		assertEquals("application/octet-stream", req.header("Accept"));
	}

	@Test
	public void testContains() {
		try (var client = client()) {
			status = 200;
			assertTrue(client.contains(Source.class, "some-id"));
			status = 404;
			assertFalse(client.contains(Source.class, "some-id"));
		}
		var first = requests.getFirst();
		assertEquals("HEAD", first.method());
		assertEquals("/datastocks/test-stock/sources/some-id?format=xml", first.uri());
	}

	@Test
	public void testCount() throws Exception {
		response = fixture("/org/openlca/ilcd/sapi_sample_process_list.xml");
		int expected = Xml.read(DescriptorList.class, response).getTotalSize();
		try (var client = client()) {
			assertEquals(expected, client.count(Source.class));
		}
		var req = requests.getFirst();
		assertEquals(
			"/datastocks/test-stock/sources?pageSize=500&startIndex=0&countOnly=true",
			req.uri());
	}

	@Test
	public void testErrorStatusThrows() {
		status = 404;
		response = "not found".getBytes(StandardCharsets.UTF_8);
		try (var client = client()) {
			var e = assertThrows(RuntimeException.class,
				() -> client.get(Source.class, "some-id"));
			assertTrue(e.getMessage(), e.getMessage().contains("404"));
		}
	}

	@Test
	public void testExternalDocumentErrorThrows() {
		status = 404;
		response = "missing document".getBytes(StandardCharsets.UTF_8);
		try (var client = client()) {
			var e = assertThrows(RuntimeException.class,
				() -> client.getExternalDocument("some-id", "no_such_file.txt"));
			assertTrue(e.getMessage(), e.getMessage().contains("404"));
			assertTrue(e.getMessage(), e.getMessage().contains("missing document"));
		}
	}

	@Test
	public void testRepeatedErrorResponses() {
		status = 500;
		response = "boom".getBytes(StandardCharsets.UTF_8);
		try (var client = client()) {
			for (int i = 0; i < 20; i++) {
				var id = "id-" + i;
				var e = assertThrows(RuntimeException.class,
					() -> client.get(Source.class, id));
				assertTrue(e.getMessage(), e.getMessage().contains("500"));
			}
		}
		assertEquals(20, requests.size());
	}

	@Test
	public void testFailedLoginThrows() {
		status = 401;
		response = "denied".getBytes(StandardCharsets.UTF_8);
		try (var client = client()) {
			assertThrows(RuntimeException.class, () -> client.login("admin", "wrong"));
		}
	}

	private static byte[] fixture(String resource) throws Exception {
		try (var in = SodaClientTest.class.getResourceAsStream(resource)) {
			assertNotNull("missing fixture " + resource, in);
			return in.readAllBytes();
		}
	}
}
