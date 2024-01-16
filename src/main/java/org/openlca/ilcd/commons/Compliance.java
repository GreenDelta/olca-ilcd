
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "ComplianceValues")
@XmlEnum
public enum Compliance {

	/**
	 * Meets all requirements of this compliance aspect as defined in the
	 * respective "Compliance system".
	 *
	 */
	@XmlEnumValue("Fully compliant")
	FULLY_COMPLIANT("Fully compliant"),

	/**
	 * Does not meet all requirements of this compliance aspect, as defined for
	 * the respective "Compliance system".
	 *
	 */
	@XmlEnumValue("Not compliant")
	NOT_COMPLIANT("Not compliant"),

	/**
	 * For this compliance aspect the named "Compliance system" has not defined
	 * compliance requirements.
	 *
	 */
	@XmlEnumValue("Not defined")
	NOT_DEFINED("Not defined");
	private final String value;

	Compliance(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<Compliance> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (Compliance c : Compliance.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
