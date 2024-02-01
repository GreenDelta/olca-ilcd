package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import java.util.Collections;
import java.util.List;

public final class DataSets {

	private DataSets() {
	}

	public static DataSetType getType(IDataSet ds) {
		if (ds instanceof Contact)
			return DataSetType.CONTACT;
		if (ds instanceof Flow)
			return DataSetType.FLOW;
		if (ds instanceof FlowProperty)
			return DataSetType.FLOW_PROPERTY;
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

	public static String getUUID(IDataSet ds) {
		if (ds instanceof Contact c)
			return Contacts.getUUID(c);
		if (ds instanceof Flow f)
			return Flows.getUUID(f);
		if (ds instanceof FlowProperty fp)
			return FlowProperties.getUUID(fp);
		if (ds instanceof ImpactMethod method)
			return ImpactMethods.getUUID(method);
		if (ds instanceof Model model)
			return Models.getUUID(model);
		if (ds instanceof Process p)
			return Processes.getUUID(p);
		if (ds instanceof Source s)
			return Sources.getUUID(s);
		if (ds instanceof UnitGroup ug)
			return UnitGroups.getUUID(ug);
		return null;
	}

	public static String getVersion(IDataSet ds) {
		if (ds instanceof Contact c)
			return Contacts.getVersion(c);
		if (ds instanceof Flow f)
			return Flows.getVersion(f);
		if (ds instanceof FlowProperty fp)
			return FlowProperties.getVersion(fp);
		if (ds instanceof ImpactMethod method)
			return ImpactMethods.getVersion(method);
		if (ds instanceof Model model)
			return Models.getVersion(model);
		if (ds instanceof Process p)
			return Processes.getVersion(p);
		if (ds instanceof Source s)
			return Sources.getVersion(s);
		if (ds instanceof UnitGroup ug)
			return UnitGroups.getVersion(ug);
		return null;
	}

	public static List<LangString> getBaseName(IDataSet ds) {
		if (ds instanceof Contact c)
			return Contacts.getName(c);
		if (ds instanceof Flow f)
			return Flows.getBaseName(f);
		if (ds instanceof FlowProperty fp)
			return FlowProperties.getName(fp);
		if (ds instanceof ImpactMethod method)
			return ImpactMethods.getName(method);
		if (ds instanceof Model model)
			return Models.getBaseName(model);
		if (ds instanceof Process p)
			return Processes.getBaseName(p);
		if (ds instanceof Source s)
			return Sources.getName(s);
		if (ds instanceof UnitGroup ug)
			return UnitGroups.getName(ug);
		return null;
	}

	public static String getUri(IDataSet ds) {
		if (ds instanceof Contact c)
			return Contacts.getUri(c);
		if (ds instanceof Flow f)
			return Flows.getUri(f);
		if (ds instanceof FlowProperty fp)
			return FlowProperties.getUri(fp);
		if (ds instanceof ImpactMethod method)
			return ImpactMethods.getUri(method);
		if (ds instanceof Model model)
			return Models.getUri(model);
		if (ds instanceof Process p)
			return Processes.getUri(p);
		if (ds instanceof Source s)
			return Sources.getUri(s);
		if (ds instanceof UnitGroup ug)
			return UnitGroups.getUri(ug);
		return null;
	}

	public static List<Classification> getClassifications(IDataSet ds) {
		if (ds instanceof Contact c)
			return Contacts.getClassifications(c);
		if (ds instanceof Flow f)
			return Flows.getClassifications(f);
		if (ds instanceof FlowProperty fp)
			return FlowProperties.getClassifications(fp);
		if (ds instanceof ImpactMethod method)
			return ImpactMethods.getClassifications(method);
		if (ds instanceof Model model)
			return Models.getClassifications(model);
		if (ds instanceof Process p)
			return Processes.getClassifications(p);
		if (ds instanceof Source s)
			return Sources.getClassifications(s);
		if (ds instanceof UnitGroup ug)
			return UnitGroups.getClassifications(ug);
		return Collections.emptyList();
	}
}
