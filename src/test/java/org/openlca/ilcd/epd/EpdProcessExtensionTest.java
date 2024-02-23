package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.epd.EpdScenario;
import org.openlca.ilcd.util.Epds;
import org.openlca.ilcd.util.Processes;

import java.util.function.Function;

public class EpdProcessExtensionTest {

	private final Process ds = Tests.read(Process.class, "sdk_sample_epd.xml");

	@Test
	public void testSafetyMargins() {
		var margins = Epds.getSafetyMargins(ds);
		assertNotNull(margins);
		assertEquals(10, margins.getValue(), 1e-16);
		assertEquals(
			"Reasons for choice of safety margins",
			LangString.getFirst(margins.getDescription())
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
			LangString.getFirst(s.apply("S1").getDescription()));
	}

	@Test
	public void testVersion() {
		var p = new Process()
			.withEpdVersion("1.2");
		p.withModelling()
			.withInventoryMethod()
			.withProcessType(ProcessType.EPD);

		var copy = Xml.read(Process.class, Xml.toString(p));
		assertEquals("1.2", copy.getEpdVersion());
		assertEquals(ProcessType.EPD, Processes.getProcessType(p));
	}


}
