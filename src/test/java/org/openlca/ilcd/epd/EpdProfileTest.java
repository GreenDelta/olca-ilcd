package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.commons.LangString;

public class EpdProfileTest {

	@Test
	public void testComplianceSystems() {
		var p1 = EpdProfiles.EN_15804.get();
		assertEquals(
			"EN 15804",
			LangString.getDefault(p1.getComplianceSystem().getName()));
		assertEquals(18, p1.getModules().size());
		assertEquals(25, p1.getIndicators().size());

		var p2 = EpdProfiles.EN_15804_A2_EF30.get();
		assertEquals(
			"EN 15804+A2",
			LangString.getDefault(p2.getComplianceSystem().getName()));
		assertEquals(18, p1.getModules().size());
		assertEquals(25, p1.getIndicators().size());
	}

	@Test
	public void testGetByDefaultID() {
		// for default profiles, the identifier of the enum
		// constant and the profile ID must be the same
		for (var v : EpdProfiles.values()) {
			var id = v.name();
			var profile = EpdProfiles.get(id);
			assertNotNull(profile);
			assertEquals(id, profile.getId());
		}
	}

	@Test
	public void testIndicatorNamesAndCodes() {
		var profile = EpdProfiles.EN_15804_A2_EF30.get();
		for (var i : profile.getIndicators()) {
			assertNotNull(i.getCode());
			var name = LangString.getDefault(i.getRef().getName());
			assertNotNull(name);
		}
	}

}
