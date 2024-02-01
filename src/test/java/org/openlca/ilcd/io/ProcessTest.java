package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.processes.DataSetInfo;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.Processes;

import java.util.List;

import static org.junit.Assert.*;

public class ProcessTest {

	private Process ds;
	private DataSetInfo info;

	@Before
	public void setup() throws Exception {
		ds = Tests.read(Process.class, "process.xml");
		info = Processes.getDataSetInfo(ds);
		assertNotNull(info);
	}

	@Test
	public void testUUID() {
		assertEquals("76d6aaa4-37e2-40b2-994c-03292b600074", info.getUUID());
	}

	@Test
	public void testName() {
		assertEquals("Acrylonitrile-Butadiene-Styrene granulate "
				+ "(ABS), production mix, at plant",
			Processes.getFullName(ds, "en"));
	}

	@Test
	public void testSynonyms() {
		assertEquals(
			"Acrylonitrile-butadiene-styrene copolymer; "
				+ "Styrene, acrylonitrile, butadiene polymer; 2-Propenenitrile, "
				+ "polymer with 1,3-butadiene and ethenylbenzene; Acrylonitrile, "
				+ "polymer with 1,3-butadiene and styrene",
			LangString.getFirst(info.getSynonyms()));
	}

	@Test
	public void testCategoryPath() {
		String[] path = Categories.getPath(ds);
		assertEquals(path.length, 2);
		assertEquals("Materials production", path[0]);
		assertEquals("Plastics", path[1]);
	}

	@Test
	public void testGetTime() {
		var time = Processes.getTime(ds);
		assertNotNull(time);
		assertEquals(1996, time.getReferenceYear().intValue());
	}

	@Test
	public void testGetGeography() {
		var geography = Processes.getGeography(ds);
		assertNotNull(geography);
		assertEquals("RER", geography.getLocation().getCode());
	}

	@Test
	public void testGetReferenceFlowIds() {
		List<Integer> refs = Processes.getReferenceFlows(ds);
		assertEquals(1, refs.size());
		assertEquals(56, refs.get(0).intValue());
	}

	@Test
	public void testGetProcessType() {
		assertEquals(
			ProcessType.PARTLY_TERMINATED_SYSTEM,
			Processes.getProcessType(ds));
	}

	@Test
	public void testGetExchanges() {
		assertTrue(ds.getExchanges().size() > 56);
	}

}
