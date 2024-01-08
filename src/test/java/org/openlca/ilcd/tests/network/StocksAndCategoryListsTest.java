package org.openlca.ilcd.tests.network;

import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;
import org.openlca.ilcd.descriptors.CategorySystemList;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.lists.CategoryList;
import org.openlca.ilcd.lists.CategorySystem;
import org.openlca.ilcd.lists.ContentType;

import java.util.List;

import static org.junit.Assert.*;

public class StocksAndCategoryListsTest {

	@Test
	public void testGetDataStocks() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = TestServer.newClient()) {
			var stocks = client.getDataStockList();
			assertFalse(stocks.dataStocks.isEmpty());
		}
	}

	@Test
	@Ignore
	public void testGetCategorySystems() {
		var url = "http://www.oekobaudat.de/OEKOBAU.DAT/resource";
		try (var client = SodaClient.of(url)) {
			CategorySystemList list = client.getCategorySystemList();
			List<String> names = list.getNames();
			assertEquals(1, names.size());
			assertTrue(names.contains("OEKOBAU.DAT"));
		}
	}

	@Test
	@Ignore
	public void testGetCategorySystem() {
		var url = "http://www.oekobaudat.de/OEKOBAU.DAT/resource";
		try (SodaClient client = SodaClient.of(url)) {
			CategorySystem system = client.getCategorySystem("OEKOBAU.DAT");
			boolean processTypeFound = false;
			for (CategoryList list : system.categories) {
				if (list.type == ContentType.PROCESS) {
					assertTrue(list.categories.size() > 5);
					processTypeFound = true;
				}
			}
			assertTrue(processTypeFound);
		}
	}

}
