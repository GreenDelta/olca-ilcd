
package org.openlca.ilcd.commons;

import java.util.Optional;

import org.openlca.commons.Strings;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

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
		if (Strings.isBlank(v))
			return Optional.empty();
		for (var type : DataSetType.values()) {
			if (type.value.equals(v)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}

	public static DataSetType of(IDataSet ds) {
		return ds != null
			? of(ds.getClass())
			: null;
	}

	public static DataSetType of(Class<? extends IDataSet> c) {
		if (c == Contact.class)
			return DataSetType.CONTACT;
		if (c == FlowProperty.class)
			return DataSetType.FLOW_PROPERTY;
		if (c == Flow.class)
			return DataSetType.FLOW;
		if (c == ImpactMethod.class)
			return DataSetType.IMPACT_METHOD;
		if (c == Model.class)
			return DataSetType.MODEL;
		if (c == Process.class)
			return DataSetType.PROCESS;
		if (c == Source.class)
			return DataSetType.SOURCE;
		if (c == UnitGroup.class)
			return DataSetType.UNIT_GROUP;
		return null;
	}

	public Class<? extends IDataSet> getDataSetClass() {
		return switch (this) {
			case CONTACT -> Contact.class;
			case FLOW_PROPERTY -> FlowProperty.class;
			case FLOW -> Flow.class;
			case IMPACT_METHOD -> ImpactMethod.class;
			case MODEL -> Model.class;
			case PROCESS -> Process.class;
			case SOURCE -> Source.class;
			case UNIT_GROUP -> UnitGroup.class;
			case EXTERNAL_FILE -> null;
		};
	}

	@Override
	public String toString() {
		return value;
	}
}
