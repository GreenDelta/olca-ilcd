package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.sources.DataSetInfo;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.Sources;

public class SourceTest {

	private Source source;
	private DataSetInfo info;

	@Before
	public void setUp() throws Exception {
		source = Tests.read(Source.class, "source.xml");
		info = Sources.getDataSetInfo(source);
		assertNotNull(info);
	}

	@Test
	public void testUUID() {
		assertEquals(
			"2c699413-f88b-4cb5-a56d-98cb4068472f",
			Sources.getUUID(source));
	}

	@Test
	public void testName() {
		assertEquals(
			"IMA-Europe_Plastic_Clay_diagramme_2c699413-f88b-4cb5-a56d-98cb4068472f.jpg",
			LangString.getDefault(info.getName()));
	}

	@Test
	public void testDescription() {
		assertNull(LangString.getDefault(info.getDescription()));
	}

	@Test
	public void testCitation() {
		assertEquals("GaBi database", info.getCitation());
	}

	@Test
	public void testCategoryPath() {
		String[] path = Categories.getPath(source);
		assertEquals(path.length, 1);
		assertEquals("Images", path[0]);
	}

}
