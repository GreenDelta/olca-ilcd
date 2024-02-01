package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.methods.AreaOfProtection;
import org.openlca.ilcd.methods.DataSetInfo;
import org.openlca.ilcd.methods.Geography;
import org.openlca.ilcd.methods.ImpactModel;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.methods.QuantitativeReference;
import org.openlca.ilcd.methods.Time;
import org.openlca.ilcd.util.ImpactMethods;

import static org.junit.Assert.*;

public class ImpactMethodTest {

	private ImpactMethod method;

	@Before
	public void setup() {
		method = Tests.read(ImpactMethod.class, "sdk_sample_lciamethod.xml");
	}

	@Test
	public void testDataSetInfo() {
		DataSetInfo info = ImpactMethods.getDataSetInfo(method);
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
	Time time = method.methodInfo.time;
		assertEquals(1234, time.referenceYear.intValue());
		assertEquals("duration1", time.duration.get(1).value);
		assertEquals(2, time.description.size());
	}

	@Test
	public void testGeography() {
		Geography geo = method.methodInfo.geography;
		assertEquals("RER", geo.interventionLocation.code);
		assertEquals(2, geo.interventionSubLocations.size());
		assertEquals("0;100", geo.impactLocation.latLong);
		assertEquals(2, geo.description.size());
	}

	@Test
	public void testImpactModel() {
		ImpactModel model = method.methodInfo.impactModel;
		assertEquals("modelName0", model.name);
		assertEquals(2, model.description.size());
		assertEquals(2, model.sources.size());
		assertEquals(2, model.includedMethods.size());
		assertEquals(2, model.consideredMechanisms.size());
		assertEquals(2, model.flowCharts.size());
	}

	@Test
	public void testModelling() {
		var modelling = method.modelling;
		assertEquals(2, modelling.useAdvice.size());
		assertEquals(2, modelling.dataSources.size());
	}
}
