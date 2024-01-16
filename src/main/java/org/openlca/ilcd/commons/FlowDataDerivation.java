
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "FlowDataDerivationTypeStatusValues")
@XmlEnum
public enum FlowDataDerivation {

	/**
	 * Data was measured for the flow; includes data from publications with
	 * measured data.
	 */
	@XmlEnumValue("Measured")
	MEASURED("Measured"),

	/**
	 * Stoichiometric, enthalpic or other theoretical methods were used to
	 * systematically calculate the value of this property from another
	 * characteristic.
	 */
	@XmlEnumValue("Calculated")
	CALCULATED("Calculated"),

	/**
	 * Expert judgement including the direct or modified use of data from
	 * similar flows, or from other locations and times (e.g. for market prices
	 * of product flows).
	 */
	@XmlEnumValue("Estimated")
	ESTIMATED("Estimated"),

	/**
	 * Data derivation type information fully or at least for quantitatively
	 * relevant parts unavailable.
	 */
	@XmlEnumValue("Unknown derivation")
	UNKNOWN_DERIVATION("Unknown derivation");

	private final String value;

	FlowDataDerivation(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<FlowDataDerivation> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (FlowDataDerivation c : FlowDataDerivation.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
