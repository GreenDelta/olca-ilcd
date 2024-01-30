package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.ReviewType;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReviewType", propOrder = {
	"scopes",
	"details",
	"reviewers",
	"otherDetails",
	"report"
})
public class Review implements Copyable<Review> {

	@XmlElement(name = "scope")
	private List<Scope> scopes;

	@FreeText
	@XmlElement(
		name = "reviewDetails",
		namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> details;

	@XmlElement(
		name = "referenceToNameOfReviewerAndInstitution",
		namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Ref> reviewers;

	@FreeText
	@XmlElement(
		name = "otherReviewDetails",
		namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> otherDetails;

	@XmlElement(
		name = "referenceToCompleteReviewReport",
		namespace = "http://lca.jrc.it/ILCD/Common")
	private Ref report;

	@XmlAttribute(name = "type", required = true)
	private ReviewType type;

	// region getters

	public List<Scope> getScopes() {
		return scopes != null ? scopes : List.of();
	}

	public List<LangString> getDetails() {
		return details != null ? details : List.of();
	}

	public List<Ref> getReviewers() {
		return reviewers != null ? reviewers : List.of();
	}

	public List<LangString> getOtherDetails() {
		return otherDetails != null ? otherDetails : List.of();
	}

	public Ref getReport() {
		return report;
	}

	public ReviewType getType() {
		return type;
	}

	// endregion

	// region setters

	public Review withScopes(List<Scope> scopes) {
		this.scopes = scopes;
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

	public Review withType(ReviewType type) {
		this.type = type;
		return this;
	}

	public List<Scope> withScopes() {
		if (scopes == null) {
			scopes = new ArrayList<>();
		}
		return scopes;
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

	// endregion

	@Override
	public Review copy() {
		var copy = new Review();
		Val.copy(scopes, copy::withScopes);
		Val.copy(details, copy::withDetails);
		Val.copy(reviewers, copy::withReviewers);
		Val.copy(otherDetails, copy::withOtherDetails);
		Val.copy(report, copy::withReport);
		copy.withType(type);
		return copy;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {"methods"})
	public static class Scope implements Copyable<Scope> {

		@XmlElement(name = "method")
		private List<Method> methods;

		@XmlAttribute(name = "name", required = true)
		private ReviewScope name;

		// region getters

		public List<Method> getMethods() {
			return methods != null ? methods : List.of();
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
	@XmlType(name = "")
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
