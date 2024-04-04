package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;

public class EpdIndicatorResultTest {

	@Test
	public void testReadWrite() {
		var ds = Tests.read(Process.class, "sdk_sample_epd.xml");
		var results = EpdIndicatorResult.allOf(ds);
		ds = Xml.read(Process.class, Xml.toString(ds));
		EpdIndicatorResult.writeClean(ds, results);
		ds = Xml.read(Process.class, Xml.toString(ds));
		results = EpdIndicatorResult.allOf(ds);

		checkResult(
			results.get(0),
			"MJ",
			Map.of(
				"A1-A3", 89.0,
				"A4/Sc1", 16.0,
				"A4/Sc2", 9.0,
				"C3/S1", 0.5,
				"C3/S2", 0.3));

		checkResult(
			results.get(1),
			"kg",
			Map.of(
				"A1-A3", 7.0,
				"A4/Sc1", 2.0,
				"A4/Sc2", 1.0,
				"C3/S1", 3.0,
				"C3/S2", 1.2));

		checkResult(
			results.get(2),
			"kg CO2-Äqv.",
			Map.of(
				"A1-A3", 42.0,
				"A4/Sc1", 3.0,
				"A4/Sc2", 2.0,
				"C3/S1", 22.0,
				"C3/S2", 7.0));

		checkResult(
			results.get(3),
			"kg SO2-Äqv.",
			Map.of(
				"A1-A3", 0.0004,
				"A4/Sc1", 0.0001,
				"A4/Sc2", 0.0002,
				"C3/S1", 0.8,
				"C3/S2", 0.1));

	}

	private void checkResult(
		EpdIndicatorResult r, String unit, Map<String, Double> results
	) {
		assertEquals(unit, LangString.getDefault(r.unitGroup().getName()));
		assertEquals(r.hasImpactIndicator(), unit.contains("Ä"));
		int c = 0;
		for (var a : r.values()) {
			var key = a.getModule();
			if (a.getScenario() != null) {
				key += "/" + a.getScenario();
			}
			assertEquals(results.get(key), a.getAmount(), 1e-16);
			c++;
		}
		assertEquals(results.size(), c);
	}

}
