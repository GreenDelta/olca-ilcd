package org.openlca.ilcd.util;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.UUID;
import java.util.function.Consumer;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.units.Unit;
import org.openlca.ilcd.units.UnitGroup;

public class RefsTest {

	@Test
	public void testFetchMethodRef() {
		with("sdk_sample_lciamethod.xml", ref -> {
			assertEquals(DataSetType.IMPACT_METHOD, ref.getType());
			assertEquals("name0", LangString.get(ref.getName(), "en"));
			assertEquals("name1", LangString.get(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchProcessRef() {
		with("sdk_sample_process.xml", ref -> {
			assertEquals(DataSetType.PROCESS, ref.getType());
			assertEquals("baseName0", LangString.get(ref.getName(), "en"));
			assertEquals("baseName1", LangString.get(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchSourceRef() {
		with("sdk_sample_source.xml", ref -> {
			assertEquals(DataSetType.SOURCE, ref.getType());
			assertEquals("shortName0", LangString.get(ref.getName(), "en"));
			assertEquals("shortName1", LangString.get(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchContactRef() {
		with("sdk_sample_contact.xml", ref -> {
			assertEquals(DataSetType.CONTACT, ref.getType());
			assertEquals("name0", LangString.get(ref.getName(), "en"));
			assertEquals("name1", LangString.get(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchFlowRef() {
		with("sdk_sample_flow.xml", ref -> {
			assertEquals(DataSetType.FLOW, ref.getType());
			assertEquals("baseName0", LangString.get(ref.getName(), "en"));
			assertEquals("baseName1", LangString.get(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchFlowPropertyRef() {
		with("sdk_sample_flowproperty.xml", ref -> {
			assertEquals(DataSetType.FLOW_PROPERTY, ref.getType());
			assertEquals("name0", LangString.get(ref.getName(), "en"));
			assertEquals("name1", LangString.get(ref.getName(), "de"));
		});
	}

	@Test
	public void testFetchUnitGroupRef() {
		with("sdk_sample_unitgroup.xml", ref -> {
			assertEquals(DataSetType.UNIT_GROUP, ref.getType());
			assertEquals("name0", LangString.get(ref.getName(), "en"));
			assertEquals("name1", LangString.get(ref.getName(), "de"));
		});
	}

	@Test
	public void testNoUnitNames() {
		var g = new UnitGroup();
		UnitGroups.withDataSetInfo(g)
			.withUUID(UUID.randomUUID().toString())
			.withName().add(LangString.of("Mass units", "en"));
		g.withUnits().add(
			new Unit().withId(1).withName("kg"));
		g.withUnits().add(
			new Unit().withId(2).withName("t"));

		var xml = Xml.toBytes(g);
		var ref = Refs.fetch(new ByteArrayInputStream(xml));
		assertNotNull(ref);
		assertEquals(UnitGroups.getUUID(g), ref.getUUID());
		assertEquals("Mass units", LangString.getDefault(ref.getName()));
		assertEquals(1, ref.getName().size());
	}

	private void with(String file, Consumer<Ref> fn) {
		Tests.withResources(file, stream -> {
			Ref ref = Refs.fetch(stream);
			assertNotNull(ref);
			assertEquals("00000000-0000-0000-0000-000000000000", ref.getUUID());
			assertEquals("00.00", ref.getVersion());
			assertTrue(ref.getUri().startsWith("http://www.ilcd-network.org/data/"));
			fn.accept(ref);
		});
	}

}
