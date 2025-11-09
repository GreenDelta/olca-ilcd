package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.DataQualityIndicator;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.ReviewType;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReviewType", propOrder = {"scopes", "indicators",
	"details", "reviewers",
	"otherDetails", "report", "other"})
public class Review implements Copyable<Review> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "scope")
	private List<Scope> scopes;

	@XmlElementWrapper(namespace = "http://lca.jrc.it/ILCD/Common", name = "dataQualityIndicators")
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "dataQualityIndicator")
	private List<DataQualityIndicator> indicators;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "reviewDetails")
	private List<LangString> details;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToNameOfReviewerAndInstitution")
	private List<Ref> reviewers;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "otherReviewDetails")
	private List<LangString> otherDetails;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToCompleteReviewReport")
	private Ref report;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "type")
	private ReviewType type;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<Scope> getScopes() {
		return scopes != null ? scopes : Collections.emptyList();
	}

	public List<DataQualityIndicator> getIndicators() {
		return indicators != null ? indicators : Collections.emptyList();
	}

	public List<LangString> getDetails() {
		return details != null ? details : Collections.emptyList();
	}

	public List<Ref> getReviewers() {
		return reviewers != null ? reviewers : Collections.emptyList();
	}

	public List<LangString> getOtherDetails() {
		return otherDetails != null ? otherDetails : Collections.emptyList();
	}

	public Ref getReport() {
		return report;
	}

	public Other getOther() {
		return other;
	}

	public ReviewType getType() {
		return type;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Review withScopes(List<Scope> scopes) {
		this.scopes = scopes;
		return this;
	}

	public Review withIndicators(List<DataQualityIndicator> indicators) {
		this.indicators = indicators;
		return this;
	}

	public Review withDetails(List<LangString> details) {
		this.details = details;
		return this;
	}

	public Review withReviewers(List<Ref> reviewers) {
		this.reviewers = reviewers;
		return this;
	}

	public Review withOtherDetails(List<LangString> otherDetails) {
		this.otherDetails = otherDetails;
		return this;
	}

	public Review withReport(Ref report) {
		this.report = report;
		return this;
	}

	public Review withOther(Other other) {
		this.other = other;
		return this;
	}

	public Review withType(ReviewType type) {
		this.type = type;
		return this;
	}

	public Review withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Scope> withScopes() {
		if (scopes == null) {
			scopes = new ArrayList<>();
		}
		return scopes;
	}

	public List<DataQualityIndicator> withIndicators() {
		if (indicators == null) {
			indicators = new ArrayList<>();
		}
		return indicators;
	}

	public List<LangString> withDetails() {
		if (details == null) {
			details = new ArrayList<>();
		}
		return details;
	}

	public List<Ref> withReviewers() {
		if (reviewers == null) {
			reviewers = new ArrayList<>();
		}
		return reviewers;
	}

	public List<LangString> withOtherDetails() {
		if (otherDetails == null) {
			otherDetails = new ArrayList<>();
		}
		return otherDetails;
	}

	public Ref withReport() {
		if (report == null) {
			report = new Ref();
		}
		return report;
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
	public Review copy() {
		var copy = new Review();
		Val.copy(scopes, copy::withScopes);
		Val.copy(indicators, copy::withIndicators);
		Val.copy(details, copy::withDetails);
		Val.copy(reviewers, copy::withReviewers);
		Val.copy(otherDetails, copy::withOtherDetails);
		Val.copy(report, copy::withReport);
		Val.copy(other, copy::withOther);
		copy.withType(type);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {"methods"})
	public static class Scope implements Copyable<Scope> {

		@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "method")
		private List<Method> methods;

		@XmlAttribute(name = "name", required = true)
		private ReviewScope name;

		// region getters

		public List<Method> getMethods() {
			return methods != null ? methods : Collections.emptyList();
		}

		public ReviewScope getName() {
			return name;
		}

		// endregion

		// region setters

		public Scope withMethods(List<Method> methods) {
			this.methods = methods;
			return this;
		}

		public Scope withName(ReviewScope name) {
			this.name = name;
			return this;
		}

		public List<Method> withMethods() {
			if (methods == null) {
				methods = new ArrayList<>();
			}
			return methods;
		}

		// endregion

		@Override
		public Scope copy() {
			var copy = new Scope();
			Val.copy(methods, copy::withMethods);
			copy.withName(name);
			return copy;
		}

	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Method implements Copyable<Method> {

		@XmlAttribute(name = "name", required = true)
		private ReviewMethod name;

		// region getters

		public ReviewMethod getName() {
			return name;
		}

		// endregion

		// region setters

		public Method withName(ReviewMethod name) {
			this.name = name;
			return this;
		}

		// endregion

		@Override
		public Method copy() {
			var copy = new Method();
			copy.withName(name);
			return copy;
		}
	}
}
