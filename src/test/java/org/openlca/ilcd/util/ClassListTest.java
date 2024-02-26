package org.openlca.ilcd.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.Classification;

public class ClassListTest {

	@Test
	public void testRead() {
		Tests.withResources("sdk_sample_process.xml", stream -> {
			List<Classification> list = Categories.read(stream);
			assertEquals(2, list.size());
			assertEquals("ILCD", list.get(0).getName());
			assertEquals(2, list.get(0).getCategories().size());
			assertEquals("classId3", list.get(0).getCategories().get(1).getClassId());
			assertEquals("Custom", list.get(1).getName());
			assertEquals(2, list.get(1).getCategories().size());
			assertEquals("classId7", list.get(1).getCategories().get(1).getClassId());
			assertEquals("class3", list.get(1).getCategories().get(1).getName());
		});

	}

}
