package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.QuantitativeReferenceType;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuantitativeReferenceType", propOrder = {
	"referenceFlows", "functionalUnit", "other"})
public class QuantitativeReference implements Copyable<QuantitativeReference> {

	@XmlElement(name = "referenceToReferenceFlow")
	private List<Integer> referenceFlows;

	@Label
	@XmlElement(name = "functionalUnitOrOther")
	private List<LangString> functionalUnit;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "type")
	private QuantitativeReferenceType type;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<Integer> getReferenceFlows() {
		return referenceFlows != null ? referenceFlows : List.of();
	}

	public List<LangString> getFunctionalUnit() {
		return functionalUnit != null ? functionalUnit : List.of();
	}

	public Other getOther() {
		return other;
	}

	public QuantitativeReferenceType getType() {
		return type;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public QuantitativeReference withReferenceFlows(List<Integer> referenceFlows) {
		this.referenceFlows = referenceFlows;
		return this;
	}

	public QuantitativeReference withFunctionalUnit(List<LangString> functionalUnit) {
		this.functionalUnit = functionalUnit;
		return this;
	}

	public QuantitativeReference withOther(Other other) {
		this.other = other;
		return this;
	}

	public QuantitativeReference withType(QuantitativeReferenceType type) {
		this.type = type;
		return this;
	}

	public QuantitativeReference withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Integer> withReferenceFlows() {
		if (referenceFlows == null) {
			referenceFlows = new ArrayList<>();
		}
		return referenceFlows;
	}

	public List<LangString> withFunctionalUnit() {
		if (functionalUnit == null) {
			functionalUnit = new ArrayList<>();
		}
		return functionalUnit;
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
	public QuantitativeReference copy() {
		var copy = new QuantitativeReference();
		if (referenceFlows != null) {
			copy.withReferenceFlows().addAll(referenceFlows);
		}
		Val.copy(functionalUnit, copy::withFunctionalUnit);
		Val.copy(other, copy::withOther);
		copy.withType(type);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
