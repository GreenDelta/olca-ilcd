package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TechnologyType", propOrder = {
	"applicability",
	"specifications",
	"other"
})
public class Technology implements Copyable<Technology> {

	@FreeText
	@XmlElement(name = "technologicalApplicability")
	private List<LangString> applicability;

	@XmlElement(name = "referenceToTechnicalSpecification")
	private List<Ref> specifications;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	// region getters

	public List<LangString> getApplicability() {
		return applicability != null ? applicability : Collections.emptyList();
	}

	public List<Ref> getSpecifications() {
		return specifications != null ? specifications : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	// endregion

	// region setters

	public Technology withApplicability(List<LangString> applicability) {
		this.applicability = applicability;
		return this;
	}

	public Technology withSpecifications(List<Ref> specifications) {
		this.specifications = specifications;
		return this;
	}

	public Technology withOther(Other other) {
		this.other = other;
		return this;
	}

	public List<LangString> withApplicability() {
		if (applicability == null) {
			applicability = new ArrayList<>();
		}
		return applicability;
	}

	public List<Ref> withSpecifications() {
		if (specifications == null) {
			specifications = new ArrayList<>();
		}
		return specifications;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	// endregion

	@Override
	public Technology copy() {
		var copy = new Technology();
		Val.copy(applicability, copy::withApplicability);
		Val.copy(specifications, copy::withSpecifications);
		Val.copy(other, copy::withOther);
		return copy;
	}

}
