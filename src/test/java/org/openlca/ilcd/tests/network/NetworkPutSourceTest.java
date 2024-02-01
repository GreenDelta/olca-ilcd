package org.openlca.ilcd.tests.network;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.sources.AdminInfo;
import org.openlca.ilcd.sources.DataSetInfo;
import org.openlca.ilcd.sources.Source;

import java.util.Random;
import java.util.UUID;

public class NetworkPutSourceTest {

	private SodaClient client;

	@Before
	public void setUp() throws Exception {
		if (!TestServer.isAvailable())
			return;
		client = TestServer.newClient();
	}

	@Test
	public void testPutSource() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = UUID.randomUUID().toString();
		var source = new Source()
			.withAdminInfo(makeAdminInfo());
		source.withSourceInfo()
			.withDataSetInfo(makeDataInfo(id));
		client.put(source);
	}

	private DataSetInfo makeDataInfo(String id) {
		var info = new DataSetInfo().withUUID(id);
		var name = "xtest Source - " + new Random().nextInt(1000);
		info.withName()
			.add(LangString.of(name, "en"));
		return info;
	}

	private AdminInfo makeAdminInfo() {
		var info = new AdminInfo();
		info.withPublication().withVersion("01.00.000");
		return info;
	}
}
