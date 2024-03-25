package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.methods.AreaOfProtection;
import org.openlca.ilcd.methods.Geography;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.methods.ImpactModel;
import org.openlca.ilcd.methods.Time;
import org.openlca.ilcd.util.ImpactMethods;

public class ImpactMethodTest {

	private ImpactMethod method;

	@Before
	public void setup() {
		method = Tests.read(ImpactMethod.class, "sdk_sample_lciamethod.xml");
	}

	@Test
	public void testDataSetInfo() {
		var info = ImpactMethods.getDataSetInfo(method);
		assertNotNull(info);
		assertEquals("00000000-0000-0000-0000-000000000000", info.getUUID());
		assertEquals(2, info.getName().size());
		assertEquals(2, info.getMethods().size());
		assertEquals("ILCD", info.getClassifications().get(0).getName());
		assertEquals("Acidification", info.getImpactCategories().get(1));
		assertEquals(AreaOfProtection.NATURAL_RESOURCES, info.getAreasOfProtection().get(0));
		assertEquals(2, info.getComment().size());
		assertEquals(2, info.getExternalDocs().size());

	}

	@Test
	public void testQuantitativeReference() {
		var qRef = ImpactMethods.getQuantitativeReference(method);
		assertNotNull(qRef);
		assertEquals(DataSetType.FLOW_PROPERTY, qRef.getQuantity().getType());
	}

	@Test
	public void testTime() {
		Time time = ImpactMethods.getTime(method);
		assertNotNull(time);
		assertEquals(1234, time.getReferenceYear().intValue());
		assertEquals("duration1", time.getDuration().get(1).getValue());
		assertEquals(2, time.getDescription().size());
	}

	@Test
	public void testGeography() {
		Geography geo = method.getMethodInfo().getGeography();
		assertEquals("RER", geo.getInterventionLocation().getCode());
		assertEquals(2, geo.getInterventionSubLocations().size());
		assertEquals("0;100", geo.getImpactLocation().getLatLong());
		assertEquals(2, geo.getDescription().size());
	}

	@Test
	public void testImpactModel() {
		ImpactModel model = method.getMethodInfo().getImpactModel();
		assertEquals("modelName0", model.getName());
		assertEquals(2, model.getDescription().size());
		assertEquals(2, model.getSources().size());
		assertEquals(2, model.getIncludedMethods().size());
		assertEquals(2, model.getConsideredMechanisms().size());
		assertEquals(2, model.getFlowCharts().size());
	}

	@Test
	public void testModelling() {
		var modelling = method.getModelling();
		assertEquals(2, modelling.getUseAdvice().size());
		assertEquals(2, modelling.getDataSources().size());
	}
}
