package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TechnologyType", propOrder = {
		"description",
		"includedProcesses",
		"applicability",
		"pictogram",
		"pictures",
		"other"
})
public class Technology implements Copyable<Technology> {

	@FreeText
	@XmlElement(name = "technologyDescriptionAndIncludedProcesses")
	public final List<LangString> description = new ArrayList<>();

	@XmlElement(name = "referenceToIncludedProcesses")
	public final List<Ref> includedProcesses = new ArrayList<>();

	@FreeText
	@XmlElement(name = "technologicalApplicability")
	public final List<LangString> applicability = new ArrayList<>();

	@XmlElement(name = "referenceToTechnologyPictogramme")
	public Ref pictogram;

	@XmlElement(name = "referenceToTechnologyFlowDiagrammOrPicture")
	public final List<Ref> pictures = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Technology copy() {
		var clone = new Technology();
		LangString.copy(description, clone.description);
		Ref.copy(includedProcesses, clone.includedProcesses);
		LangString.copy(applicability, clone.applicability);
		if (pictogram != null)
			clone.pictogram = pictogram.copy();
		Ref.copy(pictures, clone.pictures);
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
