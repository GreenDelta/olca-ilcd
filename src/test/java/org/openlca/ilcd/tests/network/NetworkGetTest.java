package org.openlca.ilcd.tests.network;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.Contacts;
import org.openlca.ilcd.util.FlowProperties;
import org.openlca.ilcd.util.Flows;
import org.openlca.ilcd.util.Processes;
import org.openlca.ilcd.util.Sources;
import org.openlca.ilcd.util.UnitGroups;

public class NetworkGetTest {

	private SodaClient client;

	@Before
	public void setUp() throws Exception {
		if (!TestServer.isAvailable())
			return;
		DataSets.upload();
		client = TestServer.newClient();
	}

	@Test
	public void testGetProcess() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = "76d6aaa4-37e2-40b2-994c-03292b600074";
		Process process = client.get(Process.class, id);
		assertEquals(id, Processes.getUUID(process));
		testContains(Process.class, id);
	}

	@Test
	public void testGetFlow() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = "0d7a3ad1-6556-11dd-ad8b-0800200c9a66";
		Flow flow = client.get(Flow.class, id);
		assertEquals(id, Flows.getUUID(flow));
		testContains(Flow.class, id);
	}

	@Test
	public void testGetFlowProperty() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = "93a60a56-a3c8-14da-a746-0800200c9a66";
		FlowProperty property = client.get(FlowProperty.class, id);
		assertEquals(id, FlowProperties.getUUID(property));
		testContains(FlowProperty.class, id);
	}

	@Test
	public void testGetUnitGroup() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = "59f191d6-5dd3-4553-af88-1a32accfe308";
		UnitGroup group = client.get(UnitGroup.class, id);
		assertEquals(id, UnitGroups.getUUID(group));
		testContains(UnitGroup.class, id);
	}

	@Test
	public void testGetContact() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = "177ca340-ffa2-11da-92e3-0800200c9a66";
		Contact contact = client.get(Contact.class, id);
		assertEquals(id, Contacts.getUUID(contact));
		testContains(Contact.class, id);
	}

	@Test
	public void testGetSource() {
		Assume.assumeTrue(TestServer.isAvailable());
		String id = "2c699413-f88b-4cb5-a56d-98cb4068472f";
		Source source = client.get(Source.class, id);
		assertEquals(id, Sources.getUUID(source));
		testContains(Source.class, id);
	}

	private void testContains(Class<? extends IDataSet> type, String id) {
		assertTrue(client.contains(type, id));
		assertFalse(client.contains(type, UUID.randomUUID().toString()));
	}

}
