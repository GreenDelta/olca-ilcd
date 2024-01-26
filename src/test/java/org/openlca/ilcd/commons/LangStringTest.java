package org.openlca.ilcd.commons;

import jakarta.xml.bind.JAXB;
import org.junit.Test;

import java.io.StringWriter;
import java.util.List;

public class LangStringTest {

	@Test
	public void testAdapter() {
		var goal = new CommissionerAndGoal();
		goal.withProject().addAll(List.of(
			LangString.of("A project", "en"),
			LangString.of("Ein project", "de")
		));

		goal.withIntendedApplications().addAll(List.of(
			LangString.of("for testing", "en"),
			LangString.of("zum Testen", "de")
		));

		var xml = new StringWriter();
		JAXB.marshal(goal, xml);
		System.out.println(xml);
	}

}
