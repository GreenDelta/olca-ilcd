package org.openlca.ilcd.io;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.function.Consumer;

import org.junit.Test;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import jakarta.xml.bind.JAXB;

public class IDataSetRefTest {

	@Test
	public void testSource() throws Exception {
		with("sdk_sample_source.xml", Source.class, ref -> {
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertEquals(DataSetType.SOURCE, ref.getType());
			assertEquals("shortName0", ref.getName().get(0).value.trim());
			assertEquals("http://www.ilcd-network.org/data/processes/sample_source.xml",
					ref.getUri().trim());
		});
	}

	@Test
	public void testContact() throws Exception {
		with("sdk_sample_contact.xml", Contact.class, ref -> {
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertEquals("name0", ref.getName().get(0).value.trim());
			assertEquals(DataSetType.CONTACT, ref.getType());
			assertEquals("http://www.ilcd-network.org/data/processes/sample_contact.xml",
					ref.getUri().trim());
		});
	}

	@Test
	public void testUnitGroup() throws Exception {
		with("sdk_sample_unitgroup.xml", UnitGroup.class, ref -> {
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertEquals("name0", ref.getName().get(0).value.trim());
			assertEquals(DataSetType.UNIT_GROUP, ref.getType());
			assertEquals("http://www.ilcd-network.org/data/processes/sample_unitgroup.xml",
					ref.getUri().trim());
		});
	}

	@Test
	public void testFlowProperty() throws Exception {
		with("sdk_sample_flowproperty.xml", FlowProperty.class, ref -> {
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertEquals("name0", ref.getName().get(0).value.trim());
			assertEquals(DataSetType.FLOW_PROPERTY, ref.getType());
			assertEquals("http://www.ilcd-network.org/data/processes/sample_flowproperty.xml",
					ref.getUri().trim());
		});
	}

	@Test
	public void testFlow() throws Exception {
		with("sdk_sample_flow.xml", Flow.class, ref -> {
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertEquals("baseName0", ref.getName().get(0).value.trim());
			assertEquals(DataSetType.FLOW, ref.getType());
			assertEquals("http://www.ilcd-network.org/data/processes/sample_flow.xml",
					ref.getUri().trim());
		});
	}

	@Test
	public void testProcess() throws Exception {
		with("sdk_sample_process.xml", org.openlca.ilcd.processes.Process.class, ref -> {
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertEquals("baseName0", ref.getName().get(0).value.trim());
			assertEquals(DataSetType.PROCESS, ref.getType());
			assertEquals("http://www.ilcd-network.org/data/processes/sample_process.xml",
					ref.getUri().trim());
		});
	}

	@Test
	public void testMethod() throws Exception {
		with("sdk_sample_lciamethod.xml", ImpactMethod.class, ref -> {
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertEquals("name0", ref.getName().get(0).value.trim());
			assertEquals(DataSetType.IMPACT_METHOD, ref.getType());
			assertEquals("http://www.ilcd-network.org/data/lciamethods/sample_lciamethod.xml", ref.getUri().trim());
		});
	}

	private void with(String xml, Class<?> type, Consumer<Ref> fn)
			throws Exception {
		try (InputStream is = getClass().getResourceAsStream(xml)) {
			Object o = JAXB.unmarshal(is, type);
			IDataSet ds = (IDataSet) o;
			fn.accept(Ref.of(ds));
		}
	}
}
