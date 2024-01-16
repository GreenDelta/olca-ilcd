
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "ExchangeFunctionTypeValues")
@XmlEnum
public enum ExchangeFunction {

	/**
	 * Reminder flow on the amount of (selected) product and waste flows of
	 * included processes. Purely informative flow for additional reporting that
	 * has already been fully considered in the inventory of the data set.
	 * Serves to document e.g. the total amount of hazardous waste generated
	 * over the life cycle of a product system.
	 *
	 */
	@XmlEnumValue("General reminder flow")
	GENERAL_REMINDER_FLOW("General reminder flow"),

	/**
	 * Reminder flow on allocated co-products that have been excluded during
	 * allocation when calculating the LCI results. Purely informative flow for
	 * additional reporting that has already been fully considered in the
	 * inventory of the data set.
	 *
	 */
	@XmlEnumValue("Allocation reminder flow")
	ALLOCATION_REMINDER_FLOW("Allocation reminder flow"),

	/**
	 * Reminder flow on excluded co-products that have been excluded in
	 * consequential modelling e.g. by system expansion / substitution when
	 * calculating the LCI results.
	 *
	 */
	@XmlEnumValue("System expansion reminder flow")
	SYSTEM_EXPANSION_REMINDER_FLOW("System expansion reminder flow");

	private final String value;

	ExchangeFunction(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<ExchangeFunction> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (ExchangeFunction c : ExchangeFunction.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
