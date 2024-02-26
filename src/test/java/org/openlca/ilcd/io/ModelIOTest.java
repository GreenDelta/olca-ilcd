package org.openlca.ilcd.io;

import jakarta.xml.bind.JAXB;
import org.junit.Test;
import org.openlca.ilcd.commons.Category;
import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.models.Connection;
import org.openlca.ilcd.models.DownstreamLink;
import org.openlca.ilcd.models.Group;
import org.openlca.ilcd.models.GroupRef;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.models.Parameter;
import org.openlca.ilcd.models.ProcessInstance;
import org.openlca.ilcd.util.Models;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.UUID;

import static org.junit.Assert.*;

public class ModelIOTest {

	@Test
	public void testSimpleModel() {
		var model = new Model();

		var info = model.withInfo().withDataSetInfo();
		info.withUUID(UUID.randomUUID().toString());
		info.withModelName().withBaseName().add(LangString.of("Example model", "en"));
		model.withAdminInfo().withPublication().withVersion("01.00.000");

		var classification = new Classification();
		var category = new Category()
			.withLevel(0)
			.withName("Life cycle models");
		classification.withCategories().add(category);
		info.withClassifications().add(classification);

		model.withInfo()
			.withQuantitativeReference()
			.withRefProcess(42);

		var group = new Group().withId(42);
		group.withName().add(LangString.of("Use phase", "en"));
		var tech = model.withInfo().withTechnology();
		tech.withGroups().add(group);

		var pi = new ProcessInstance();
		pi.withGroupRefs().add(
			new GroupRef().withGroupID(42));
		tech.withProcesses().add(pi);

		pi.withParameters().add(
			new Parameter()
				.withName("distance")
				.withValue(42.42));

		var con = new Connection()
			.withOutputFlow(UUID.randomUUID().toString());
		var link = new DownstreamLink()
			.withInputFlow(UUID.randomUUID().toString())
			.withProcess(42);
		con.withDownstreamLinks().add(link);
		pi.withConnections().add(con);

		StringWriter writer = new StringWriter();
		Xml.write(model, writer);
		StringReader reader = new StringReader(writer.toString());
		model = JAXB.unmarshal(reader, Model.class);

		assertEquals(1, Models.getProcesses(model).size());
	}

	@Test
	public void testOrigin() {
		Model m = new Model();
		assertNull(Models.getOrigin(m));
		Models.setOrigin(m, "openLCA");
		StringWriter writer = new StringWriter();
		JAXB.marshal(m, writer);
		StringReader reader = new StringReader(writer.toString());
		m = JAXB.unmarshal(reader, Model.class);
		assertEquals("openLCA", Models.getOrigin(m));
	}

}
