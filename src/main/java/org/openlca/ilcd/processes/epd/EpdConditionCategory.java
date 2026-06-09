package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import org.openlca.commons.Strings;

import java.util.Optional;

@XmlEnum
public enum EpdConditionCategory {

	@XmlEnumValue("A - inherent quality")
	A_INHERENT_QUALITY("A - inherent quality"),

	@XmlEnumValue("B - design level")
	B_DESIGN_LEVEL("B - design level"),

	@XmlEnumValue("C - work execution")
	C_WORK_EXECUTION("C - work execution"),

	@XmlEnumValue("D - indoor environment")
	D_INDOOR_ENVIRONMENT("D - indoor environment"),

	@XmlEnumValue("E - outdoor environment")
	E_OUTDOOR_ENVIRONMENT("E - outdoor environment"),

	@XmlEnumValue("F - usage conditions")
	F_USAGE_CONDITIONS("F - usage conditions"),

	@XmlEnumValue("G - maintenance level")
	G_MAINTENANCE_LEVEL("G - maintenance level");

	private final String value;

	EpdConditionCategory(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static Optional<EpdConditionCategory> fromValue(String v) {
		if (Strings.isBlank(v))
			return Optional.empty();
		for (var c : values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}
}
