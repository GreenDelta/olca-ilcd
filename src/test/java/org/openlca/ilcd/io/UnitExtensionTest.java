package org.openlca.ilcd.io;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;
import org.openlca.ilcd.units.Unit;
import org.openlca.ilcd.util.UnitExtension;

import jakarta.xml.bind.JAXB;

import static org.junit.Assert.*;

public class UnitExtensionTest {

	@Test
	public void testEmpty() {
		Unit unit = io(createUnit());
		UnitExtension extension = new UnitExtension(unit);
		assertFalse(extension.isValid());
		assertNull(extension.getUnitId());
	}

	@Test
	public void testExtension() {
		Unit unit = createUnit();
		UnitExtension extension = new UnitExtension(unit);
		extension.setUnitId("00-12-4-5");
		Unit alias = io(unit);
		assertNotSame(unit, alias);
		extension = new UnitExtension(alias);
		assertTrue(extension.isValid());
		assertEquals("00-12-4-5", extension.getUnitId());
	}

	private Unit createUnit() {
		return new Unit()
			.withId(1)
			.withFactor(42d)
			.withName("kg");
	}

	private Unit io(Unit unit) {
		StringWriter writer = new StringWriter();
		JAXB.marshal(unit, writer);
		writer.flush();
		String xml = writer.toString();
		StringReader reader = new StringReader(xml);
		return JAXB.unmarshal(reader, Unit.class);
	}

}
