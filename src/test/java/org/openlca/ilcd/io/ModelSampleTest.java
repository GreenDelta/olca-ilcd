package org.openlca.ilcd.io;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.models.Model;

import jakarta.xml.bind.JAXB;
import org.openlca.ilcd.util.Models;

public class ModelSampleTest {

	private Model model;

	@Before
	public void setup() {
		model = Tests.read(Model.class, "eilcd_sample_model.xml");
	}

	@Test
	public void testDataSetInfo() {
		assertEquals("10062015-184a-41b8-8fa6-49e999cbd101", Models.getUUID(model));
	}

}
