package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Strings;

import java.util.Optional;

@XmlType(name = "AreaOfProtectionValues")
@XmlEnum
public enum AreaOfProtection {

	@XmlEnumValue("Natural resources")
	NATURAL_RESOURCES("Natural resources"),

	@XmlEnumValue("Natural environment")
	NATURAL_ENVIRONMENT("Natural environment"),

	@XmlEnumValue("Human health")
	HUMAN_HEALTH("Human health"),

	@XmlEnumValue("Man-made environment")
	MAN_MADE_ENVIRONMENT("Man-made environment"),

	@XmlEnumValue("Other")
	OTHER("Other");

	private final String value;

	AreaOfProtection(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<AreaOfProtection> fromValue(String v) {
		if (Strings.isBlank(v))
			return Optional.empty();
		for (AreaOfProtection c : AreaOfProtection.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
