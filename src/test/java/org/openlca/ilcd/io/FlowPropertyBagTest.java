package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.FlowPropertyBag;

import static org.junit.Assert.assertEquals;

public class FlowPropertyBagTest {

	private FlowPropertyBag bag;

	@Before
	public void setUp() throws Exception {
		var prop = Tests.read(FlowProperty.class, "flowproperty.xml");
		this.bag = new FlowPropertyBag(prop, "en");
	}

	@Test
	public void testGetId() {
		assertEquals("93a60a56-a3c8-14da-a746-0800200c9a66", bag.getId());
	}

	@Test
	public void testGetName() {
		assertEquals("Gross calorific value", bag.getName());
	}

	@Test
	public void testCategoryPath() {
		String[] path = Categories.getPath(bag.getValue());
		assertEquals(path.length, 1);
		assertEquals("Technical flow properties", path[0]);
	}

	@Test
	public void testGetUnitGroupReference() {
		Ref ref = bag.getUnitGroupReference();
		assertEquals("93a60a57-a3c8-11da-a746-0800200c9a66", ref.uuid);
	}

}
