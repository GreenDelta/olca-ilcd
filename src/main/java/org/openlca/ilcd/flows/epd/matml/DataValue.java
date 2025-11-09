package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Data")
public class DataValue implements Copyable<DataValue> {

	@XmlValue
	private String value;

	@XmlAttribute(name = "format", required = true)
	private String format;

	// region getters

	public String getValue() {
		return value;
	}

	public String getFormat() {
		return format;
	}

	// endregion

	// region setters

	public DataValue withValue(String value) {
		this.value = value;
		return this;
	}

	public DataValue withFormat(String format) {
		this.format = format;
		return this;
	}

	// endregion

	@Override
	public DataValue copy() {
		var copy = new DataValue();
		copy.withValue(value);
		copy.withFormat(format);
		return copy;
	}

}
