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
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
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
	private List<LangString> description;

	@XmlElement(name = "referenceToIncludedProcesses")
	private List<Ref> includedProcesses;

	@FreeText
	@XmlElement(name = "technologicalApplicability")
	private List<LangString> applicability;

	@XmlElement(name = "referenceToTechnologyPictogramme")
	private Ref pictogram;

	@XmlElement(name = "referenceToTechnologyFlowDiagrammOrPicture")
	private List<Ref> pictures;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<LangString> getDescription() {
		return description != null ? description : Collections.emptyList();
	}

	public List<Ref> getIncludedProcesses() {
		return includedProcesses != null ? includedProcesses : Collections.emptyList();
	}

	public List<LangString> getApplicability() {
		return applicability != null ? applicability : Collections.emptyList();
	}

	public Ref getPictogram() {
		return pictogram;
	}

	public List<Ref> getPictures() {
		return pictures != null ? pictures : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Technology withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public Technology withIncludedProcesses(List<Ref> includedProcesses) {
		this.includedProcesses = includedProcesses;
		return this;
	}

	public Technology withApplicability(List<LangString> applicability) {
		this.applicability = applicability;
		return this;
	}

	public Technology withPictogram(Ref pictogram) {
		this.pictogram = pictogram;
		return this;
	}

	public Technology withPictures(List<Ref> pictures) {
		this.pictures = pictures;
		return this;
	}

	public Technology withOther(Other other) {
		this.other = other;
		return this;
	}

	public Technology withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	public List<Ref> withIncludedProcesses() {
		if (includedProcesses == null) {
			includedProcesses = new ArrayList<>();
		}
		return includedProcesses;
	}

	public List<LangString> withApplicability() {
		if (applicability == null) {
			applicability = new ArrayList<>();
		}
		return applicability;
	}

	public Ref withPictogram() {
		if (pictogram == null) {
			pictogram = new Ref();
		}
		return pictogram;
	}

	public List<Ref> withPictures() {
		if (pictures == null) {
			pictures = new ArrayList<>();
		}
		return pictures;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Technology copy() {
		var copy = new Technology();
		Val.copy(description, copy::withDescription);
		Val.copy(includedProcesses, copy::withIncludedProcesses);
		Val.copy(applicability, copy::withApplicability);
		Val.copy(pictogram, copy::withPictogram);
		Val.copy(pictures, copy::withPictures);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
