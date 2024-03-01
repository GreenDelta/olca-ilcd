package org.openlca.ilcd.util;

import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.models.AdminInfo;
import org.openlca.ilcd.models.DataEntry;
import org.openlca.ilcd.models.DataSetInfo;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.models.ModelInfo;
import org.openlca.ilcd.models.ModelName;
import org.openlca.ilcd.models.ProcessInstance;
import org.openlca.ilcd.models.Publication;
import org.openlca.ilcd.models.QuantitativeReference;
import org.openlca.ilcd.models.Technology;

import javax.xml.datatype.XMLGregorianCalendar;

public class Models {

	public static String getUUID(Model model) {
		var info = getDataSetInfo(model);
		return info != null
			? info.getUUID()
			: null;
	}

	public static void withUUID(Model m, String uuid) {
		withDataSetInfo(m).withUUID(uuid);
	}

	public static String getVersion(Model m) {
		var pub = getPublication(m);
		return pub != null ? pub.getVersion() : null;
	}

	public static void withVersion(Model m, String version) {
		withPublication(m).withVersion(version);
	}

	public static List<LangString> getBaseName(Model m) {
		var name = getModelName(m);
		return name != null
			? name.getBaseName()
			: Collections.emptyList();
	}

	public static List<LangString> withBaseName(Model m) {
		return withModelName(m).withBaseName();
	}

	public static void withBaseName(Model m, LangString name) {
		var names = withBaseName(m);
		names.clear();
		if (name != null) {
			names.add(name);
		}
	}

	public static String getUri(Model m) {
		var pub =	getPublication(m);
		return pub != null ? pub.getUri() : null;
	}

	public static void withUri(Model m, String uri) {
		withPublication(m).withUri(uri);
	}

	public static XMLGregorianCalendar getTimeStamp(Model m) {
		var entry = getDataEntry(m);
		return entry != null
			? entry.getTimeStamp()
			: null;
	}

	public static void withTimeStamp(Model m, XMLGregorianCalendar t) {
		withDataEntry(m).withTimeStamp(t);
	}

	public static List<Classification> getClassifications(Model model) {
		DataSetInfo di = getDataSetInfo(model);
		return di != null
			? di.getClassifications()
			: Collections.emptyList();
	}

	public static List<Classification> withClassifications(Model m) {
		return withDataSetInfo(m).withClassifications();
	}

	public static String getOrigin(Model model) {
		return model != null
			? Extensions.getString(model.getOtherAttributes(), "origin")
			: null;
	}

	public static void setOrigin(Model model, String value) {
		if (model == null)
			return;
		Extensions.setString(model::withOtherAttributes, "origin", value);
	}

	public static ModelInfo getModelInfo(Model model) {
		if (model == null)
			return null;
		return model.getInfo();
	}

	public static DataSetInfo getDataSetInfo(Model model) {
		var mi = getModelInfo(model);
		return mi != null
			? mi.getDataSetInfo()
			: null;
	}

	public static DataSetInfo withDataSetInfo(Model model) {
		return model.withInfo().withDataSetInfo();
	}

	public static ModelName getModelName(Model model) {
		DataSetInfo di = getDataSetInfo(model);
		if (di == null)
			return null;
		return di.getModelName();
	}

	public static ModelName withModelName(Model model) {
		return withDataSetInfo(model).withModelName();
	}

	public static QuantitativeReference getQuantitativeReference(Model m) {
		ModelInfo mi = getModelInfo(m);
		if (mi == null)
			return null;
		return mi.getQuantitativeReference();
	}

	public static QuantitativeReference withQuantitativeReference(Model m) {
		return m.withInfo().withQuantitativeReference();
	}

	public static Technology getTechnology(Model m) {
		ModelInfo mi = getModelInfo(m);
		if (mi == null)
			return null;
		return mi.getTechnology();
	}

	public static Technology withTechnology(Model m) {
		return m.withInfo().withTechnology();
	}

	public static AdminInfo getAdminInfo(Model m) {
		if (m == null)
			return null;
		return m.getAdminInfo();
	}

	public static DataEntry getDataEntry(Model m) {
		AdminInfo ai = getAdminInfo(m);
		if (ai == null)
			return null;
		return ai.getDataEntry();
	}

	public static DataEntry withDataEntry(Model m) {
		return m.withAdminInfo().withDataEntry();
	}

	public static Publication getPublication(Model m) {
		AdminInfo ai = getAdminInfo(m);
		if (ai == null)
			return null;
		return ai.getPublication();
	}

	public static Publication withPublication(Model m) {
		return m.withAdminInfo().withPublication();
	}

	public static List<ProcessInstance> getProcesses(Model m) {
		var tech = getTechnology(m);
		return tech == null
			? Collections.emptyList()
			: tech.getProcesses();
	}

	public static List<ProcessInstance> withProcesses(Model m) {
		return withTechnology(m).withProcesses();
	}

}
