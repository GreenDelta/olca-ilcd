package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.flows.epd.matml.Units;
import org.openlca.ilcd.util.Flows;

public class EpdFlowExtensionTest {

	private final Flow ds = Tests.read(Flow.class, "sdk_sample_epd_flow.xml");

	@Test
	public void testMethodExtension() {
		var method = Flows.getInventoryMethod(ds);
		assertNotNull(method);
		var ext = method.getEpdExtension();
		assertTrue(ext.getVendorSpecific());
		assertEquals("Hersteller", LangString.getDefault(ext.getVendor().getName()));
		assertEquals(
			"Verweis z.B. auf Produktkatalog",
			LangString.getDefault(ext.getDocumentation().getName()));
	}

	@Test
	public void testGenericFlow() {
		var info = Flows.getDataSetInfo(ds);
		assertNotNull(info);
		var ref = info.getEpdExtension().getGenericFlow();
		assertEquals("generic reference flow", LangString.getDefault(ref.getName()));
	}

	@Test
	public void testMatMLRoot() {
		var ext = ds.getFlowInfo().getDataSetInfo().getEpdExtension();
		var materialDoc = ext.getMaterialDoc();
		assertNotNull(ext.getMaterialDoc());

		// MaterialDoc
		var materials = materialDoc.getMaterials();
		var properties = materialDoc.getProperties();
		assertEquals(2, materials.size());
		assertEquals(2, properties.size());
	}

	@Test
	public void testMatMLMetadata() {
		var ext = ds.getFlowInfo().getDataSetInfo().getEpdExtension();
		var properties = ext.getMaterialDoc().getProperties();

		// "pr1"
		var property1 = properties.get(0);
		assertEquals("gross density", property1.getName());
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
		assertEquals("grammage", property2.getName());

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
		var properties = ext.getMaterialDoc().getProperties();
		assertEquals(2, properties.size());
		assertEquals("gross density", properties.get(0).getName());

		// Material 0
		var material0 = materials.get(0);
		var bulkDetails0 = material0.getBulkDetails();
		assertEquals("(Name of the material)", bulkDetails0.getName());
		var properties0 = bulkDetails0.getProperties();

		assertEquals("pr1", properties0.get(0).getProperty());
		assertEquals("0.58", properties0.get(0).getValue().getValue());
		assertEquals("float", properties0.get(0).getValue().getFormat());

		assertEquals("pr2", properties0.get(1).getProperty());
		assertEquals("42", properties0.get(1).getValue().getValue());
		assertEquals("integer", properties0.get(1).getValue().getFormat());

		// Material 1
		var material1 = materials.get(1);
		var bulkDetails1 = material1.getBulkDetails();
		assertEquals("(Name of the secondary material)", bulkDetails1.getName());
		var properties1 = bulkDetails1.getProperties();

		assertEquals("pr1", properties1.get(0).getProperty());
		assertEquals("4.2e7", properties1.get(0).getValue().getValue());
		assertEquals("exponential", properties1.get(0).getValue().getFormat());
	}

	@Test
	public void testNoExtension() {
		var flow = Tests.read(Flow.class, "sdk_sample_flow.xml");
		var info = Flows.getDataSetInfo(flow);
		assertNotNull(info);
		assertNull(info.getEpdExtension());
	}

}
