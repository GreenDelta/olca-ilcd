package org.openlca.ilcd.units;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuantitativeReferenceType", propOrder = {
		"referenceUnit",
		"other"
})
public class QuantitativeReference implements Copyable<QuantitativeReference> {

	@XmlElement(required = true, name = "referenceToReferenceUnit")
	private int referenceUnit;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public int getReferenceUnit() {
		return referenceUnit;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public QuantitativeReference withReferenceUnit(int referenceUnit) {
		this.referenceUnit = referenceUnit;
		return this;
	}

	public QuantitativeReference withOther(Other other) {
		this.other = other;
		return this;
	}

	public QuantitativeReference withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
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
		copy.withReferenceUnit(referenceUnit);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
