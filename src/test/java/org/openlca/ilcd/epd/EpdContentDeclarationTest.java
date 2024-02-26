package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.epd.EpdContentComponent;
import org.openlca.ilcd.processes.epd.EpdContentDeclaration;
import org.openlca.ilcd.processes.epd.EpdContentElement;
import org.openlca.ilcd.processes.epd.EpdContentMaterial;
import org.openlca.ilcd.processes.epd.EpdContentSubstance;
import org.openlca.ilcd.processes.epd.EpdInnerContentElement;
import org.openlca.ilcd.util.Epds;

import java.util.Objects;

public class EpdContentDeclarationTest {

	private EpdContentDeclaration dec;

	@Before
	public void setup() {
		var ds = Tests.read(Process.class, "sdk_sample_epd.xml");
		dec = Epds.getContentDeclaration(ds);
		Objects.requireNonNull(dec);
	}

	@Test
	public void testRootComponent() {
		var expected = new EpdContentComponent();
		expected.withName()
			.add(LangString.of("wooden panel"));
		expected.withWeightPerc().withValue(100d);
		check(expected, dec.getElements().stream()
			.filter(e -> LangString.getFirst(e.getName()).equals("wooden panel"))
			.findAny()
			.orElseThrow());
	}

	@Test
	public void testInnerMaterials() {
		var comp = dec.getElements().stream()
			.filter(e -> e instanceof EpdContentComponent)
			.map(EpdContentComponent.class::cast)
			.findAny()
			.orElseThrow();

		var mat1 = new EpdContentMaterial()
			.withGuid("00000000-0000-0000-0000-000000000000")
			.withRenewable(100d)
			.withRecycled(0d)
			.withRecyclable(0d);
		mat1.withName()
			.add(LangString.of("Spruce"));
		mat1.withWeightPerc()
			.withMin(97d)
			.withMax(99d);
		mat1.withMass().withValue(0.99);
		check(mat1, comp.getElements().stream()
			.filter(m -> LangString.getFirst(m.getName()).equals("Spruce"))
			.findAny()
			.orElseThrow());

		var mat2 = new EpdContentMaterial()
			.withCasNumber("000050-00-0")
			.withEcNumber("200-001-8")
			.withRenewable(5d)
			.withRecycled(40d)
			.withRecyclable(0d);
		mat2.withName()
			.add(LangString.of("MUF adhesive"));
		mat2.withWeightPerc()
			.withMin(1d)
			.withMax(3d);
		mat2.withMass()
			.withMin(0.0005)
			.withMax(0.01);
		check(mat2, comp.getElements().stream()
			.filter(m -> LangString.getFirst(m.getName()).equals("MUF adhesive"))
			.findAny()
			.orElseThrow());
	}

	@Test
	public void testPackagingMaterials() {

		var mat1 = new EpdContentMaterial()
			.withPackaging(true)
			.withCasNumber("032131-17-2")
			.withRenewable(100d)
			.withRecycled(50d)
			.withRecyclable(100d);
		mat1.withName()
			.add(LangString.of("Straps, nylon"));
		mat1.withWeightPerc().withMax(0.03d);
		mat1.withMass().withMax(0.11);
		check(mat1, dec.getElements().stream()
			.filter(m -> LangString.getFirst(m.getName()).equals("Straps, nylon"))
			.findAny()
			.orElseThrow());

		var mat2 = new EpdContentMaterial()
			.withPackaging(true)
			.withCasNumber("009002-88-4")
			.withRenewable(100d)
			.withRecycled(0d)
			.withRecyclable(100d);
		mat2.withName()
			.add(LangString.of("Clingwrap, polyeten"));
		mat2.withWeightPerc().withValue(0.3);
		mat2.withMass().withValue(1.1);
		check(mat2, dec.getElements().stream()
			.filter(m -> LangString.getFirst(m.getName()).equals("Clingwrap, polyeten"))
			.findAny()
			.orElseThrow());
	}

	@Test
	public void testSubstance() {
		var mat = dec.getElements().stream()
			.filter(e -> LangString.getFirst(e.getName()).equals("Straps, nylon"))
			.map(EpdContentMaterial.class::cast)
			.findAny()
			.orElseThrow();
		assertEquals(1, mat.getSubstances().size());

		var sub = new EpdContentSubstance()
			.withPackaging(true)
			.withCasNumber("3687-45-4")
			.withEcNumber("222-980-0");
		sub.withName()
			.add(LangString.of("(Z)-octadec-9-enyl oleate"));
		sub.withWeightPerc().withMax(0.0001);
		check(sub, mat.getSubstances().get(0));
	}

	private void check(EpdContentElement<?> expected, EpdContentElement<?> elem) {
		assertEquals(expected.getClass(), elem.getClass());
		assertEquals(
			LangString.getFirst(expected.getName()),
			LangString.getFirst(elem.getName()));

		if (expected.getWeightPerc() == null) {
			assertNull(elem.getWeightPerc());
		} else {
			assertEquals(
				expected.getWeightPerc().getValue(),
				elem.getWeightPerc().getValue());
			assertEquals(
				expected.getWeightPerc().getMin(),
				elem.getWeightPerc().getMin());
			assertEquals(
				expected.getWeightPerc().getMax(),
				elem.getWeightPerc().getMax());
		}

		if (expected.getMass() == null) {
			assertNull(elem.getMass());
		} else {
			assertEquals(
				expected.getMass().getValue(),
				elem.getMass().getValue());
			assertEquals(
				expected.getMass().getMin(),
				elem.getMass().getMin());
			assertEquals(
				expected.getMass().getMax(),
				elem.getMass().getMax());
		}

		if (expected instanceof EpdInnerContentElement<?> exp) {
			var mat = (EpdInnerContentElement<?>) elem;
			assertEquals(exp.getGuid(), mat.getGuid());
			assertEquals(exp.getRenewable(), mat.getRenewable());
			assertEquals(exp.getRecycled(), mat.getRecycled());
			assertEquals(exp.getRecyclable(), mat.getRecyclable());
			assertEquals(exp.getCasNumber(), mat.getCasNumber());
			assertEquals(exp.getEcNumber(), mat.getEcNumber());
			assertEquals(exp.getPackaging(), mat.getPackaging());
		}
	}
}
