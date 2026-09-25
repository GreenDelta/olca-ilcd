package org.openlca.ilcd.io.soda;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Test;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.SodaConnection;

/// Tests the token based authentication against a local soda4LCA instance.
/// The tests are skipped when no instance is available at localhost:8080.
public class TokenAuthenticationTest {

	@Test
	public void testRequestToken() {
		Assume.assumeTrue(TestServer.isAvailable());
		var token = TestServer.newToken();
		assertFalse(token.isBlank());
		// a token is a JSON web token with three parts
		assertEquals(3, token.split("\\.").length);
	}

	@Test
	public void testWrongPasswordGetsNoToken() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = SodaClient.of(TestServer.ENDPOINT)) {
			var res = client.getAuthenticationToken(TestServer.USER, "no-password");
			assertTrue(res.isError());
			var error = res.error().toLowerCase();
			assertTrue(res.error(),
				error.contains("denied") || error.contains("incorrect"));
		}
	}

	@Test
	public void testTokenClientCanRunCrur() {
		Assume.assumeTrue(TestServer.isAvailable());
		var token = TestServer.newToken();
		try (var client = TestServer.newTokenClient(token)) {
			CrurTest.runWith(client);
		}
	}

	@Test
	public void testTokenInConnectionIsUsed() {
		Assume.assumeTrue(TestServer.isAvailable());
		var con = new SodaConnection();
		con.url = TestServer.ENDPOINT;
		con.token = TestServer.newToken();
		// user and password must be ignored when a token is set
		con.user = "no-such-user";
		con.password = "wrong-password";
		try (var client = SodaClient.of(con)) {
			assertFalse(client.getDataStockList().getDataStocks().isEmpty());
		}
	}

	@Test(expected = Exception.class)
	public void testInvalidTokenIsRejected() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = SodaClient.of(TestServer.ENDPOINT)
			.withAuthenticationToken("not.a.valid.token")) {
			client.getDataStockList();
		}
	}
}
