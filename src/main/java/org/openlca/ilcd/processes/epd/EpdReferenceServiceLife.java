package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdReferenceServiceLife implements Copyable<EpdReferenceServiceLife> {

	@XmlAttribute(name = "years", namespace = Vocab.EPD_2024)
	private Double years;

	@XmlElement(name = "useConditionFactor", namespace = Vocab.EPD_2024)
	private List<EpdUseConditionFactor> useConditionFactors;

	@XmlElement(name = "referenceToStandard", namespace = Vocab.EPD_2024)
	private List<Ref> referenceToStandards;

	@XmlElement(name = "referenceToUseConditionsDocumentation", namespace = Vocab.EPD_2024)
	private List<Ref> referenceToUseConditionsDocumentations;

	@XmlElement(name = "comment", namespace = Vocab.EPD_2024)
	private List<LangString> comments;

	// region getters

	public Double getYears() {
		return years;
	}

	public List<EpdUseConditionFactor> getUseConditionFactors() {
		return useConditionFactors != null ? useConditionFactors : List.of();
	}

	public List<Ref> getReferenceToStandards() {
		return referenceToStandards != null ? referenceToStandards : List.of();
	}

	public List<Ref> getReferenceToUseConditionsDocumentations() {
		return referenceToUseConditionsDocumentations != null ? referenceToUseConditionsDocumentations : List.of();
	}

	public List<LangString> getComments() {
		return comments != null ? comments : List.of();
	}

	// endregion

	// region setters

	public EpdReferenceServiceLife withYears(Double years) {
		this.years = years;
		return this;
	}

	public EpdReferenceServiceLife withUseConditionFactors(List<EpdUseConditionFactor> useConditionFactors) {
		this.useConditionFactors = useConditionFactors;
		return this;
	}

	public EpdReferenceServiceLife withReferenceToStandards(List<Ref> referenceToStandards) {
		this.referenceToStandards = referenceToStandards;
		return this;
	}

	public EpdReferenceServiceLife withReferenceToUseConditionsDocumentations(List<Ref> referenceToUseConditionsDocumentations) {
		this.referenceToUseConditionsDocumentations = referenceToUseConditionsDocumentations;
		return this;
	}

	public EpdReferenceServiceLife withComments(List<LangString> comments) {
		this.comments = comments;
		return this;
	}

	public List<EpdUseConditionFactor> withUseConditionFactors() {
		if (useConditionFactors == null) {
			useConditionFactors = new ArrayList<>();
		}
		return useConditionFactors;
	}

	public List<Ref> withReferenceToStandards() {
		if (referenceToStandards == null) {
			referenceToStandards = new ArrayList<>();
		}
		return referenceToStandards;
	}

	public List<Ref> withReferenceToUseConditionsDocumentations() {
		if (referenceToUseConditionsDocumentations == null) {
			referenceToUseConditionsDocumentations = new ArrayList<>();
		}
		return referenceToUseConditionsDocumentations;
	}

	public List<LangString> withComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	// endregion

	@Override
	public EpdReferenceServiceLife copy() {
		var copy = new EpdReferenceServiceLife();
		copy.withYears(years);
		Val.copy(useConditionFactors, copy::withUseConditionFactors);
		Val.copy(referenceToStandards, copy::withReferenceToStandards);
		Val.copy(referenceToUseConditionsDocumentations, copy::withReferenceToUseConditionsDocumentations);
		Val.copy(comments, copy::withComments);
		return copy;
	}
}
