package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TechnologyType", propOrder = {
		"technologicalApplicability",
		"technicalSpecifications",
		"other"
})
public class Technology {

	@FreeText
	public List<LangString> technologicalApplicability;

	@XmlElement(name = "referenceToTechnicalSpecification")
	public List<Ref> technicalSpecifications;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

}
