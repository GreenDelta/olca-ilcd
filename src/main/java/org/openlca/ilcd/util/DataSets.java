package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.IDataSet;
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
