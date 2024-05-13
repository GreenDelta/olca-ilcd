package org.openlca.ilcd.tests.network;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.lists.ContentType;

public class StocksAndCategoryListsTest {

	@Test
	@Ignore
	public void testGetCategorySystems() {
		var url = "https://www.oekobaudat.de/OEKOBAU.DAT/resource";
		try (var client = SodaClient.of(url)) {
			var list = client.getCategorySystemList();
			var names = list.getNames();
			assertEquals(1, names.size());
			assertTrue(names.contains("OEKOBAU.DAT"));
		}
	}

	@Test
	@Ignore
	public void testGetCategorySystem() {
		var url = "https://www.oekobaudat.de/OEKOBAU.DAT/resource";
		try (var client = SodaClient.of(url)) {
			var system = client.getCategorySystem("OEKOBAU.DAT");
			boolean processTypeFound = false;
			for (var list : system.getCategories()) {
				if (list.getType() == ContentType.PROCESS) {
					assertTrue(list.getCategories().size() > 5);
					processTypeFound = true;
				}
			}
			assertTrue(processTypeFound);
		}
	}
}
