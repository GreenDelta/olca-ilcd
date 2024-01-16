
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "ExchangeDirectionValues")
@XmlEnum
public enum ExchangeDirection {

	/**
	 * Flow in input list of the process, e.g. resources from nature or energy
	 * carriers, or commodities and services entering from the technosphere. (In
	 * case the flow has a negative "resulting amount" value this is equivalent
	 * to belonging to the output list of the process.)
	 *
	 */
	@XmlEnumValue("Input")
	INPUT("Input"),

	/**
	 * Flow in output list of the process, e.g. emissions to nature, or products
	 * and waste going to the technosphere into another process. (In case the
	 * flow has a negative "resulting amount" value this is equivalent to
	 * belonging to the input list of the process.)
	 *
	 */
	@XmlEnumValue("Output")
	OUTPUT("Output");

	private final String value;

	ExchangeDirection(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<ExchangeDirection> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (ExchangeDirection c : ExchangeDirection.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
