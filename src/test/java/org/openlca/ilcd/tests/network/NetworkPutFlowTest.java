package org.openlca.ilcd.tests.network;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.flows.AdminInfo;
import org.openlca.ilcd.flows.DataSetInfo;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;

import java.util.Random;
import java.util.UUID;

public class NetworkPutFlowTest {

	private SodaClient client;

	@Before
	public void setUp() throws Exception {
		if (!TestServer.isAvailable())
			return;
		client = TestServer.newClient();
	}

	@Test
	public void testPutFlow() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = UUID.randomUUID().toString();
		Flow flow = new Flow()
			.withAdminInfo(makeAdminInfo());
		flow.withFlowInfo()
			.withDataSetInfo(makeDataInfo(id));
		flow.withModelling()
			.withInventoryMethod()
			.withFlowType(FlowType.ELEMENTARY_FLOW);
		client.put(flow);
	}

	private DataSetInfo makeDataInfo(String id) {
		var info = new DataSetInfo().withUUID(id);
		String name = "xtest Flow - " + new Random().nextInt(1000);
		info.withFlowName()
			.withBaseName()
			.add(LangString.of(name, "en"));
		return info;
	}

	private AdminInfo makeAdminInfo() {
		var info = new AdminInfo();
		info.withPublication().withVersion("01.00.000");
		return info;
	}
}
