package org.openlca.ilcd.io;

import org.junit.Assert;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.processes.Process;

public class WrapperTest {

	@Test
	public void testExchanges() {
		var p = Tests.read(Process.class, "sdk_sample_process.xml");
		Assert.assertEquals(2, p.getExchanges().size());
		Assert.assertEquals(2, p.getImpactResults().size());
	}

}
