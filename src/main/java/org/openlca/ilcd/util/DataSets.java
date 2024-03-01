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

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
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

	public static void withUUID(IDataSet ds, String uuid) {
		if (ds instanceof Contact c) {
			Contacts.withUUID(c, uuid);
			return;
		}
		if (ds instanceof Flow f) {
			Flows.withUUID(f, uuid);
			return;
		}
		if (ds instanceof FlowProperty fp) {
			FlowProperties.withUUID(fp, uuid);
			return;
		}
		if (ds instanceof ImpactMethod method) {
			ImpactMethods.withUUID(method, uuid);
			return;
		}
		if (ds instanceof Model model) {
			Models.withUUID(model, uuid);
			return;
		}
		if (ds instanceof Process p) {
			Processes.withUUID(p, uuid);
			return;
		}
		if (ds instanceof Source s) {
			Sources.withUUID(s, uuid);
			return;
		}
		if (ds instanceof UnitGroup ug) {
			UnitGroups.withUUID(ug, uuid);
		}
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

	public static void withVersion(IDataSet ds, String version) {
		if (ds instanceof Contact c) {
			Contacts.withVersion(c, version);
			return;
		}
		if (ds instanceof Flow f) {
			Flows.withVersion(f, version);
			return;
		}
		if (ds instanceof FlowProperty fp) {
			FlowProperties.withVersion(fp, version);
			return;
		}
		if (ds instanceof ImpactMethod method) {
			ImpactMethods.withVersion(method, version);
			return;
		}
		if (ds instanceof Model model) {
			Models.withVersion(model, version);
			return;
		}
		if (ds instanceof Process p) {
			Processes.withVersion(p, version);
			return;
		}
		if (ds instanceof Source s) {
			Sources.withVersion(s, version);
			return;
		}
		if (ds instanceof UnitGroup ug) {
			UnitGroups.withVersion(ug, version);
		}
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

	public static void withBaseName(IDataSet ds, LangString name) {
		if (ds instanceof Contact c) {
			Contacts.withName(c, name);
			return;
		}
		if (ds instanceof Flow f) {
			Flows.withBaseName(f, name);
			return;
		}
		if (ds instanceof FlowProperty fp) {
			FlowProperties.withName(fp, name);
			return;
		}
		if (ds instanceof ImpactMethod method) {
			ImpactMethods.withName(method, name);
			return;
		}
		if (ds instanceof Model model) {
			Models.withBaseName(model, name);
			return;
		}
		if (ds instanceof Process p) {
			Processes.withBaseName(p, name);
			return;
		}
		if (ds instanceof Source s) {
			Sources.withName(s, name);
			return;
		}
		if (ds instanceof UnitGroup ug) {
			UnitGroups.withName(ug, name);
		}
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

	public static void withUri(IDataSet ds, String uri) {
		if (ds instanceof Contact c) {
			Contacts.withUri(c, uri);
			return;
		}
		if (ds instanceof Flow f) {
			Flows.withUri(f, uri);
			return;
		}
		if (ds instanceof FlowProperty fp) {
			FlowProperties.withUri(fp, uri);
			return;
		}
		if (ds instanceof ImpactMethod method) {
			ImpactMethods.withUri(method, uri);
			return;
		}
		if (ds instanceof Model model) {
			Models.withUri(model, uri);
			return;
		}
		if (ds instanceof Process p) {
			Processes.withUri(p, uri);
			return;
		}
		if (ds instanceof Source s) {
			Sources.withUri(s, uri);
			return;
		}
		if (ds instanceof UnitGroup ug) {
			UnitGroups.withUri(ug, uri);
		}
	}

	public static XMLGregorianCalendar getTimeStamp(IDataSet ds) {
		if (ds instanceof Contact c)
			return Contacts.getTimeStamp(c);
		if (ds instanceof Flow f)
			return Flows.getTimeStamp(f);
		if (ds instanceof FlowProperty fp)
			return FlowProperties.getTimeStamp(fp);
		if (ds instanceof ImpactMethod method)
			return ImpactMethods.getTimeStamp(method);
		if (ds instanceof Model model)
			return Models.getTimeStamp(model);
		if (ds instanceof Process p)
			return Processes.getTimeStamp(p);
		if (ds instanceof Source s)
			return Sources.getTimeStamp(s);
		if (ds instanceof UnitGroup ug)
			return UnitGroups.getTimeStamp(ug);
		return null;
	}

	public static void withTimeStamp(IDataSet ds, XMLGregorianCalendar t) {
		if (ds instanceof Contact c) {
			Contacts.withTimeStamp(c, t);
			return;
		}
		if (ds instanceof Flow f) {
			Flows.withTimeStamp(f, t);
			return;
		}
		if (ds instanceof FlowProperty fp) {
			FlowProperties.withTimeStamp(fp, t);
			return;
		}
		if (ds instanceof ImpactMethod method) {
			ImpactMethods.withTimeStamp(method, t);
			return;
		}
		if (ds instanceof Model model) {
			Models.withTimeStamp(model, t);
			return;
		}
		if (ds instanceof Process p) {
			Processes.withTimeStamp(p, t);
			return;
		}
		if (ds instanceof Source s) {
			Sources.withTimeStamp(s, t);
			return;
		}
		if (ds instanceof UnitGroup ug) {
			UnitGroups.withTimeStamp(ug, t);
		}
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

	public static List<Classification> withClassifications(IDataSet ds) {
		if (ds instanceof Contact c)
			return Contacts.withClassifications(c);
		if (ds instanceof Flow f)
			return Flows.withClassifications(f);
		if (ds instanceof FlowProperty fp)
			return FlowProperties.withClassifications(fp);
		if (ds instanceof ImpactMethod method)
			return ImpactMethods.withClassifications(method);
		if (ds instanceof Model model)
			return Models.withClassifications(model);
		if (ds instanceof Process p)
			return Processes.withClassifications(p);
		if (ds instanceof Source s)
			return Sources.withClassifications(s);
		if (ds instanceof UnitGroup ug)
			return UnitGroups.withClassifications(ug);
		return new ArrayList<>();
	}
}
