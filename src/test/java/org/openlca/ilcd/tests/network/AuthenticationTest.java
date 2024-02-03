package org.openlca.ilcd.tests.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Assume;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.io.AuthInfo;

import jakarta.xml.bind.JAXB;

public class AuthenticationTest {

	@Test
	public void testReadAuthInfo() {
		AuthInfo auth = readTestXml("auth_info.xml");
		assertTrue(auth.isAuthenticated());
		assertEquals("openlca", auth.getUser());
		assertTrue(auth.getRoles().contains("ADMIN"));
		assertTrue(auth.getRoles().contains("SUPER_ADMIN"));
		assertTrue(auth.getDataStocks().get(0).isReadAllowed());
		assertTrue(auth.getDataStocks().get(0).isExportAllowed());
	}

	@Test
	public void testIsNotAuthenticated() {
		AuthInfo auth = readTestXml("auth_info_no_auth.xml");
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

	private AuthInfo readTestXml(String res) {
		var ref = new AtomicReference<AuthInfo>();
		Tests.withResources(
			res, stream -> ref.set(JAXB.unmarshal(stream, AuthInfo.class)));
		return ref.get();
	}
}
