package org.openlca.ilcd.tests.network;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.util.Flows;

import java.util.UUID;

public class FlowUpdateTest {

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
		Flow flow = makeFlow(id);
		client.put(flow);
		Assert.assertTrue(client.contains(Flow.class, id));
		flow.withAdminInfo()
			.withPublication()
			.withVersion("02.00.000");
		client.put(flow);
		flow = client.get(Flow.class, id);
		Assert.assertEquals("02.00.000", Flows.getVersion(flow));
	}

	private Flow makeFlow(String id) {
		var flow = new Flow();

		var info = flow.withFlowInfo()
			.withDataSetInfo()
			.withUUID(id);

		info.withFlowName()
			.withBaseName()
			.add(LangString.of("test flow - " + id, "en"));

		flow.withAdminInfo()
			.withPublication()
			.withVersion( "01.00.000");

		flow.withModelling()
			.withInventoryMethod()
			.withFlowType( FlowType.ELEMENTARY_FLOW);

		return flow;
	}
}
