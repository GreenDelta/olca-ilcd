package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataQualityIndicator;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.ReviewMethod;
import org.openlca.ilcd.commons.ReviewScope;
import org.openlca.ilcd.commons.ReviewType;
import org.openlca.ilcd.commons.annotations.FreeText;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReviewType", propOrder = { "scopes", "indicators",
		"details", "reviewers",
		"otherDetails", "report", "other" })
public class Review implements Copyable<Review> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "scope")
	public final List<Scope> scopes = new ArrayList<>();

	@XmlElementWrapper(namespace = "http://lca.jrc.it/ILCD/Common", name = "dataQualityIndicators")
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "dataQualityIndicator")
	public DataQualityIndicator[] indicators;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "reviewDetails")
	public final List<LangString> details = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToNameOfReviewerAndInstitution")
	public final List<Ref> reviewers = new ArrayList<>();

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "otherReviewDetails")
	public final List<LangString> otherDetails = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToCompleteReviewReport")
	public Ref report;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAttribute(name = "type")
	public ReviewType type;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "methods" })
	public static class Scope implements Copyable<Scope> {

		@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "method")
		public final List<Method> methods = new ArrayList<>();

		@XmlAttribute(name = "name", required = true)
		public ReviewScope name;

		@Override
		public Scope copy() {
			Scope clone = new Scope();
			for (Method m : methods) {
				if (m == null)
					continue;
				clone.methods.add(m.copy());
			}
			clone.name = name;
			return clone;
		}

	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Method implements Copyable<Method> {

		@XmlAttribute(name = "name", required = true)
		public ReviewMethod name;

		@Override
		public Method copy() {
			Method clone = new Method();
			clone.name = name;
			return clone;
		}
	}

	@Override
	public Review copy() {
		Review clone = new Review();
		for (Scope s : scopes)
			clone.scopes.add(s.copy());
		if (indicators != null) {
			clone.indicators = new DataQualityIndicator[indicators.length];
			for (int i = 0; i < indicators.length; i++) {
				if (indicators[i] == null)
					continue;
				clone.indicators[i] = indicators[i].copy();
			}
		}
		LangString.copy(details, clone.details);
		Ref.copy(reviewers, clone.reviewers);
		LangString.copy(otherDetails, clone.otherDetails);
		if (report != null)
			clone.report = report.copy();
		if (other != null)
			clone.other = other.copy();
		clone.type = type;
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
