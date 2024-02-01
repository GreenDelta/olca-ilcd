package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.QuantitativeReferenceType;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.descriptors.ProcessDescriptor;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Processes;

import java.util.function.Consumer;

public class ProcessTypeTest {

	@Test
	public void testReferenceType() {
		with(p -> {
			var qRef = Processes.getQuantitativeReference(p);
			assertNotNull(qRef);
			assertEquals(QuantitativeReferenceType.REFERENCE_FLOWS, qRef.getType());
		});
	}

	@Test
	public void testProcessType() {
		with(p -> assertEquals(
			ProcessType.UNIT_PROCESS,
			Processes.getProcessType(p)));
	}

	@Test
	public void testDescriptorType() {
		var list = Tests.read(DescriptorList.class, "sapi_sample_process_list.xml");
		var p = (ProcessDescriptor) list.getDescriptors().get(0);
		assertEquals(ProcessType.LCI_RESULT, p.getProcessType());
	}

	private void with(Consumer<Process> fn) {
		var p = Tests.read(Process.class, "sdk_sample_process.xml");
		fn.accept(p);
	}

}
