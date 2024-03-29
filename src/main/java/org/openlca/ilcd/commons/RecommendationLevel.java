package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "RecommendationLevelValues")
@XmlEnum
public enum RecommendationLevel {

	/**
	 * Highest recommendation level. See also field "Specific meaning of the
	 * recommendation level".
	 */
	@XmlEnumValue("Level I")
	LEVEL_I("Level I"),

	/**
	 * Second highest recommendation level. See also field "Specific meaning of
	 * the recommendation level".
	 */
	@XmlEnumValue("Level II")
	LEVEL_II("Level II"),

	/**
	 * Third highest recommendation level. See also field "Specific meaning of
	 * the recommendation level".
	 */
	@XmlEnumValue("Level III")
	LEVEL_III("Level III"),

	/**
	 * Level between the third highest recommendation level and "not
	 * recommended". See also field "Specific meaning of the recommendation
	 * level".
	 */
	@XmlEnumValue("Interim")
	INTERIM("Interim"),

	/**
	 * Not recommended for use.
	 */
	@XmlEnumValue("Not recommended")
	NOT_RECOMMENDED("Not recommended");

	private final String value;

	RecommendationLevel(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<RecommendationLevel> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (RecommendationLevel c : RecommendationLevel.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
