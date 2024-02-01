package org.openlca.ilcd.tests.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.descriptors.ContactDescriptor;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.descriptors.FlowDescriptor;
import org.openlca.ilcd.descriptors.FlowPropertyDescriptor;
import org.openlca.ilcd.descriptors.ProcessDescriptor;
import org.openlca.ilcd.descriptors.SourceDescriptor;
import org.openlca.ilcd.descriptors.UnitGroupDescriptor;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Run some search tests against the ELCD database. These tests are ignored by
 * default because they need some time and a valid natwork connection.
 */
@Ignore
public class SearchTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private SodaClient client;

	@Before
	public void setUp() throws Exception {
		client = SodaClient.of("http://eplca.jrc.ec.europa.eu/ELCD3/resource");
	}

	@After
	public void tearDown() {
		client.close();
	}

	@Test
	public void testListParams() {
		DescriptorList list = client.search(Flow.class, "water");
		assertTrue(list.getTotalSize() > 0);
		assertEquals(list.getStartIndex(), 0);
		assertEquals(list.getPageSize(), 500);
	}

	@Test
	public void testSearchProcess() {
		String name = "ABS";
		log.debug("test: search process with name '{}'", name);
		DescriptorList list = client.search(Process.class, name);
		assertFalse(list.getDescriptors().isEmpty());
		for (Object obj : list.getDescriptors()) {
			assertTrue(obj instanceof ProcessDescriptor);
			ProcessDescriptor descriptor = (ProcessDescriptor) obj;
			log.debug("process found: id={}", descriptor.getUuid());
		}
		client.close();
	}

	@Test
	public void testSearchFlow() {
		String name = "glycidol";
		log.debug("test: search flow with name '{}'", name);
		DescriptorList list = client.search(Flow.class, name);
		assertFalse(list.getDescriptors().isEmpty());
		for (Object obj : list.getDescriptors()) {
			assertTrue(obj instanceof FlowDescriptor);
			FlowDescriptor descriptor = (FlowDescriptor) obj;
			log.debug("flow found: id={}", descriptor.getUuid());
		}
	}

	@Test
	public void testSearchFlowProperty() {
		String name = "calorific";
		log.debug("test: search flow property with name '{}'", name);
		DescriptorList list = client.search(FlowProperty.class, name);
		assertFalse(list.getDescriptors().isEmpty());
		for (Object obj : list.getDescriptors()) {
			assertTrue(obj instanceof FlowPropertyDescriptor);
			FlowPropertyDescriptor descriptor = (FlowPropertyDescriptor) obj;
			log.debug("flow property found: id={}", descriptor.getUuid());
		}
	}

	@Test
	public void testSearchUnitGroup() {
		String name = "mass";
		log.debug("test: search unit group with name '{}'", name);
		DescriptorList list = client.search(UnitGroup.class, name);
		assertFalse(list.getDescriptors().isEmpty());
		for (Object obj : list.getDescriptors()) {
			assertTrue(obj instanceof UnitGroupDescriptor);
			UnitGroupDescriptor descriptor = (UnitGroupDescriptor) obj;
			log.debug("unit group found: id={}", descriptor.getUuid());
		}
	}

	@Test
	public void testSearchContact() {
		String name = "Review";
		log.debug("test: search contact with name '{}'", name);
		DescriptorList list = client.search(Contact.class, name);
		assertFalse(list.getDescriptors().isEmpty());
		for (Object obj : list.getDescriptors()) {
			assertTrue(obj instanceof ContactDescriptor);
			ContactDescriptor descriptor = (ContactDescriptor) obj;
			log.debug("contact found: id={}", descriptor.getUuid());
		}
	}

	@Test
	public void testSearchSource() {
		String name = "IMA-Europe_Plastic";
		log.debug("test: search source with name '{}'", name);
		DescriptorList list = client.search(Source.class, name);
		assertFalse(list.getDescriptors().isEmpty());
		for (Object obj : list.getDescriptors()) {
			assertTrue(obj instanceof SourceDescriptor);
			SourceDescriptor descriptor = (SourceDescriptor) obj;
			log.debug("contact found: id={}", descriptor.getUuid());
		}
	}

}
