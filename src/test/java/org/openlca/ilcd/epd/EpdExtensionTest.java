package org.openlca.ilcd.epd;

import org.junit.Assert;
import org.junit.Test;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Processes;

public class EpdExtensionTest {

	@Test
	public void testVersion() {
		var p = new Process()
			.withEpdVersion("1.2");
		p.withModelling()
			.withInventoryMethod()
			.withProcessType(ProcessType.EPD);

		var copy = Xml.read(Process.class, Xml.toString(p));
		Assert.assertEquals("1.2", copy.getEpdVersion());
		Assert.assertEquals(ProcessType.EPD, Processes.getProcessType(p));
	}


}
