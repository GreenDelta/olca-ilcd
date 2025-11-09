package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyData", propOrder = {"value"})
public class PropertyData implements Copyable<PropertyData> {

	@XmlElement(name = "Data", namespace = Vocab.MATML, required = true)
	private DataValue value;

	@XmlAttribute(name = "property", required = true)
	private String property;

	// region getters

	public DataValue getValue() {
		return value;
	}

	public String getProperty() {
		return property;
	}

	// endregion

	// region setters

	public PropertyData withValue(DataValue value) {
		this.value = value;
		return this;
	}

	public PropertyData withProperty(String property) {
		this.property = property;
		return this;
	}

	public DataValue withValue() {
		if (value == null) {
			value = new DataValue();
		}
		return value;
	}

	// endregion

	@Override
	public PropertyData copy() {
		var copy = new PropertyData()
			.withProperty(property);
		Val.copy(value, copy::withValue);
		return copy;
	}

}
