package org.openlca.ilcd.tests.network;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.contacts.AdminInfo;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.contacts.DataSetInfo;
import org.openlca.ilcd.io.SodaClient;

import java.util.Random;
import java.util.UUID;

public class NetworkPutContactTest {

	private SodaClient client;

	@Before
	public void setUp() throws Exception {
		if (!TestServer.isAvailable())
			return;
		client = TestServer.newClient();
	}

	@Test
	public void testPutContact() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = UUID.randomUUID().toString();
		var contact = new Contact()
			.withAdminInfo(makeAdminInfo());
		contact.withContactInfo().withDataSetInfo(makeDataInfo(id));
		client.put(contact);
	}

	private DataSetInfo makeDataInfo(String id) {
		String name = "xtest contact - " + new Random().nextInt(1000);
		var info = new DataSetInfo().withUUID(id);
		info.withName().add(LangString.of(name, "en"));
		info.withShortName().add(LangString.of(name, "en"));
		return info;
	}

	private AdminInfo makeAdminInfo() {
		var info = new AdminInfo();
		info.withPublication().withVersion("01.00.000");
		return info;
	}
}
