package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.commons.LangString;

public class EpdProfileTest {
	@Test
	public void testGetDefaultProfiles() {
		var p1 = EpdProfiles.EN_15804.get();
		assertEquals(
			"EN 15804",
			LangString.getDefault(p1.getComplianceSystem().getName()));
		assertEquals(18, p1.getModules().size());
		assertEquals(25, p1.getIndicators().size());

		var p2 = EpdProfiles.EN_15804_A2.get();
		assertEquals(
			"EN 15804+A2",
			LangString.getDefault(p2.getComplianceSystem().getName()));
		assertEquals(18, p1.getModules().size());
		assertEquals(25, p1.getIndicators().size());
	}

}
