package org.openlca.ilcd.tests.network;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.flowproperties.AdminInfo;
import org.openlca.ilcd.flowproperties.DataSetInfo;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.io.SodaClient;

import java.util.Random;
import java.util.UUID;

public class NetworkPutFlowPropertyTest {

	private SodaClient client;

	@Before
	public void setUp() throws Exception {
		if (!TestServer.isAvailable())
			return;
		client = TestServer.newClient();
	}

	@Test
	public void testPutFlowProperty() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = UUID.randomUUID().toString();
		FlowProperty fp = new FlowProperty()
			.withAdminInfo(makeAdminInfo());
		fp.withFlowPropertyInfo()
			.withDataSetInfo(makeDataInfo(id));
		client.put(fp);
	}

	private DataSetInfo makeDataInfo(String id) {
		var name = "xtest FlowProperty - " + new Random().nextInt(1000);
		var info = new DataSetInfo().withUUID(id);
		info.withName().add(LangString.of(name, "en"));
		return info;
	}

	private AdminInfo makeAdminInfo() {
		var info = new AdminInfo();
		info.withPublication().withVersion("01.00.000");
		return info;
	}
}
