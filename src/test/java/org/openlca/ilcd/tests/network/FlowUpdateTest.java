package org.openlca.ilcd.tests.network;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.util.Flows;

public class FlowUpdateTest {

	@Test
	public void testPutFlow() {
		Assume.assumeTrue(TestServer.isAvailable());
		var id = UUID.randomUUID().toString();
		var flow = makeFlow(id);
		try (var client = TestServer.newClient()) {
			client.put(flow);
			Assert.assertTrue(client.contains(Flow.class, id));
			flow.withAdminInfo()
				.withPublication()
				.withVersion("02.00.000");
			client.put(flow);
			flow = client.get(Flow.class, id);
			Assert.assertEquals("02.00.000", Flows.getVersion(flow));
		}
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
			.withVersion("01.00.000");

		flow.withModelling()
			.withInventoryMethod()
			.withFlowType(FlowType.ELEMENTARY_FLOW);

		return flow;
	}
}
