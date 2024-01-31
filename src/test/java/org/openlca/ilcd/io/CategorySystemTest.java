package org.openlca.ilcd.io;

import jakarta.xml.bind.JAXB;
import org.junit.Test;
import org.openlca.ilcd.lists.CategorySystem;

import static org.junit.Assert.*;

public class CategorySystemTest {

	@Test
	public void testRead() throws Exception {
		var is = getClass().getResourceAsStream("sdk_sample_classification.xml");
		assertNotNull(is);
		try (is) {
			var system = JAXB.unmarshal(is, CategorySystem.class);
			assertEquals("ILCD", system.getName());
			assertEquals(7, system.getCategories().size());
		}
	}

}
