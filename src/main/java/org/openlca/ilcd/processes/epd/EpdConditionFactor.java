package org.openlca.ilcd.processes.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdConditionFactor implements Copyable<EpdConditionFactor> {

	@XmlAttribute(name = "factorCategory", namespace = Vocab.EPD_2024)
	private String category;

	@XmlAttribute(name = "objectSpecificGrade", namespace = Vocab.EPD_2024)
	private Integer objectSpecificGrade;

	@XmlAttribute(name = "referenceGrade", namespace = Vocab.EPD_2024)
	private Integer referenceGrade;

	@XmlAttribute(name = "factor", namespace = Vocab.EPD_2024)
	private double value;

	@XmlElement(name = "comment", namespace = Vocab.EPD_2024)
	private List<LangString> comments;

	// region getters

	public String getCategory() {
		return category;
	}

	public Integer getObjectSpecificGrade() {
		return objectSpecificGrade;
	}

	public Integer getReferenceGrade() {
		return referenceGrade;
	}

	public double getValue() {
		return value;
	}

	public List<LangString> getComments() {
		return comments != null ? comments : List.of();
	}

	// endregion

	// region setters

	public EpdConditionFactor withFactorCategory(String factorCategory) {
		this.category = factorCategory;
		return this;
	}

	public EpdConditionFactor withObjectSpecificGrade(Integer objectSpecificGrade) {
		this.objectSpecificGrade = objectSpecificGrade;
		return this;
	}

	public EpdConditionFactor withReferenceGrade(Integer referenceGrade) {
		this.referenceGrade = referenceGrade;
		return this;
	}

	public EpdConditionFactor withValue(double factor) {
		this.value = factor;
		return this;
	}

	public EpdConditionFactor withComments(List<LangString> comments) {
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
	public EpdConditionFactor copy() {
		var copy = new EpdConditionFactor();
		copy.withFactorCategory(category);
		copy.withObjectSpecificGrade(objectSpecificGrade);
		copy.withReferenceGrade(referenceGrade);
		copy.withValue(value);
		Val.copy(comments, copy::withComments);
		return copy;
	}
}
