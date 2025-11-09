
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Strings;

import java.util.Optional;

@XmlType(name = "TypeOfOrganisationValues")
@XmlEnum
public enum OrganisationType {

	/**
	 * Private company
	 *
	 */
	@XmlEnumValue("Private company")
	PRIVATE_COMPANY("Private company"),

	/**
	 * Governmental organisation
	 *
	 */
	@XmlEnumValue("Governmental")
	GOVERNMENTAL("Governmental"),

	/**
	 * Non-governmental organisation
	 *
	 */
	@XmlEnumValue("Non-governmental org.")
	NON_GOVERNMENTAL_ORG("Non-governmental org."),

	/**
	 * Other, e.g. a project consortium or network
	 *
	 */
	@XmlEnumValue("Other")
	OTHER("Other");

	private final String value;

	OrganisationType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<OrganisationType> fromValue(String v) {
		if (Strings.isBlank(v))
			return Optional.empty();
		for (OrganisationType c : OrganisationType.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
