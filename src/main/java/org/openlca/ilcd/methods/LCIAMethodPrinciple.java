package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "LCIAMethodPrincipleValues")
@XmlEnum
public enum LCIAMethodPrinciple {

	@XmlEnumValue("Distance-to-target")
	DISTANCE_TO_TARGET("Distance-to-target"),

	@XmlEnumValue("Critical surface-time")
	CRITICAL_SURFACE_TIME("Critical surface-time"),

	@XmlEnumValue("Effective volumes")
	EFFECTIVE_VOLUMES("Effective volumes"),

	@XmlEnumValue("AoP-Damage model")
	AO_P_DAMAGE_MODEL("AoP-Damage model"),

	@XmlEnumValue("Carrying capacity")
	CARRYING_CAPACITY("Carrying capacity"),

	@XmlEnumValue("Resource dissipation")
	RESOURCE_DISSIPATION("Resource dissipation"),

	@XmlEnumValue("other")
	OTHER("other");

	private final String value;

	LCIAMethodPrinciple(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<LCIAMethodPrinciple> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (LCIAMethodPrinciple c : LCIAMethodPrinciple.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
