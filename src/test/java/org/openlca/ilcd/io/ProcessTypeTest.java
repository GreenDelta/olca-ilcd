package org.openlca.ilcd.io;

import org.junit.Assert;
import org.junit.Test;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.QuantitativeReferenceType;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.descriptors.ProcessDescriptor;
import org.openlca.ilcd.processes.Process;

import java.util.function.Consumer;

public class ProcessTypeTest {

	@Test
	public void testReferenceType() {
		with(p -> {
			Assert.assertEquals(QuantitativeReferenceType.REFERENCE_FLOWS,
				p.processInfo.quantitativeReference.type);
		});
	}

	@Test
	public void testProcessType() {
		with(p -> {
			Assert.assertEquals(ProcessType.UNIT_PROCESS,
				p.modelling.inventoryMethod.processType);
		});
	}

	@Test
	public void testDescriptorType() {
		var list = Tests.read(DescriptorList.class, "sapi_sample_process_list.xml");
		var p = (ProcessDescriptor) list.descriptors.get(0);
		Assert.assertEquals(ProcessType.LCI_RESULT, p.type);
	}

	private void with(Consumer<Process> fn) {
		var p = Tests.read(Process.class, "sdk_sample_process.xml");
		fn.accept(p);
	}

}
