package org.openlca.ilcd.commons;

import jakarta.xml.bind.JAXB;
import org.junit.Test;

import java.io.StringWriter;

public class LangString2Test {

	@Test
	public void testAdapter() {
		var goal = new CommissionerAndGoal();
		goal.withProject()
			.putDefault("A project")
			.put("de", "Ein Projekt");
		goal.withIntendedApplications()
			.putDefault("for testing")
			.put("de", "zum Testen");

		var xml = new StringWriter();
		JAXB.marshal(goal, xml);
		System.out.println(xml);

	}

}
