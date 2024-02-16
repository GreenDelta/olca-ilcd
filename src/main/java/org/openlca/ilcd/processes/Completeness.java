package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.FlowCompleteness;
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
@XmlType(name = "CompletenessType", propOrder = {
		"productCompleteness",
		"supportedImpactMethods",
		"entries",
		"otherDetails",
		"other" })
public class Completeness implements Copyable<Completeness> {

	@XmlElement(name = "completenessProductModel")
	private FlowCompleteness productCompleteness;

	@XmlElement(name = "referenceToSupportedImpactAssessmentMethods")
	private List<Ref> supportedImpactMethods;

	@XmlElement(name = "completenessElementaryFlows")
	private List<FlowCompletenessEntry> entries;

	@FreeText
	@XmlElement(name = "completenessOtherProblemField")
	private List<LangString> otherDetails;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public FlowCompleteness getProductCompleteness() {
		return productCompleteness;
	}

	public List<Ref> getSupportedImpactMethods() {
		return supportedImpactMethods != null ? supportedImpactMethods : Collections.emptyList();
	}

	public List<FlowCompletenessEntry> getEntries() {
		return entries != null ? entries : Collections.emptyList();
	}

	public List<LangString> getOtherDetails() {
		return otherDetails != null ? otherDetails : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Completeness withProductCompleteness(FlowCompleteness productCompleteness) {
		this.productCompleteness = productCompleteness;
		return this;
	}

	public Completeness withSupportedImpactMethods(List<Ref> supportedImpactMethods) {
		this.supportedImpactMethods = supportedImpactMethods;
		return this;
	}

	public Completeness withEntries(List<FlowCompletenessEntry> entries) {
		this.entries = entries;
		return this;
	}

	public Completeness withOtherDetails(List<LangString> otherDetails) {
		this.otherDetails = otherDetails;
		return this;
	}

	public Completeness withOther(Other other) {
		this.other = other;
		return this;
	}

	public Completeness withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Ref> withSupportedImpactMethods() {
		if (supportedImpactMethods == null) {
			supportedImpactMethods = new ArrayList<>();
		}
		return supportedImpactMethods;
	}

	public List<FlowCompletenessEntry> withEntries() {
		if (entries == null) {
			entries = new ArrayList<>();
		}
		return entries;
	}

	public List<LangString> withOtherDetails() {
		if (otherDetails == null) {
			otherDetails = new ArrayList<>();
		}
		return otherDetails;
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
	public Completeness copy() {
		var copy = new Completeness();
		copy.withProductCompleteness(productCompleteness);
		Val.copy(supportedImpactMethods, copy::withSupportedImpactMethods);
		Val.copy(entries, copy::withEntries);
		Val.copy(otherDetails, copy::withOtherDetails);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
