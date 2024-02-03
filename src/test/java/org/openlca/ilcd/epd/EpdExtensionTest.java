package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Processes;

public class EpdExtensionTest {

	private final Process ds = Tests.read(Process.class, "sdk_sample_epd.xml");

	@Test
	public void testSafetyMargins() {
		var info = Processes.getDataSetInfo(ds);
		assertNotNull(info);
		var m = info.getEpdExtension().getSafetyMargins();
		assertEquals(10, m.getMargins(), 1e-16);
		assertEquals(
			"Reasons for choice of safety margins",
			LangString.getFirst(m.getDescription())
		);
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
