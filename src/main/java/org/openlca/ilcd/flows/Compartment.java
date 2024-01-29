package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryType", propOrder = {"value"})
public class Compartment implements Copyable<Compartment> {

	@XmlValue
	private String value;

	@XmlAttribute(name = "level", required = true)
	private int level;

	@XmlAttribute(name = "catId")
	private String catId;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getValue() {
		return value;
	}

	public int getLevel() {
		return level;
	}

	public String getCatId() {
		return catId;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Compartment withValue(String value) {
		this.value = value;
		return this;
	}

	public Compartment withLevel(int level) {
		this.level = level;
		return this;
	}

	public Compartment withCatId(String catId) {
		this.catId = catId;
		return this;
	}

	public Compartment withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Compartment copy() {
		var copy = new Compartment();
		copy.withValue(value);
		copy.withLevel(level);
		copy.withCatId(catId);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
