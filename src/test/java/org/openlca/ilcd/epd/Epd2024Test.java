package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Epds;

public class Epd2024Test {

	private final Process ds = Tests.read(Process.class, "epd-v1.3-example.xml");

	@Test
	public void testProductIds() {
		var ids = Epds.getProductIds(ds);
		assertEquals(4, ids.size());

		var id1 = ids.getFirst();
		assertEquals("GTIN", id1.getType());
		assertEquals("3234567890126", id1.getValue());

		var id2 = ids.get(1);
		assertEquals("GTIN", id2.getType());
		assertEquals("3234567890132", id2.getValue());

		var id3 = ids.get(2);
		assertEquals("GTIN", id3.getType());
		assertEquals("3234567890288", id3.getValue());

		var id4 = ids.get(3);
		assertEquals("GMN", id4.getType());
		assertEquals("445922", id4.getValue());
	}

	@Test
	public void testReferenceServiceLife() {
		var rsl = Epds.getReferenceServiceLife(ds);
		assertNotNull(rsl);
		assertEquals(100.0, rsl.getYears(), 1e-16);
		assertEquals(7, rsl.getUseConditionFactors().size());
		assertEquals(1, rsl.getReferenceToStandards().size());
		assertEquals(1, rsl.getReferenceToUseConditionsDocumentations().size());

		var std = rsl.getReferenceToStandards().getFirst();
		assertEquals("c0016b33-8cf7-415c-ac6e-deba0d21440d", std.getUUID());
		assertEquals("EN15804+A2", LangString.getDefault(std.getName()));

		var comments = rsl.getComments();
		assertEquals(2, comments.size());
		assertEquals("The indicated service life requires installation and maintenance according to manufacturer instructions.",
			LangString.getDefault(comments));
	}

	@Test
	public void testReferenceServiceLifeFactors() {
		var rsl = Epds.getReferenceServiceLife(ds);
		var factors = rsl.getUseConditionFactors();

		var first = factors.getFirst();
		assertEquals("A - inherent quality", first.getFactorCategory());
		assertEquals(Integer.valueOf(1), first.getObjectSpecificGrade());
		assertEquals(Integer.valueOf(1), first.getReferenceGrade());
		assertEquals(1.0, first.getFactor(), 1e-16);

		var outdoor = factors.get(4);
		assertEquals("E - outdoor environment", outdoor.getFactorCategory());
		assertEquals(Integer.valueOf(2), outdoor.getObjectSpecificGrade());
		assertEquals(Integer.valueOf(1), outdoor.getReferenceGrade());
		assertEquals(0.9, outdoor.getFactor(), 1e-16);
		assertEquals(2, outdoor.getComments().size());
		assertEquals("Lots of rain but no wind", LangString.getDefault(outdoor.getComments()));
	}

	@Test
	public void testEstimatedServiceLife() {
		var esl = Epds.getEstimatedServiceLife(ds);
		assertNotNull(esl);
		assertEquals(8.0, esl.getYears(), 1e-16);
		assertEquals(7, esl.getUseConditionFactors().size());

		var std = esl.getReferenceToStandards().getFirst();
		assertEquals("ISO22057", LangString.getDefault(std.getName()));
	}

	@Test
	public void testEstimatedServiceLifeFactors() {
		var esl = Epds.getEstimatedServiceLife(ds);
		var factors = esl.getUseConditionFactors();

		var first = factors.getFirst();
		assertEquals("A - inherent quality", first.getFactorCategory());
		assertEquals(Integer.valueOf(5), first.getObjectSpecificGrade());
		assertEquals(Integer.valueOf(1), first.getReferenceGrade());
		assertEquals(0.7, first.getFactor(), 1e-16);
	}
}
