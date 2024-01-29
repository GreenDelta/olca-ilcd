package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.ShortText;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpactModelType", propOrder = {
		"name",
		"description",
		"sources",
		"includedMethods",
		"consideredMechanisms",
		"flowCharts",
		"other"
})
public class ImpactModel implements Copyable<ImpactModel> {

	@XmlElement(name = "modelName")
	private String name;

	@FreeText
	@XmlElement(name = "modelDescription")
	private List<LangString> description;

	@XmlElement(name = "referenceToModelSource")
	private List<Ref> sources;

	@XmlElement(name = "referenceToIncludedMethods")
	private List<Ref> includedMethods;

	@ShortText
	private List<LangString> consideredMechanisms;

	@XmlElement(name = "referenceToMethodologyFlowChart")
	private List<Ref> flowCharts;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getName() {
		return name;
	}

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	public List<Ref> getSources() {
		return sources != null ? sources : List.of();
	}

	public List<Ref> getIncludedMethods() {
		return includedMethods != null ? includedMethods : List.of();
	}

	public List<LangString> getConsideredMechanisms() {
		return consideredMechanisms != null ? consideredMechanisms : List.of();
	}

	public List<Ref> getFlowCharts() {
		return flowCharts != null ? flowCharts : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public ImpactModel withName(String name) {
		this.name = name;
		return this;
	}

	public ImpactModel withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public ImpactModel withSources(List<Ref> sources) {
		this.sources = sources;
		return this;
	}

	public ImpactModel withIncludedMethods(List<Ref> includedMethods) {
		this.includedMethods = includedMethods;
		return this;
	}

	public ImpactModel withConsideredMechanisms(List<LangString> consideredMechanisms) {
		this.consideredMechanisms = consideredMechanisms;
		return this;
	}

	public ImpactModel withFlowCharts(List<Ref> flowCharts) {
		this.flowCharts = flowCharts;
		return this;
	}

	public ImpactModel withOther(Other other) {
		this.other = other;
		return this;
	}

	public ImpactModel withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	public List<Ref> withSources() {
		if (sources == null) {
			sources = new ArrayList<>();
		}
		return sources;
	}

	public List<Ref> withIncludedMethods() {
		if (includedMethods == null) {
			includedMethods = new ArrayList<>();
		}
		return includedMethods;
	}

	public List<LangString> withConsideredMechanisms() {
		if (consideredMechanisms == null) {
			consideredMechanisms = new ArrayList<>();
		}
		return consideredMechanisms;
	}

	public List<Ref> withFlowCharts() {
		if (flowCharts == null) {
			flowCharts = new ArrayList<>();
		}
		return flowCharts;
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
	public ImpactModel copy() {
		var copy = new ImpactModel();
		copy.withName(name);
		Val.copy(description, copy::withDescription);
		Val.copy(sources, copy::withSources);
		Val.copy(includedMethods, copy::withIncludedMethods);
		Val.copy(consideredMechanisms, copy::withConsideredMechanisms);
		Val.copy(flowCharts, copy::withFlowCharts);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
