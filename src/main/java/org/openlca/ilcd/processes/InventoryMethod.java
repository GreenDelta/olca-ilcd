package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ModellingApproach;
import org.openlca.ilcd.commons.ModellingPrinciple;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIMethodAndAllocationType", propOrder = {
	"processType",
	"principle",
	"principleDeviations",
	"approaches",
	"approachDeviations",
	"constants",
	"constantsDeviations",
	"sources",
	"other"})
public class InventoryMethod implements Copyable<InventoryMethod> {

	@XmlElement(name = "typeOfDataSet")
	private ProcessType processType;

	@XmlElement(name = "LCIMethodPrinciple")
	private ModellingPrinciple principle;

	@FreeText
	@XmlElement(name = "deviationsFromLCIMethodPrinciple")
	private List<LangString> principleDeviations;

	@XmlElement(name = "LCIMethodApproaches")
	private List<ModellingApproach> approaches;

	@FreeText
	@XmlElement(name = "deviationsFromLCIMethodApproaches")
	private List<LangString> approachDeviations;

	@FreeText
	@XmlElement(name = "modellingConstants")
	private List<LangString> constants;

	@FreeText
	@XmlElement(name = "deviationsFromModellingConstants")
	private List<LangString> constantsDeviations;

	@XmlElement(name = "referenceToLCAMethodDetails")
	private List<Ref> sources;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public ProcessType getProcessType() {
		return processType;
	}

	public ModellingPrinciple getPrinciple() {
		return principle;
	}

	public List<LangString> getPrincipleDeviations() {
		return principleDeviations != null ? principleDeviations : List.of();
	}

	public List<ModellingApproach> getApproaches() {
		return approaches != null ? approaches : List.of();
	}

	public List<LangString> getApproachDeviations() {
		return approachDeviations != null ? approachDeviations : List.of();
	}

	public List<LangString> getConstants() {
		return constants != null ? constants : List.of();
	}

	public List<LangString> getConstantsDeviations() {
		return constantsDeviations != null ? constantsDeviations : List.of();
	}

	public List<Ref> getSources() {
		return sources != null ? sources : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public InventoryMethod withProcessType(ProcessType processType) {
		this.processType = processType;
		return this;
	}

	public InventoryMethod withPrinciple(ModellingPrinciple principle) {
		this.principle = principle;
		return this;
	}

	public InventoryMethod withPrincipleDeviations(List<LangString> principleDeviations) {
		this.principleDeviations = principleDeviations;
		return this;
	}

	public InventoryMethod withApproaches(List<ModellingApproach> approaches) {
		this.approaches = approaches;
		return this;
	}

	public InventoryMethod withApproachDeviations(List<LangString> approachDeviations) {
		this.approachDeviations = approachDeviations;
		return this;
	}

	public InventoryMethod withConstants(List<LangString> constants) {
		this.constants = constants;
		return this;
	}

	public InventoryMethod withConstantsDeviations(List<LangString> constantsDeviations) {
		this.constantsDeviations = constantsDeviations;
		return this;
	}

	public InventoryMethod withSources(List<Ref> sources) {
		this.sources = sources;
		return this;
	}

	public InventoryMethod withOther(Other other) {
		this.other = other;
		return this;
	}

	public InventoryMethod withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withPrincipleDeviations() {
		if (principleDeviations == null) {
			principleDeviations = new ArrayList<>();
		}
		return principleDeviations;
	}

	public List<ModellingApproach> withApproaches() {
		if (approaches == null) {
			approaches = new ArrayList<>();
		}
		return approaches;
	}

	public List<LangString> withApproachDeviations() {
		if (approachDeviations == null) {
			approachDeviations = new ArrayList<>();
		}
		return approachDeviations;
	}

	public List<LangString> withConstants() {
		if (constants == null) {
			constants = new ArrayList<>();
		}
		return constants;
	}

	public List<LangString> withConstantsDeviations() {
		if (constantsDeviations == null) {
			constantsDeviations = new ArrayList<>();
		}
		return constantsDeviations;
	}

	public List<Ref> withSources() {
		if (sources == null) {
			sources = new ArrayList<>();
		}
		return sources;
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
	public InventoryMethod copy() {
		var copy = new InventoryMethod();
		copy.withProcessType(processType);
		copy.withPrinciple(principle);
		Val.copy(principleDeviations, copy::withPrincipleDeviations);
		if (approaches != null) {
			copy.withApproaches().addAll(approaches);
		}
		Val.copy(approachDeviations, copy::withApproachDeviations);
		Val.copy(constants, copy::withConstants);
		Val.copy(constantsDeviations, copy::withConstantsDeviations);
		Val.copy(sources, copy::withSources);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
