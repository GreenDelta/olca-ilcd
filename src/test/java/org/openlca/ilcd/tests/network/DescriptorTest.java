package org.openlca.ilcd.tests.network;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.descriptors.UnitGroupDescriptor;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.units.UnitGroup;
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
			if (client.contains(UnitGroup.class, group.getUUID()))
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
		assertFalse(result.descriptors.isEmpty());
		iterateAndCompareFirst(result);
	}

	private void iterateAndCompareFirst(DescriptorList result) {
		for (Object obj : result.descriptors) {
			assertTrue(obj instanceof UnitGroupDescriptor);
			var descriptor = (UnitGroupDescriptor) obj;
			log.trace("Unit group '{}' found.", descriptor.uuid);
		}
		var descriptorFromList = (UnitGroupDescriptor) result.descriptors.get(0);
		compareFirst(descriptorFromList);
		loadFull(descriptorFromList);
	}

	private void compareFirst(UnitGroupDescriptor fromList) {
		var resource = client.target(unitUrl)
			.path(fromList.uuid)
			.queryParam("view", "overview");
		log.trace("Get unit group descriptor: {}", resource.getUri());
		var descriptor = resource.request().get(UnitGroupDescriptor.class);
		assertEquals(fromList.name.get(0), descriptor.name.get(0));
		assertEquals(fromList.uuid, descriptor.uuid);
	}

	private void loadFull(UnitGroupDescriptor descriptor) {
		var resource = client.target(unitUrl)
			.path(descriptor.uuid).queryParam("format", "xml");
		log.trace("Get full unit group: {}", resource.getUri());
		var unitGroup = resource.request().get(UnitGroup.class);
		assertEquals(
			descriptor.name.get(0).value,
			unitGroup.unitGroupInfo.dataSetInfo.name.get(0).value);
		assertEquals(descriptor.uuid, unitGroup.unitGroupInfo.dataSetInfo.uuid);
	}

}
