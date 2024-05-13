package org.openlca.ilcd.io.soda;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Assume;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.SodaQuery;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.DataSets;

public class DescriptorTest {

	@Test
	public void testPageCount() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = TestServer.newClient()) {
			var types = List.of(
				Contact.class,
				Source.class,
				UnitGroup.class,
				FlowProperty.class,
				Flow.class,
				Process.class,
				ImpactMethod.class); // Model.class fails!
			for (var type : types) {
				int count = client.query(type, new SodaQuery())
					.getTotalSize();
				var ds = client.getDescriptors(type);
				assertEquals(count, ds.size());
			}
		}
	}

	@Test
	public void testGetDescriptors() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = TestServer.newClient()) {
			var specs = List.of(
				Spec.of(Contact.class, "contact.xml"),
				Spec.of(Source.class, "source.xml"),
				Spec.of(UnitGroup.class, "unit.xml"),
				Spec.of(FlowProperty.class, "flowproperty.xml"),
				Spec.of(Flow.class, "flow.xml"),
				Spec.of(Process.class, "process.xml"));
			for (var spec : specs) {
				spec.runOn(client);
			}
		}
	}

	private record Spec<T extends IDataSet>(Class<T> type, String file) {

		static <T extends IDataSet> Spec<T> of(Class<T> type, String file) {
			return new Spec<>(type, file);
		}

		void runOn(SodaClient client) {
			T ds = Tests.read(type, file);
			var uuid = UUID.randomUUID().toString();
			DataSets.withUUID(ds, uuid);
			if (!client.contains(type, uuid)) {
				client.put(ds);
			}
			assertTrue(client.contains(type, uuid));

			boolean found = false;
			for (var d : client.getDescriptors(type)) {
				if (uuid.equals(d.getUUID())) {
					found = true;
					break;
				}
			}
			assertTrue(found);
		}
	}
}
