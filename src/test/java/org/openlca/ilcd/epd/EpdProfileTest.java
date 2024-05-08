package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

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
	public void testDefaultCodesAndNames() {
		// all indicators in default profiles need to have a code
		// and that code needs to be unique within that profile
		// also a name in English must be present
		var profiles = EpdProfiles.getAll();
		assertEquals(3, profiles.size());
		for (var profile : profiles) {
			var codes = new HashSet<String>();
			for (var indicator : profile.getIndicators()) {
				var code = indicator.getCode();
				assertNotNull(code);
				assertFalse(codes.contains(code));
				codes.add(code);
				var name = LangString.getDefault(indicator.getRef().getName());
				assertNotNull(name);
			}
		}
	}

	@Test
	public void testGetIndicators() {
		var id = "4331bbdb-978a-490d-8707-eeb047f01a55";
		var code = "GWP-luluc";
		var indicator = EpdProfiles.getIndicatorForId(id);
		assertNotNull(indicator);
		assertEquals(code, indicator.getCode());
		boolean found = false;
		for (var i : EpdProfiles.getIndicatorsForCode(code)) {
			var iid = i.getRef().getUUID();
			if (Objects.equals(id, iid)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test
	public void testIndicatorIdsAndCodes() {
		// in the default profiles, indicators with the same ID
		// must have the same code
		var m = new HashMap<String, String>();
		for (var profile : EpdProfiles.getAll()) {
			for (var i : profile.getIndicators()) {
				var id = i.getRef().getUUID();
				var code = i.getCode();
				var otherCode = m.get(id);
				if (otherCode == null) {
					m.put(id, code);
					continue;
				}
				assertEquals(otherCode, code);
			}
		}
	}

}
