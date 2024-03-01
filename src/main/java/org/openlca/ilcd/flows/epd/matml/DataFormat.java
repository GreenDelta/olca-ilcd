package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "DataFormat")
@XmlEnum
public enum DataFormat {

	@XmlEnumValue("float")
	FLOAT("float"),

	@XmlEnumValue("integer")
	INTEGER("integer"),

	@XmlEnumValue("exponential")
	EXPONENTIAL("exponential"),

	@XmlEnumValue("mixed")
	MIXED("mixed");

	private final String value;

	DataFormat(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<DataFormat> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (DataFormat c : DataFormat.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
