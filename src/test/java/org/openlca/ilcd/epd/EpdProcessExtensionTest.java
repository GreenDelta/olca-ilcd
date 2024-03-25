package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.function.Function;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.epd.EpdResultExtension;
import org.openlca.ilcd.processes.epd.EpdScenario;
import org.openlca.ilcd.processes.epd.EpdSubType;
import org.openlca.ilcd.util.Epds;

public class EpdProcessExtensionTest {

	private final Process ds = Tests.read(Process.class, "sdk_sample_epd.xml");

	@Test
	public void testSafetyMargins() {
		var margins = Epds.getSafetyMargins(ds);
		assertNotNull(margins);
		assertEquals(10, margins.getValue(), 1e-16);
		assertEquals(
			"Reasons for choice of safety margins",
			LangString.getDefault(margins.getDescription())
		);
	}

	@Test
	public void testScenarios() {
		var scenarios = Epds.getScenarios(ds);
		assertEquals(4, scenarios.size());
		Function<String, EpdScenario> s = name ->
			scenarios.stream()
				.filter(si -> si.getName().equals(name))
				.findAny()
				.orElseThrow();

		assertTrue(s.apply("Sc1").isDefaultScenario());
		assertFalse(s.apply("Sc2").isDefaultScenario());
		assertFalse(s.apply("S1").isDefaultScenario());
		assertTrue(s.apply("S2").isDefaultScenario());

		assertEquals("1", s.apply("Sc1").getGroup());
		assertEquals("1", s.apply("Sc2").getGroup());
		assertEquals("2", s.apply("S1").getGroup());
		assertEquals("2", s.apply("S2").getGroup());

		assertEquals(
			"description of scenario S1",
			LangString.getDefault(s.apply("S1").getDescription()));
	}

	@Test
	public void testVersion() {
		var epd = new Process()
			.withEpdVersion("1.2");
		Epds.withInventoryMethod(epd)
			.withProcessType(ProcessType.EPD);
		var copy = Xml.read(Process.class, Xml.toString(epd));
		assertEquals("1.2", copy.getEpdVersion());
		assertEquals(ProcessType.EPD, Epds.getProcessType(epd));
	}

	@Test
	public void testSubType() {
		assertEquals(EpdSubType.AVERAGE_DATASET, Epds.getSubType(ds));
	}

	@Test
	public void testPublicationDate() {
		var pubDate = Epds.getPublicationDate(ds);
		assertNotNull(pubDate);
		var fmt = new SimpleDateFormat("yyyy-MM-dd")
			.format(pubDate.toGregorianCalendar().getTime());
		assertEquals("2020-05-08", fmt);
	}

	@Test
	public void testOriginalEpdRef() {
		var ref = Epds.getOriginalEpds(ds).get(0);
		assertEquals("Wood Panel EPD", LangString.getDefault(ref.getName()));
	}

	@Test
	public void testPublisherRef() {
		var ref = Epds.getPublishers(ds).get(0);
		assertEquals(
			"Institut Bauen und Umwelt e. V.",
			LangString.getDefault(ref.getName()));
	}

	@Test
	public void testEpdResults() {
		checkResult(
			ds.getExchanges().get(1).getEpdExtension(),
			"MJ",
			Map.of(
				"A1-A3", 89.0,
				"A4/Sc1", 16.0,
				"A4/Sc2", 9.0,
				"C3/S1", 0.5,
				"C3/S2", 0.3));

		checkResult(
			ds.getExchanges().get(2).getEpdExtension(),
			"kg",
			Map.of(
				"A1-A3", 7.0,
				"A4/Sc1", 2.0,
				"A4/Sc2", 1.0,
				"C3/S1", 3.0,
				"C3/S2", 1.2));

		checkResult(
			ds.getImpactResults().get(0).getEpdExtension(),
			"kg CO2-Äqv.",
			Map.of(
				"A1-A3", 42.0,
				"A4/Sc1", 3.0,
				"A4/Sc2", 2.0,
				"C3/S1", 22.0,
				"C3/S2", 7.0));

		checkResult(
			ds.getImpactResults().get(1).getEpdExtension(),
			"kg SO2-Äqv.",
			Map.of(
				"A1-A3", 0.0004,
				"A4/Sc1", 0.0001,
				"A4/Sc2", 0.0002,
				"C3/S1", 0.8,
				"C3/S2", 0.1));
	}


	private void checkResult(
		EpdResultExtension e, String unit, Map<String, Double> results
	) {
		assertEquals(unit, LangString.getDefault(e.getUnitGroup().getName()));
		int c = 0;
		for (var v : e.getValues()) {
			var key = v.getModule();
			if (v.getScenario() != null) {
				key += "/" + v.getScenario();
			}
			assertEquals(results.get(key), v.getAmount(), 1e-16);
			c++;
		}
		assertEquals(results.size(), c);
	}

}
