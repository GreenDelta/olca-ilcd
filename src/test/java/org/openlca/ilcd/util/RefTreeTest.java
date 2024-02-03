package org.openlca.ilcd.util;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.processes.Exchange;
import org.openlca.ilcd.processes.Process;

import static org.junit.Assert.*;

public class RefTreeTest {

	@Test
	public void testNoRef() {
		Process p = new Process();
		RefTree tree = RefTree.create(p);
		assertTrue(tree.root.childs.isEmpty());
		assertTrue(tree.getRefs().isEmpty());
	}

	@Test
	public void testFlowRef() {
		var e = new Exchange();
		e.withFlow().withUUID("123");
		var p = new Process();
		p.withExchanges().add(e);
		RefTree tree = RefTree.create(p);
		assertEquals(1, tree.root.childs.size());
		Ref ref = tree.getRefs().get(0);
		assertEquals("123", ref.getUUID());
	}

	@Test
	public void testProcessSample() {
		Process p = Tests.read(Process.class, "sdk_sample_process.xml");
		RefTree tree = RefTree.create(p);
		assertTrue(tree.getRefs().size() > 2);
		int lciaResultCount = 0;
		for (Ref ref : tree.getRefs()) {
			if (ref.getType() == DataSetType.IMPACT_METHOD) {
				assertTrue(ref.isValid());
				lciaResultCount++;
			}
		}
		assertEquals(1, lciaResultCount); // two references but with same uuid
		// and version
	}

	@Test
	public void testFlowSample() {
		Flow p = Tests.read(Flow.class, "sdk_sample_flow.xml");
		RefTree tree = RefTree.create(p);
		assertTrue(tree.getRefs().size() > 2);
	}

}
