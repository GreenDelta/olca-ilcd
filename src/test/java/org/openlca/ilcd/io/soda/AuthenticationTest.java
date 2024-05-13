package org.openlca.ilcd.io.soda;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Assume;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.io.AuthInfo;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.SodaConnection;

import jakarta.xml.bind.JAXB;

public class AuthenticationTest {

	@Test
	public void testReadAuthInfo() {
		var auth = readTestXml("auth_info.xml");
		assertTrue(auth.isAuthenticated());
		assertEquals("openlca", auth.getUser());
		assertTrue(auth.getRoles().contains("ADMIN"));
		assertTrue(auth.getRoles().contains("SUPER_ADMIN"));
		assertTrue(auth.getDataStocks().get(0).isReadAllowed());
		assertTrue(auth.getDataStocks().get(0).isExportAllowed());
	}

	@Test
	public void testIsNotAuthenticated() {
		var auth = readTestXml("auth_info_no_auth.xml");
		assertFalse(auth.isAuthenticated());
		assertNull(auth.getUser());
		assertTrue(auth.getDataStocks().isEmpty());
	}

	@Test
	public void testReadFromServer() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = TestServer.newClient()) {
			var info = client.getAuthInfo();
			assertTrue(info.isAuthenticated());
			assertEquals(TestServer.USER, info.getUser());
		}
	}

	@Test(expected = Exception.class)
	public void testCreateWithWrongPassword() {
		Assume.assumeTrue(TestServer.isAvailable());
		var con = new SodaConnection();
		con.url = TestServer.ENDPOINT;
		con.user = "user";
		con.password = "invalid";
		var client = SodaClient.of(con);
		client.close();
	}

	@Test
	public void testGetDataStocks() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = TestServer.newClient()) {
			var stocks = client.getDataStockList();
			assertFalse(stocks.getDataStocks().isEmpty());
		}
	}

	private AuthInfo readTestXml(String res) {
		var ref = new AtomicReference<AuthInfo>();
		Tests.withResources(
			res, stream -> ref.set(JAXB.unmarshal(stream, AuthInfo.class)));
		return ref.get();
	}
}
