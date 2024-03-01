package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Name", propOrder = {"value"})
public class Name implements Copyable<Name> {

	@XmlValue
	private String value;

	// region getters

	public String getValue() {
		return value;
	}

	// endregion

	// region setters

	public Name withValue(String value) {
		this.value = value;
		return this;
	}

	// endregion

	@Override
	public Name copy() {
		var copy = new Name();
		copy.withValue(value);
		return copy;
	}

}
