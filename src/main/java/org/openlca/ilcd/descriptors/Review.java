package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataQualityIndicator;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ReviewType;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"scope", "dataQualityIndicators",
	"reviewDetails", "otherReviewDetails", "reviewer"})
@XmlRootElement(name = "review")
public class Review implements Copyable<Review> {

	private List<Scope> scope;

	@XmlElementWrapper(name = "dataQualityIndicators")
	@XmlElement(name = "dataQualityIndicator")
	private List<DataQualityIndicator> dataQualityIndicators;

	private List<LangString> reviewDetails;

	private List<LangString> otherReviewDetails;

	private DataSetReference reviewer;

	@XmlAttribute(name = "type", required = true)
	private ReviewType type;

	// region getters

	public List<Scope> getScope() {
		return scope != null ? scope : Collections.emptyList();
	}

	public List<DataQualityIndicator> getDataQualityIndicators() {
		return dataQualityIndicators != null ? dataQualityIndicators : Collections.emptyList();
	}

	public List<LangString> getReviewDetails() {
		return reviewDetails != null ? reviewDetails : Collections.emptyList();
	}

	public List<LangString> getOtherReviewDetails() {
		return otherReviewDetails != null ? otherReviewDetails : Collections.emptyList();
	}

	public DataSetReference getReviewer() {
		return reviewer;
	}

	public ReviewType getType() {
		return type;
	}

	// endregion

	// region setters

	public Review withScope(List<Scope> scope) {
		this.scope = scope;
		return this;
	}

	public Review withDataQualityIndicators(List<DataQualityIndicator> dataQualityIndicators) {
		this.dataQualityIndicators = dataQualityIndicators;
		return this;
	}

	public Review withReviewDetails(List<LangString> reviewDetails) {
		this.reviewDetails = reviewDetails;
		return this;
	}

	public Review withOtherReviewDetails(List<LangString> otherReviewDetails) {
		this.otherReviewDetails = otherReviewDetails;
		return this;
	}

	public Review withReviewer(DataSetReference reviewer) {
		this.reviewer = reviewer;
		return this;
	}

	public Review withType(ReviewType type) {
		this.type = type;
		return this;
	}

	public List<Scope> withScope() {
		if (scope == null) {
			scope = new ArrayList<>();
		}
		return scope;
	}

	public List<DataQualityIndicator> withDataQualityIndicators() {
		if (dataQualityIndicators == null) {
			dataQualityIndicators = new ArrayList<>();
		}
		return dataQualityIndicators;
	}

	public List<LangString> withReviewDetails() {
		if (reviewDetails == null) {
			reviewDetails = new ArrayList<>();
		}
		return reviewDetails;
	}

	public List<LangString> withOtherReviewDetails() {
		if (otherReviewDetails == null) {
			otherReviewDetails = new ArrayList<>();
		}
		return otherReviewDetails;
	}

	public DataSetReference withReviewer() {
		if (reviewer == null) {
			reviewer = new DataSetReference();
		}
		return reviewer;
	}

	// endregion

	@Override
	public Review copy() {
		var copy = new Review();
		Val.copy(scope, copy::withScope);
		Val.copy(dataQualityIndicators, copy::withDataQualityIndicators);
		Val.copy(reviewDetails, copy::withReviewDetails);
		Val.copy(otherReviewDetails, copy::withOtherReviewDetails);
		Val.copy(reviewer, copy::withReviewer);
		copy.withType(type);
		return copy;
	}

}
