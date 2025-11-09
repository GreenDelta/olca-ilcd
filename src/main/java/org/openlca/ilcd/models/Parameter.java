package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class Parameter implements Copyable<Parameter> {

	@XmlAttribute(name = "name")
	private String name;

	@XmlValue
	private Double value;

	// region getters

	public String getName() {
		return name;
	}

	public Double getValue() {
		return value;
	}

	// endregion

	// region setters

	public Parameter withName(String name) {
		this.name = name;
		return this;
	}

	public Parameter withValue(Double value) {
		this.value = value;
		return this;
	}

	// endregion

	@Override
	public Parameter copy() {
		var copy = new Parameter();
		copy.withName(name);
		copy.withValue(value);
		return copy;
	}

}
