package org.openlca.ilcd.tests.network;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

public class IterTest {

	private final List<Class<? extends IDataSet>> types = List.of(
		Contact.class,
		Source.class,
		UnitGroup.class,
		FlowProperty.class,
		Flow.class,
		Process.class,
		ImpactMethod.class
	);

	@Test
	public void testIter() {
		try (var client = TestServer.newClient()) {
			for (var type : types) {
				int count = 0;
				for (var ds : client.iter(type)) {
					assertEquals(type, ds.getClass());
					count++;
				}
				assertEquals(client.count(type), count);
			}
		}
	}
}
