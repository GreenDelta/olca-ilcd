package org.openlca.ilcd.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.function.Consumer;

import org.junit.Test;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.io.ProcessSampleTest;

public class RefsTest {

	@Test
	public void testFetchMethodRef() {
		with("sdk_sample_lciamethod.xml", ref -> {
			assertEquals(DataSetType.IMPACT_METHOD, ref.getType());
			assertEquals("name0", LangString.getVal(ref.getName(), "en"));
			assertEquals("name1", LangString.getVal(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchProcessRef() {
		with("sdk_sample_process.xml", ref -> {
			assertEquals(DataSetType.PROCESS, ref.getType());
			assertEquals("baseName0", LangString.getVal(ref.getName(), "en"));
			assertEquals("baseName1", LangString.getVal(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchSourceRef() {
		with("sdk_sample_source.xml", ref -> {
			assertEquals(DataSetType.SOURCE, ref.getType());
			assertEquals("shortName0", LangString.getVal(ref.getName(), "en"));
			assertEquals("shortName1", LangString.getVal(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchContactRef() {
		with("sdk_sample_contact.xml", ref -> {
			assertEquals(DataSetType.CONTACT, ref.getType());
			assertEquals("name0", LangString.getVal(ref.getName(), "en"));
			assertEquals("name1", LangString.getVal(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchFlowRef() {
		with("sdk_sample_flow.xml", ref -> {
			assertEquals(DataSetType.FLOW, ref.getType());
			assertEquals("baseName0", LangString.getVal(ref.getName(), "en"));
			assertEquals("baseName1", LangString.getVal(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchFlowPropertyRef() {
		with("sdk_sample_flowproperty.xml", ref -> {
			assertEquals(DataSetType.FLOW_PROPERTY, ref.getType());
			assertEquals("name0", LangString.getVal(ref.getName(), "en"));
			assertEquals("name1", LangString.getVal(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchUnitGroupRef() {
		with("sdk_sample_unitgroup.xml", ref -> {
			assertEquals(DataSetType.UNIT_GROUP, ref.getType());
			assertEquals("name0", LangString.getVal(ref.getName(), "en"));
			assertEquals("name1", LangString.getVal(ref.getName(), "de"));
		});
	}

	private void with(String file, Consumer<Ref> fn) {
		try (InputStream is = ProcessSampleTest.class
				.getResourceAsStream(file)) {
			Ref ref = Refs.fetch(is);
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertTrue(ref.getUri().startsWith("http://www.ilcd-network.org/data/"));
			fn.accept(ref);
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}

}
