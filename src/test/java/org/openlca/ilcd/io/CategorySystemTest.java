package org.openlca.ilcd.io;

import jakarta.xml.bind.JAXB;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.lists.CategorySystem;

import static org.junit.Assert.*;

public class CategorySystemTest {

	@Test
	public void testRead() {
		var system = Tests.read(
			CategorySystem.class, "sdk_sample_classification.xml");
		assertEquals("ILCD", system.getName());
		assertEquals(7, system.getCategories().size());
	}

}
