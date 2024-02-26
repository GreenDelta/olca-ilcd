package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlEnum
public enum EpdSubType {

	@XmlEnumValue("generic dataset")
	GENERIC_DATASET("generic dataset"),

	@XmlEnumValue("representative dataset")
	REPRESENTATIVE_DATASET("representative dataset"),

	@XmlEnumValue("average dataset")
	AVERAGE_DATASET("average dataset"),

	@XmlEnumValue("specific dataset")
	SPECIFIC_DATASET("specific dataset"),

	@XmlEnumValue("template dataset")
	TEMPLATE_DATASET("template dataset");

	private final String value;

	EpdSubType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<EpdSubType> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (var t : EpdSubType.values()) {
			if (t.value.equals(v)) {
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}
}

