package org.openlca.ilcd.epd;

import org.junit.Test;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Epds;

import javax.xml.namespace.QName;
import java.util.UUID;

import static org.junit.Assert.*;

public class FormatVersionTest {

	@Test
	public void testWriteFormatVersion() {
		var p = new Process();
		p	.withProcessInfo()
			.withDataSetInfo()
			.withUUID(UUID.randomUUID().toString());
		p.withOtherAttributes()
			.put(new QName(Vocab.EPD_2019, "epd-version"), "1.2");
		p = Xml.read(Process.class, Xml.toString(p));
		assertEquals("1.2", p.getEpdVersion());
	}
}
