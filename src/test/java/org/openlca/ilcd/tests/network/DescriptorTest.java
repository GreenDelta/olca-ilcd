package org.openlca.ilcd.tests.network;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.descriptors.UnitGroupDescriptor;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.UnitGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;


public class DescriptorTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String unitUrl = TestServer.ENDPOINT + "/unitgroups";
	private final Client client = ClientBuilder.newClient();

	@Before
	public void setup() throws Exception {
		if (!TestServer.isAvailable())
			return;
		try (var client = TestServer.newClient();
				 var stream = getClass().getResourceAsStream("unit.xml")) {
			var group = Xml.read(UnitGroup.class, stream);
			if (client.contains(UnitGroup.class, UnitGroups.getUUID(group)))
				return;
			client.put(group);
		}
	}

	@Test
	public void testGetDescriptors() {
		Assume.assumeTrue(TestServer.isAvailable());
		log.trace("Run testGetDescriptors");
		log.trace("Get unit groups: {}", unitUrl);
		var result = client.target(unitUrl)
			.request()
			.get(DescriptorList.class);
		assertFalse(result.getDescriptors().isEmpty());
		iterateAndCompareFirst(result);
	}

	private void iterateAndCompareFirst(DescriptorList result) {
		for (Object obj : result.getDescriptors()) {
			assertTrue(obj instanceof UnitGroupDescriptor);
			var descriptor = (UnitGroupDescriptor) obj;
			log.trace("Unit group '{}' found.", descriptor.getUUID());
		}
		var descriptorFromList = (UnitGroupDescriptor) result.getDescriptors().get(0);
		compareFirst(descriptorFromList);
		loadFull(descriptorFromList);
	}

	private void compareFirst(UnitGroupDescriptor fromList) {
		var resource = client.target(unitUrl)
			.path(fromList.getUUID())
			.queryParam("view", "overview");
		log.trace("Get unit group descriptor: {}", resource.getUri());
		var descriptor = resource.request().get(UnitGroupDescriptor.class);
		assertEquals(fromList.getName().get(0), descriptor.getName().get(0));
		assertEquals(fromList.getUUID(), descriptor.getUUID());
	}

	private void loadFull(UnitGroupDescriptor d) {
		var resource = client.target(unitUrl)
			.path(d.getUUID()).queryParam("format", "xml");
		log.trace("Get full unit group: {}", resource.getUri());
		var group = resource.request().get(UnitGroup.class);
		var info = UnitGroups.getDataSetInfo(group);
		assertNotNull(info);
		assertEquals(LangString.getFirst(d.getName()), info.getName().get(0).value);
		assertEquals(d.getUUID(), info.getUUID());
	}

}
