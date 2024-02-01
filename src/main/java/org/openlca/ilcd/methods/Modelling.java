package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.ShortText;
import org.openlca.ilcd.flowproperties.ComplianceDeclaration;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModellingAndValidationType", propOrder = {
		"useAdvice",
		"normalisationAndWeighting",
		"dataSources",
		"completeness",
		"reviews",
		"complianceDeclarations",
		"other"
})
public class Modelling implements Copyable<Modelling> {

	@ShortText
	@XmlElement(name = "useAdviceForDataSet")
	private List<LangString> useAdvice;

	@XmlElement(name = "LCIAMethodNormalisationAndWeighting")
	private NormalisationAndWeighting normalisationAndWeighting;

	@XmlElementWrapper(name = "dataSources")
	@XmlElement(name = "referenceToDataSource")
	private List<Ref> dataSources;

	private Completeness completeness;

	@XmlElementWrapper(name="validation")
	@XmlElement(name = "review", required = true)
	private List<Review> reviews;

	@XmlElementWrapper(name = "complianceDeclarations")
	@XmlElement(name = "compliance")
	private List<ComplianceDeclaration> complianceDeclarations;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<LangString> getUseAdvice() {
		return useAdvice != null ? useAdvice : List.of();
	}

	public NormalisationAndWeighting getNormalisationAndWeighting() {
		return normalisationAndWeighting;
	}

	public List<Ref> getDataSources() {
		return dataSources != null ? dataSources : List.of();
	}

	public Completeness getCompleteness() {
		return completeness;
	}

	public List<Review> getReviews() {
		return reviews != null ? reviews : List.of();
	}

	public List<ComplianceDeclaration> getComplianceDeclarations() {
		return complianceDeclarations != null ? complianceDeclarations : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Modelling withUseAdvice(List<LangString> useAdvice) {
		this.useAdvice = useAdvice;
		return this;
	}

	public Modelling withNormalisationAndWeighting(NormalisationAndWeighting normalisationAndWeighting) {
		this.normalisationAndWeighting = normalisationAndWeighting;
		return this;
	}

	public Modelling withDataSources(List<Ref> dataSources) {
		this.dataSources = dataSources;
		return this;
	}

	public Modelling withCompleteness(Completeness completeness) {
		this.completeness = completeness;
		return this;
	}

	public Modelling withReviews(List<Review> reviews) {
		this.reviews = reviews;
		return this;
	}

	public Modelling withComplianceDeclarations(List<ComplianceDeclaration> complianceDeclarations) {
		this.complianceDeclarations = complianceDeclarations;
		return this;
	}

	public Modelling withOther(Other other) {
		this.other = other;
		return this;
	}

	public Modelling withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withUseAdvice() {
		if (useAdvice == null) {
			useAdvice = new ArrayList<>();
		}
		return useAdvice;
	}

	public NormalisationAndWeighting withNormalisationAndWeighting() {
		if (normalisationAndWeighting == null) {
			normalisationAndWeighting = new NormalisationAndWeighting();
		}
		return normalisationAndWeighting;
	}

	public List<Ref> withDataSources() {
		if (dataSources == null) {
			dataSources = new ArrayList<>();
		}
		return dataSources;
	}

	public Completeness withCompleteness() {
		if (completeness == null) {
			completeness = new Completeness();
		}
		return completeness;
	}

	public List<Review> withReviews() {
		if (reviews == null) {
			reviews = new ArrayList<>();
		}
		return reviews;
	}

	public List<ComplianceDeclaration> withComplianceDeclarations() {
		if (complianceDeclarations == null) {
			complianceDeclarations = new ArrayList<>();
		}
		return complianceDeclarations;
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
	public Modelling copy() {
		var copy = new Modelling();
		Val.copy(useAdvice, copy::withUseAdvice);
		Val.copy(normalisationAndWeighting, copy::withNormalisationAndWeighting);
		Val.copy(dataSources, copy::withDataSources);
		Val.copy(completeness, copy::withCompleteness);
		Val.copy(reviews, copy::withReviews);
		Val.copy(complianceDeclarations, copy::withComplianceDeclarations);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
