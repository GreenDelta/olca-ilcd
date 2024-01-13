package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.flowproperties.DataSetInfo;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.FlowProperties;

import static org.junit.Assert.*;

public class FlowPropertyTest {

	private FlowProperty prop;
	private DataSetInfo info;

	@Before
	public void setUp() throws Exception {
		prop = Tests.read(FlowProperty.class, "flowproperty.xml");
		info = FlowProperties.getDataSetInfo(prop);
		assertNotNull(info);
	}

	@Test
	public void testUUID() {
		assertEquals("93a60a56-a3c8-14da-a746-0800200c9a66", prop.getUUID());
	}

	@Test
	public void testName() {
		assertEquals("Gross calorific value", LangString.getFirst(info.name));
	}

	@Test
	public void testCategoryPath() {
		var path = Categories.getPath(prop);
		assertEquals(path.length, 1);
		assertEquals("Technical flow properties", path[0]);
	}

	@Test
	public void testUnitGroupReference() {
		var ref = FlowProperties.getUnitGroupRef(prop);
		assertNotNull(ref);
		assertEquals("93a60a57-a3c8-11da-a746-0800200c9a66", ref.uuid);
	}

}
