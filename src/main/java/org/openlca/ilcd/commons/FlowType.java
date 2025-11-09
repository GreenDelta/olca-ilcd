
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Strings;

import java.util.Optional;

@XmlType(name = "FlowTypeValues")
@XmlEnum
public enum FlowType {

	/**
	 * Exchange between nature (ecosphere) and technosphere, e.g. an emission,
	 * resource.
	 *
	 */
	@XmlEnumValue("Elementary flow")
	ELEMENTARY_FLOW("Elementary flow"),

	/**
	 * Exchange of goods or services within technosphere, with a positive
	 * economic/market value.
	 *
	 */
	@XmlEnumValue("Product flow")
	PRODUCT_FLOW("Product flow"),

	/**
	 * Exchange of matters within the technosphere, with a economic/market value
	 * equal or below "0".
	 *
	 */
	@XmlEnumValue("Waste flow")
	WASTE_FLOW("Waste flow"),

	/**
	 * Exchange of other type, e.g. dummy or modelling support flows.
	 *
	 */
	@XmlEnumValue("Other flow")
	OTHER_FLOW("Other flow");

	private final String value;

	FlowType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<FlowType> fromValue(String v) {
		if (Strings.isBlank(v))
			return Optional.empty();
		for (FlowType c : FlowType.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
