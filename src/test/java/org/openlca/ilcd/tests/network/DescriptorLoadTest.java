package org.openlca.ilcd.tests.network;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.openlca.ilcd.descriptors.FlowDescriptor;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;

public class DescriptorLoadTest {

	@Test
	@Ignore
	public void testGetDescriptors() {
		var client = SodaClient.of("http://eplca.jrc.ec.europa.eu/ELCD3/resource");
		var descriptors = client.getDescriptors(Flow.class);
		assertTrue(descriptors.size() > 40_000);

		// check all UUIDs are unique
		Set<String> ids = new HashSet<>();
		for (var d : descriptors) {
			assertTrue(d instanceof FlowDescriptor);
			if (ids.contains(d.getUUID())) {
				fail("Duplicate UUID in descriptor list: " + d.getUUID());
			}
			ids.add(d.getUUID());
		}

		client.close();
	}

}
