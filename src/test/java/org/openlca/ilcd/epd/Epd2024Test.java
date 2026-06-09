package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Epds;

public class Epd2024Test {

	private final Process ds = Tests.read(Process.class, "epd-v1.3-example.xml");

	@Test
	public void testProductIds() {
		var ids = Epds.getProductIds(ds);
		assertEquals(4, ids.size());

		var id1 = ids.getFirst();
		assertEquals("GTIN", id1.getType());
		assertEquals("3234567890126", id1.getValue());

		var id2 = ids.get(1);
		assertEquals("GTIN", id2.getType());
		assertEquals("3234567890132", id2.getValue());

		var id3 = ids.get(2);
		assertEquals("GTIN", id3.getType());
		assertEquals("3234567890288", id3.getValue());

		var id4 = ids.get(3);
		assertEquals("GMN", id4.getType());
		assertEquals("445922", id4.getValue());
	}

}
