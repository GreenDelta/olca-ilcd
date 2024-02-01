package org.openlca.ilcd.io;

import static org.junit.Assert.assertEquals;
import static org.openlca.ilcd.util.DataSets.*;

import java.io.InputStream;
import java.util.function.Consumer;

import org.junit.Test;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import jakarta.xml.bind.JAXB;

public class IDataSetTest {

	@Test
	public void testSource() throws Exception {
		with("sdk_sample_source.xml", Source.class, ds -> {
			assertEquals("00000000-0000-0000-0000-000000000000", getUUID(ds));
			assertEquals("00.00", getVersion(ds));
			assertEquals(DataSetType.SOURCE, getType(ds));
			assertEquals("http://www.ilcd-network.org/data/processes/sample_source.xml",
					getUri(ds).trim());
			assertEquals(2, getClassifications(ds).size());
		});
	}

	@Test
	public void testContact() throws Exception {
		with("sdk_sample_contact.xml", Contact.class, ds -> {
			assertEquals("00000000-0000-0000-0000-000000000000", getUUID(ds));
			assertEquals("00.00", getVersion(ds));
			assertEquals(DataSetType.CONTACT, getType(ds));
			assertEquals("http://www.ilcd-network.org/data/processes/sample_contact.xml",
					getUri(ds).trim());
			assertEquals(2, getClassifications(ds).size());
		});
	}

	@Test
	public void testUnitGroup() throws Exception {
		with("sdk_sample_unitgroup.xml", UnitGroup.class, ds -> {
			assertEquals("00000000-0000-0000-0000-000000000000", getUUID(ds));
			assertEquals("00.00", getVersion(ds));
			assertEquals(DataSetType.UNIT_GROUP, getType(ds));
			assertEquals("http://www.ilcd-network.org/data/processes/sample_unitgroup.xml",
					getUri(ds).trim());
			assertEquals(2, getClassifications(ds).size());
		});
	}

	@Test
	public void testFlowProperty() throws Exception {
		with("sdk_sample_flowproperty.xml", FlowProperty.class, ds -> {
			assertEquals("00000000-0000-0000-0000-000000000000", getUUID(ds));
			assertEquals("00.00", getVersion(ds));
			assertEquals(DataSetType.FLOW_PROPERTY, getType(ds));
			assertEquals("http://www.ilcd-network.org/data/processes/sample_flowproperty.xml",
					getUri(ds).trim());
			assertEquals(2, getClassifications(ds).size());
		});
	}

	@Test
	public void testFlow() throws Exception {
		with("sdk_sample_flow.xml", Flow.class, ds -> {
			assertEquals("00000000-0000-0000-0000-000000000000", getUUID(ds));
			assertEquals("00.00", getVersion(ds));
			assertEquals(DataSetType.FLOW, getType(ds));
			assertEquals("http://www.ilcd-network.org/data/processes/sample_flow.xml",
					getUri(ds).trim());
			assertEquals(2, getClassifications(ds).size());
		});
	}

	@Test
	public void testProcess() throws Exception {
		with("sdk_sample_process.xml", org.openlca.ilcd.processes.Process.class, ds -> {
			assertEquals("00000000-0000-0000-0000-000000000000", getUUID(ds));
			assertEquals("00.00", getVersion(ds));
			assertEquals(DataSetType.PROCESS, getType(ds));
			assertEquals("http://www.ilcd-network.org/data/processes/sample_process.xml",
					getUri(ds).trim());
			assertEquals(2, getClassifications(ds).size());
		});
	}

	@Test
	public void testMethod() throws Exception {
		with("sdk_sample_lciamethod.xml", ImpactMethod.class, ds -> {
			assertEquals("00000000-0000-0000-0000-000000000000", getUUID(ds));
			assertEquals("00.00", getVersion(ds));
			assertEquals(DataSetType.IMPACT_METHOD, getType(ds));
			assertEquals("http://www.ilcd-network.org/data/lciamethods/sample_lciamethod.xml", getUri(ds).trim());
			assertEquals(1, getClassifications(ds).size());
		});
	}

	private void with(String xml, Class<?> type, Consumer<IDataSet> fn)
			throws Exception {
		try (InputStream is = getClass().getResourceAsStream(xml)) {
			Object o = JAXB.unmarshal(is, type);
			IDataSet ds = (IDataSet) o;
			fn.accept(ds);
		}
	}

}
