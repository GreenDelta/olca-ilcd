package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Data")
public class Data implements Copyable<Data> {

	@XmlValue
	private String value;

	@XmlAttribute(name = "format", required = true)
	private DataFormat format;

	// region getters

	public String getValue() {
		return value;
	}

	public DataFormat getFormat() {
		return format;
	}

	// endregion

	// region setters

	public Data withValue(String value) {
		this.value = value;
		return this;
	}

	public Data withFormat(DataFormat format) {
		this.format = format;
		return this;
	}

	// endregion

	@Override
	public Data copy() {
		var copy = new Data();
		copy.withValue(value);
		copy.withFormat(format);
		return copy;
	}

}
