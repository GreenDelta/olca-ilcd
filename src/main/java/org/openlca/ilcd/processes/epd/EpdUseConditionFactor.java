package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdUseConditionFactor implements Copyable<EpdUseConditionFactor> {

	@XmlAttribute(name = "factorCategory", namespace = Vocab.EPD_2024)
	private String factorCategory;

	@XmlAttribute(name = "objectSpecificGrade", namespace = Vocab.EPD_2024)
	private Integer objectSpecificGrade;

	@XmlAttribute(name = "referenceGrade", namespace = Vocab.EPD_2024)
	private Integer referenceGrade;

	@XmlAttribute(name = "factor", namespace = Vocab.EPD_2024)
	private Double factor;

	@XmlElement(name = "comment", namespace = Vocab.EPD_2024)
	private List<LangString> comments;

	// region getters

	public String getFactorCategory() {
		return factorCategory;
	}

	public Integer getObjectSpecificGrade() {
		return objectSpecificGrade;
	}

	public Integer getReferenceGrade() {
		return referenceGrade;
	}

	public Double getFactor() {
		return factor;
	}

	public List<LangString> getComments() {
		return comments != null ? comments : List.of();
	}

	// endregion

	// region setters

	public EpdUseConditionFactor withFactorCategory(String factorCategory) {
		this.factorCategory = factorCategory;
		return this;
	}

	public EpdUseConditionFactor withObjectSpecificGrade(Integer objectSpecificGrade) {
		this.objectSpecificGrade = objectSpecificGrade;
		return this;
	}

	public EpdUseConditionFactor withReferenceGrade(Integer referenceGrade) {
		this.referenceGrade = referenceGrade;
		return this;
	}

	public EpdUseConditionFactor withFactor(Double factor) {
		this.factor = factor;
		return this;
	}

	public EpdUseConditionFactor withComments(List<LangString> comments) {
		this.comments = comments;
		return this;
	}

	public List<LangString> withComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	// endregion

	@Override
	public EpdUseConditionFactor copy() {
		var copy = new EpdUseConditionFactor();
		copy.withFactorCategory(factorCategory);
		copy.withObjectSpecificGrade(objectSpecificGrade);
		copy.withReferenceGrade(referenceGrade);
		copy.withFactor(factor);
		Val.copy(comments, copy::withComments);
		return copy;
	}
}
