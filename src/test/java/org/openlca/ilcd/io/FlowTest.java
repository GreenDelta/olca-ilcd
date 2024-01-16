package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.flows.FlowPropertyRef;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.Flows;
import org.openlca.ilcd.flows.DataSetInfo;

import java.util.List;

import static org.junit.Assert.*;

public class FlowTest {

	private Flow flow;
	private DataSetInfo info;

	@Before
	public void setUp() throws Exception {
		flow = Tests.read(Flow.class, "flow.xml");
		info = Flows.getDataSetInfo(flow);
		assertNotNull(info);
	}

	@Test
	public void testGetUUID() {
		assertEquals("0d7a3ad1-6556-11dd-ad8b-0800200c9a66", flow.getUUID());
	}

	@Test
	public void testGetName() {
		assertEquals("glycidol", LangString.getFirst(flow.getName()));
	}

	@Test
	public void testGetCasNumber() {
		assertEquals("000556-52-5", info.casNumber);
	}

	@Test
	public void testGetSumFormula() {
		assertEquals("C3H6O2", info.sumFormula);
	}

	@Test
	public void testGetReferenceFlowPropertyId() {
		assertEquals(Integer.valueOf(0),
			Flows.getQuantitativeReference(flow).referenceFlowProperty);
	}

	@Test
	public void testGetDataSetType() {
		assertEquals(DataSetType.FLOW, flow.getDataSetType());
	}

	@Test
	public void testLocation() {
		List<LangString> location = Flows.getGeography(flow).location;
		assertEquals("US", location.get(0).value);
	}

	@Test
	public void testGetFlowPropertyReferences() {
		List<FlowPropertyRef> props = Flows.getFlowProperties(flow);
		assertTrue(props.size() == 1);
		FlowPropertyRef ref = props.get(0);
		assertEquals("93a60a56-a3c8-11da-a746-0800200b9a66",
			ref.flowProperty.uuid);
	}

	@Test
	public void testCategoryPath() {
		String[] path = Categories.getPath(flow);
		assertEquals(path.length, 3);
		assertEquals("Emissions", path[0]);
		assertEquals("Emissions to air", path[1]);
		assertEquals("Emissions to lower stratosphere and upper troposphere",
			path[2]);
	}

}
