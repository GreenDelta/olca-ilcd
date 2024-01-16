package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.units.Unit;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.UnitGroups;

import java.util.List;

import static org.junit.Assert.*;

public class UnitGroupTest {

	private UnitGroup group;

	@Before
	public void setUp() throws Exception {
		group = Tests.read(UnitGroup.class, "unit.xml");
	}

	@Test
	public void testGetReferenceUnitId() {
		var qref = UnitGroups.getQuantitativeReference(group);
		assertNotNull(qref);
		assertEquals(0, qref.referenceUnit);
	}

	@Test
	public void testGetUnits() {
		List<Unit> units = UnitGroups.getUnits(group);
		assertEquals(4, units.size());
		assertEquals("kg*a", units.get(0).name);
	}

	@Test
	public void testGetId() {
		assertEquals("59f191d6-5dd3-4553-af88-1a32accfe308", group.getUUID());
	}

	@Test
	public void testGetName() {
		assertEquals("Units of mass*time", LangString.getFirst(group.getName()));
	}

	@Test
	public void testGetComment() {
		var info = UnitGroups.getDataSetInfo(group);
		assertNotNull(info);
		var comment = LangString.getFirst(info.generalComment)
			.replace("\n", "")
			.replace("\t", " ")
			.replace("    ", " ")
			.trim();
		assertEquals(
			"Reference Unit Group Data Set of the International Reference "
				+ "Life Cycle Data System (ILCD).",
			comment);
	}

	@Test
	public void testCategoryPath() {
		String[] path = Categories.getPath(group);
		assertEquals(path.length, 1);
		assertEquals("Technical unit groups", path[0]);
	}

}
