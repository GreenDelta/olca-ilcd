package org.openlca.ilcd.io;

import org.junit.Assert;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.processes.Exchange;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Processes;

public class ProcessReadWriteTest {

	@Test
	public void testReadWrite() {
		Process process = new Process();
		setNameAndComment(process);
		createExchange(process);
		byte[] bytes = Xml.toBytes(process);
		process = Xml.read(Process.class, bytes);
		Assert.assertEquals("process name",
			Processes.getBaseName(process).get(0).getValue());
		Assert.assertEquals("process description",
			process.getProcessInfo().getDataSetInfo().getComment().get(0).getValue());
		Assert.assertEquals(1, process.getExchanges().size());
	}

	private void setNameAndComment(Process process) {
		var info = process.withProcessInfo().withDataSetInfo();
		info.withProcessName()
			.withBaseName()
			.add(LangString.of("process name", "en"));
		info.withComment()
			.add(LangString.of("process description", "en"));
	}

	private void createExchange(Process process) {
		process.withExchanges()
			.add(new Exchange().withMeanAmount(1.5));
	}

}
