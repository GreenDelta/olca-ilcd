package org.openlca.ilcd.io.soda;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Assume;
import org.junit.Test;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.DataSets;
import org.openlca.ilcd.util.Flows;

public class SearchTest {

	@Test
	public void testSearch() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = TestServer.newClient()) {
			List.of(
					new Contact(),
					new Source(),
					new UnitGroup(),
					new FlowProperty(),
					new Flow(),
					new Process(),
					new ImpactMethod())
				.forEach(ds -> run(ds, client));
		}
	}

	private void run(IDataSet ds, SodaClient client) {
		var id = UUID.randomUUID().toString();
		var name = ds.getClass().getSimpleName() + id.substring(24);
		DataSets.withUUID(ds, id);
		DataSets.withVersion(ds, "01.00.000");
		DataSets.withBaseName(ds, LangString.of(name));
		if (ds instanceof Flow flow) {
			Flows.withFlowType(flow, FlowType.ELEMENTARY_FLOW);
		}
		client.put(ds);
		assertTrue(client.contains(ds.getClass(), id));

		var page = client.search(ds.getClass(), name);
		assertEquals(1, page.getDescriptors().size());
		var d = page.getDescriptors().get(0);
		assertEquals(id, d.getUUID());
	}
}
