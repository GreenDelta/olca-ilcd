package org.openlca.ilcd.processes.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdServiceLife implements Copyable<EpdServiceLife> {

	@XmlAttribute(name = "years", namespace = Vocab.EPD_2024)
	private double years;

	@XmlElement(name = "useConditionFactor", namespace = Vocab.EPD_2024)
	private List<EpdConditionFactor> conditionFactors;

	@XmlElement(name = "referenceToStandard", namespace = Vocab.EPD_2024)
	private List<Ref> standards;

	@XmlElement(name = "referenceToUseConditionsDocumentation", namespace = Vocab.EPD_2024)
	private List<Ref> documentations;

	@XmlElement(name = "comment", namespace = Vocab.EPD_2024)
	private List<LangString> comments;

	// region getters

	public double getYears() {
		return years;
	}

	public List<EpdConditionFactor> getConditionFactors() {
		return conditionFactors != null ? conditionFactors : List.of();
	}

	public List<Ref> getStandards() {
		return standards != null ? standards : List.of();
	}

	public List<Ref> getDocumentations() {
		return documentations != null ? documentations : List.of();
	}

	public List<LangString> getComments() {
		return comments != null ? comments : List.of();
	}

	// endregion

	// region setters

	public EpdServiceLife withYears(double years) {
		this.years = years;
		return this;
	}

	public EpdServiceLife withConditionFactors(List<EpdConditionFactor> factors) {
		this.conditionFactors = factors;
		return this;
	}

	public EpdServiceLife withStandards(List<Ref> standards) {
		this.standards = standards;
		return this;
	}

	public EpdServiceLife withDocumentations(List<Ref> documentations) {
		this.documentations = documentations;
		return this;
	}

	public EpdServiceLife withComments(List<LangString> comments) {
		this.comments = comments;
		return this;
	}

	public List<EpdConditionFactor> withConditionFactors() {
		if (conditionFactors == null) {
			conditionFactors = new ArrayList<>();
		}
		return conditionFactors;
	}

	public List<Ref> withStandards() {
		if (standards == null) {
			standards = new ArrayList<>();
		}
		return standards;
	}

	public List<Ref> withDocumentations() {
		if (documentations == null) {
			documentations = new ArrayList<>();
		}
		return documentations;
	}

	public List<LangString> withComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	// endregion

	@Override
	public EpdServiceLife copy() {
		var copy = new EpdServiceLife();
		copy.withYears(years);
		Val.copy(conditionFactors, copy::withConditionFactors);
		Val.copy(standards, copy::withStandards);
		Val.copy(documentations, copy::withDocumentations);
		Val.copy(comments, copy::withComments);
		return copy;
	}
}
