package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.processes.Process;

import java.io.IOException;

public class MetaDataOnlyStoreTest {

	private final String UID = "00000000-0000-0000-0000-000000000000";
	private DataStore store;

	@Before
	public void setup() {
		store = new MemDataStore();
		store.put(Tests.read(Process.class, "sdk_sample_process.xml"));
		store.put(Tests.read(ImpactMethod.class, "sdk_sample_lciamethod.xml"));
	}

	@After
	public void cleanup() throws IOException {
		store.close();
	}

	@Test
	public void testProcess() {
		var p = store.get(Process.class, UID);
		assertEquals(2, p.getExchanges().size());
		var meta = MetaDataOnlyStore.of(store);
		var pi = meta.get(Process.class, UID);
		assertTrue(pi.getExchanges().isEmpty());
		assertTrue(pi.getImpactResults().isEmpty());
		assertTrue(
			meta.iterator(Process.class)
				.next()
				.getExchanges()
				.isEmpty());
	}

	@Test
	public void testKeepRefFlows() {
		var meta = MetaDataOnlyStore.of(store)
			.keepReferenceFlows(true);
		var pi = meta.get(Process.class, UID);
		assertTrue(pi.getImpactResults().isEmpty());
		assertEquals(2, pi.getExchanges().size());
	}

	@Test
	public void testImpactMethod() {
		var i = store.get(ImpactMethod.class, UID);
		assertEquals(2, i.getFactors().size());
		var meta = MetaDataOnlyStore.of(store);
		var ii = meta.get(ImpactMethod.class, UID);
		assertTrue(ii.getFactors().isEmpty());
		assertTrue(
			meta.iterator(ImpactMethod.class)
				.next()
				.getFactors()
				.isEmpty());
	}

}
