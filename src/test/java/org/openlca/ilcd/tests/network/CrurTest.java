package org.openlca.ilcd.tests.network;

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

/**
 * Not a CRUD test, because soda4LCA does not support the deletion of datasets
 * currently; thus, we just read after the update.
 */
public class CrurTest {

	@Test
	public void testCrur() {
		try (var client = TestServer.newClient()) {
			// it does not work for life cycle models (Model) with
			// the current soda4LCA version
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

	void run(IDataSet ds, SodaClient client) {
		var id = UUID.randomUUID().toString();
		var name = "a " + ds.getClass().getSimpleName();
		DataSets.withUUID(ds, id);
		DataSets.withVersion(ds, "01.00.000");
		DataSets.withBaseName(ds, LangString.of(name));

		if (ds instanceof Flow flow) {
			// for flows the flow type is required
			Flows.withFlowType(flow, FlowType.ELEMENTARY_FLOW);
		}

		// c - create
		assertFalse(client.contains(ds.getClass(), id));
		client.put(ds);
		assertTrue(client.contains(ds.getClass(), id));

		// r - read
		var ret1 = client.get(ds.getClass(), id);
		assertNotSame(ds, ret1);
		assertEquals(name, LangString.getDefault(DataSets.getBaseName(ret1)));
		assertEquals("01.00.000", DataSets.getVersion(ret1));

		// u - update
		DataSets.withVersion(ret1, "02.00.000");
		DataSets.withBaseName(ret1, LangString.of(name + " II"));
		client.put(ret1);

		// r - read
		var ret2 = client.get(ds.getClass(), id);
		assertNotSame(ret1, ret2);
		var name2 = LangString.getDefault(DataSets.getBaseName(ret1));
		assertEquals(name + " II", name2);
		assertEquals("02.00.000", DataSets.getVersion(ret2));
	}
}
