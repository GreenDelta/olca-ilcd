package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.util.Flows;

public class EpdFlowExtensionTest {

	private final Flow ds = Tests.read(Flow.class, "sdk_sample_epd_flow.xml");

	@Test
	public void testMatML() {
		var ext = ds.getFlowInfo().getDataSetInfo().getEpdExtension();
		assertNotNull(ext.getMaterialDoc());

		// TODO test the structure
	}

	@Test
	public void testNoExtension() {
		var flow = Tests.read(Flow.class, "sdk_sample_flow.xml");
		var info = Flows.getDataSetInfo(flow);
		assertNotNull(info);
		assertNull(info.getEpdExtension());
	}

}
