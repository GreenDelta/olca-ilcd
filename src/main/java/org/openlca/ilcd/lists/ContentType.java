
package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

/**
 * Specifies the type of data sets that can be contained in a category.
 */
@XmlEnum
@XmlType(name = "DataSetType")
public enum ContentType {

	@XmlEnumValue("Process")
	PROCESS("Process"),

	@XmlEnumValue("LCIAMethod")
	LCIA_METHOD("LCIAMethod"),

	@XmlEnumValue("Flow")
	FLOW("Flow"),

	@XmlEnumValue("FlowProperty")
	FLOW_PROPERTY("FlowProperty"),

	@XmlEnumValue("UnitGroup")
	UNIT_GROUP("UnitGroup"),

	@XmlEnumValue("Source")
	SOURCE("Source"),

	@XmlEnumValue("Contact")
	CONTACT("Contact");

	private final String value;

	ContentType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<ContentType> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (ContentType c : ContentType.values()) {
			if (c.value.equals(v)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

}
