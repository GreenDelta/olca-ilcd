
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.Strings;

import java.util.Optional;

@XmlType(name = "GlobalReferenceTypeValues")
@XmlEnum
public enum DataSetType {

	@XmlEnumValue("source data set")
	SOURCE("source data set"),

	@XmlEnumValue("process data set")
	PROCESS("process data set"),

	@XmlEnumValue("flow data set")
	FLOW("flow data set"),

	@XmlEnumValue("flow property data set")
	FLOW_PROPERTY("flow property data set"),

	@XmlEnumValue("unit group data set")
	UNIT_GROUP("unit group data set"),

	@XmlEnumValue("contact data set")
	CONTACT("contact data set"),

	@XmlEnumValue("LCIA method data set")
	IMPACT_METHOD("LCIA method data set"),

	@XmlEnumValue("other external file")
	EXTERNAL_FILE("other external file"),

	@XmlEnumValue("life cycle model")
	MODEL("life cycle model");

	private final String value;

	DataSetType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static Optional<DataSetType> fromValue(String v) {
		if (Strings.nullOrEmpty(v))
			return Optional.empty();
		for (var type : DataSetType.values()) {
			if (type.value.equals(v)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	public static DataSetType of(IDataSet ds) {
		if (ds == null)
			return null;
		if (ds instanceof Contact)
			return DataSetType.CONTACT;
		if (ds instanceof FlowProperty)
			return DataSetType.FLOW_PROPERTY;
		if (ds instanceof Flow)
			return DataSetType.FLOW;
		if (ds instanceof ImpactMethod)
			return DataSetType.IMPACT_METHOD;
		if (ds instanceof Model)
			return DataSetType.MODEL;
		if (ds instanceof Process)
			return DataSetType.PROCESS;
		if (ds instanceof Source)
			return DataSetType.SOURCE;
		if (ds instanceof UnitGroup)
			return DataSetType.UNIT_GROUP;
		return null;
	}

	@Override
	public String toString() {
		return value;
	}
}
