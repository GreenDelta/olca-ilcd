package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Strings;

import java.util.Optional;

@XmlType(name = "MethodOfReviewValues")
@XmlEnum
public enum ReviewMethod {

	@XmlEnumValue("Recollection / Validation of data")
	RECOLLECTION_VALIDATION_OF_DATA("Recollection / Validation of data"),

	@XmlEnumValue("Recalculation")
	RECALCULATION("Recalculation"),

	@XmlEnumValue("Cross-check with other source")
	CROSS_CHECK_WITH_OTHER_SOURCE("Cross-check with other source"),

	@XmlEnumValue("Cross-check with other LCIA method(ology)")
	CROSS_CHECK_WITH_OTHER_LCIA_METHOD_OLOGY("Cross-check with other LCIA method(ology)"),

	@XmlEnumValue("Expert judgement")
	EXPERT_JUDGEMENT("Expert judgement");

	private final String value;

	ReviewMethod(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<ReviewMethod> fromValue(String v) {
		if (Strings.isBlank(v))
			return Optional.empty();
		for (ReviewMethod c : ReviewMethod.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
