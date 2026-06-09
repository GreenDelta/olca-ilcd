package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "variationRange")
public enum EpdVariationRange {

	@XmlEnumValue("A - less than 2,5%")
	A_LESS_THAN_2_5("A - less than 2,5%"),

	@XmlEnumValue("B - between 2,5% and 10%")
	B_BETWEEN_2_5_AND_10("B - between 2,5% and 10%"),

	@XmlEnumValue("C - between 10% and 25%")
	C_BETWEEN_10_AND_25("C - between 10% and 25%"),

	@XmlEnumValue("D - between 25% and 50%")
	D_BETWEEN_25_AND_50("D - between 25% and 50%"),

	@XmlEnumValue("E - more than 50%")
	E_MORE_THAN_50("E - more than 50%");

	private final String value;

	EpdVariationRange(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
