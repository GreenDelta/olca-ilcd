package org.openlca.ilcd.epd;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.flows.epd.matml.DataFormat;
import org.openlca.ilcd.flows.epd.matml.Units;
import org.openlca.ilcd.util.Flows;

import static org.junit.Assert.*;

public class EpdFlowExtensionTest {

	private final Flow ds = Tests.read(Flow.class, "sdk_sample_epd_flow.xml");

	@Test
	public void testMatMLRoot() {
		var ext = ds.getFlowInfo().getDataSetInfo().getEpdExtension();
		var materialDoc = ext.getMaterialDoc();
		assertNotNull(ext.getMaterialDoc());

		// MaterialDoc
		var materials = materialDoc.getMaterials();
		var metadata = materialDoc.getMetadata();
		assertEquals(2, materials.size());
		assertNotNull(metadata);
	}

	@Test
	public void testMatMLMetadata() {
		var ext = ds.getFlowInfo().getDataSetInfo().getEpdExtension();
		var properties = ext.getMaterialDoc().getMetadata().getProperties();

		// "pr1"
		var property1 = properties.get(0);
		assertEquals("gross density", property1.getName().getValue());
		assertEquals("pr1", property1.getId());

		assertEquals(Units.class, property1.getUnits().getClass());
		var units1 = (Units) property1.getUnits();
		assertEquals("kg/m^3", units1.getName());
		assertEquals("kilograms per cubic metre", units1.getDescription());

		assertNull(units1.getUnits().get(0).getPower());
		assertEquals("kg", units1.getUnits().get(0).getName());
		assertEquals("-3", units1.getUnits().get(1).getPower());
		assertEquals("m", units1.getUnits().get(1).getName());

		// "pr2"
		var property2 = properties.get(1);
		assertEquals("pr2", property2.getId());
		assertEquals("grammage", property2.getName().getValue());

		assertEquals(Units.class, property2.getUnits().getClass());
		var units2 = (Units) property2.getUnits();
		assertEquals("kg/m^2", units2.getName());
		assertEquals("kilograms per square metre", units2.getDescription());

		assertNull(units2.getUnits().get(0).getPower());
		assertEquals("kg", units2.getUnits().get(0).getName());
		assertEquals("-2", units2.getUnits().get(1).getPower());
		assertEquals("m", units2.getUnits().get(1).getName());
	}

	@Test
	public void testMatMLMaterial() {
		var ext = ds.getFlowInfo().getDataSetInfo().getEpdExtension();
		var materials = ext.getMaterialDoc().getMaterials();
		var properties = ext.getMaterialDoc().getMetadata().getProperties();

		// Material 0
		var material0 = materials.get(0);
		var bulkDetails0 = material0.getBulkDetails();
		assertEquals("(Name of the material)", bulkDetails0.getName().getValue());
		var properties0 = bulkDetails0.getProperties();

		assertEquals(properties.get(0), properties0.get(0).getProperty());
		assertEquals("0.58", properties0.get(0).getData().getValue());
		assertEquals(DataFormat.FLOAT, properties0.get(0).getData().getFormat());

		assertEquals(properties.get(1), properties0.get(1).getProperty());
		assertEquals("42", properties0.get(1).getData().getValue());
		assertEquals(DataFormat.INTEGER, properties0.get(1).getData().getFormat());

		// Material 1
		var material1 = materials.get(1);
		var bulkDetails1 = material1.getBulkDetails();
		assertEquals("(Name of the secondary material)", bulkDetails1.getName().getValue());
		var properties1 = bulkDetails1.getProperties();

		assertEquals(properties.get(0), properties1.get(0).getProperty());
		assertEquals("4.2e7", properties1.get(0).getData().getValue());
		assertEquals(DataFormat.EXPONENTIAL, properties1.get(0).getData().getFormat());
	}

	@Test
	public void testNoExtension() {
		var flow = Tests.read(Flow.class, "sdk_sample_flow.xml");
		var info = Flows.getDataSetInfo(flow);
		assertNotNull(info);
		assertNull(info.getEpdExtension());
	}

}
