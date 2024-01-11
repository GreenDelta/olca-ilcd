package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.units.Unit;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.UnitGroupBag;
import org.openlca.ilcd.util.UnitGroups;

import java.util.List;

import static org.junit.Assert.*;

public class UnitGroupBagTest {

	private UnitGroupBag bag;
	private UnitGroup ug;

	@Before
	public void setUp() throws Exception {
		ug = Tests.read(UnitGroup.class, "unit.xml");
		bag = new UnitGroupBag(ug, "en");
	}

	@Test
	public void testGetReferenceUnitId() {
		assertEquals(Integer.valueOf(0), bag.getReferenceUnitId());
	}

	@Test
	public void testGetUnits() {
		List<Unit> units = UnitGroups.getUnits(ug);
		assertEquals(4, units.size());
		assertEquals("kg*a", units.get(0).name);
	}

	@Test
	public void testGetId() {
		assertEquals("59f191d6-5dd3-4553-af88-1a32accfe308", bag.getId());
	}

	@Test
	public void testGetName() {
		assertEquals("Units of mass*time", bag.getName());
	}

	@Test
	public void testGetComment() {
		assertEquals(
			"Reference Unit Group Data Set of the International Reference "
				+ "Life Cycle Data System (ILCD).",
			bag.getComment().replace("\n", "").replace("\t", " ")
				.replace("    ", " ").trim());
	}

	@Test
	public void testCategoryPath() {
		String[] path = Categories.getPath(bag.getValue());
		assertEquals(path.length, 1);
		assertEquals("Technical unit groups", path[0]);
	}

}
