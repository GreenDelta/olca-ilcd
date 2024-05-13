package org.openlca.ilcd.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.flows.AdminInfo;
import org.openlca.ilcd.flows.DataEntry;
import org.openlca.ilcd.flows.DataSetInfo;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.flows.FlowCategoryInfo;
import org.openlca.ilcd.flows.FlowInfo;
import org.openlca.ilcd.flows.FlowName;
import org.openlca.ilcd.flows.FlowPropertyRef;
import org.openlca.ilcd.flows.Geography;
import org.openlca.ilcd.flows.InventoryMethod;
import org.openlca.ilcd.flows.Modelling;
import org.openlca.ilcd.flows.QuantitativeReference;
import org.openlca.ilcd.flows.Technology;

public final class Flows {

	private Flows() {
	}

	public static String getUUID(Flow f) {
		var info = getDataSetInfo(f);
		return info != null ? info.getUUID() : null;
	}

	public static void withUUID(Flow f, String uuid) {
		withDataSetInfo(f).withUUID(uuid);
	}

	public static String getVersion(Flow f) {
		var pub = getPublication(f);
		return pub != null ? pub.getVersion() : null;
	}

	public static void withVersion(Flow f, String version) {
		withPublication(f).withVersion(version);
	}

	public static List<LangString> getBaseName(Flow f) {
		var name = getFlowName(f);
		return name != null
			? name.getBaseName()
			: Collections.emptyList();
	}

	public static List<LangString> withBaseName(Flow f) {
		return withFlowName(f).withBaseName();
	}

	public static void withBaseName(Flow f, LangString name) {
		var names = withBaseName(f);
		names.clear();
		if (name != null) {
			names.add(name);
		}
	}

	public static String getUri(Flow f) {
		var pub =	getPublication(f);
		return pub != null ? pub.getUri() : null;
	}

	public static void withUri(Flow f, String uri) {
		withPublication(f).withUri(uri);
	}

	public static XMLGregorianCalendar getTimeStamp(Flow f) {
		var entry = getDataEntry(f);
		return entry != null
			? entry.getTimeStamp()
			: null;
	}

	public static void withTimeStamp(Flow f, XMLGregorianCalendar t) {
		withDataEntry(f).withTimeStamp(t);
	}

	public static List<Classification> getClassifications(Flow f) {
		var info = getCategoryInfo(f);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static List<Classification> withClassifications(Flow f) {
		return withCategoryInfo(f).withClassifications();
	}

	public static AdminInfo getAdminInfo(Flow f) {
		return f != null
			? f.getAdminInfo()
			: null;
	}

	public static DataEntry getDataEntry(Flow f) {
		AdminInfo ai = getAdminInfo(f);
		return ai != null
			? ai.getDataEntry()
			: null;
	}

	public static DataEntry withDataEntry(Flow f) {
		return f.withAdminInfo().withDataEntry();
	}

	public static Publication getPublication(Flow f) {
		AdminInfo ai = getAdminInfo(f);
		return ai != null
			? ai.getPublication()
			: null;
	}

	public static Publication withPublication(Flow f) {
		return f.withAdminInfo().withPublication();
	}

	public static FlowInfo getFlowInfo(Flow f) {
		return f != null
			? f.getFlowInfo()
			: null;
	}

	public static DataSetInfo getDataSetInfo(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.getDataSetInfo()
			: null;
	}

	public static DataSetInfo withDataSetInfo(Flow f) {
		return f.withFlowInfo().withDataSetInfo();
	}

	public static FlowName getFlowName(Flow f) {
		DataSetInfo dsi = getDataSetInfo(f);
		return dsi != null
			? dsi.getFlowName()
			: null;
	}

	public static FlowName withFlowName(Flow f) {
		return withDataSetInfo(f).withFlowName();
	}

	public static String getFullName(Flow f, String lang) {
		var name = getFlowName(f);
		if (name == null)
			return null;
		return Stream.of(
				name.getBaseName(),
				name.getFlowProperties(),
				name.getMixAndLocationTypes(),
				name.getTreatmentStandardsRoutes())
			.map(strings -> LangString.getOrDefault(strings, lang))
			.filter(Strings::notEmpty)
			.collect(Collectors.joining(", "));
	}

	public static FlowCategoryInfo getCategoryInfo(Flow f) {
		var info = getDataSetInfo(f);
		return info != null
			? info.getClassificationInformation()
			: null;
	}

	public static FlowCategoryInfo withCategoryInfo(Flow f) {
		return withDataSetInfo(f).withClassificationInformation();
	}

	public static QuantitativeReference getQuantitativeReference(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.getQuantitativeReference()
			: null;
	}

	public static QuantitativeReference withQuantitativeReference(Flow f) {
		return f.withFlowInfo().withQuantitativeReference();
	}

	/**
	 * Returns the data set internal ID of the reference flow property of the
	 * given flow or null if it is not defined.
	 */
	public static Integer getReferenceFlowPropertyID(Flow f) {
		var qref = getQuantitativeReference(f);
		return qref != null
			? qref.getReferenceFlowProperty()
			: null;
	}

	public static Geography getGeography(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.getGeography()
			: null;
	}

	public static Geography withGeography(Flow f) {
		return f.withFlowInfo().withGeography();
	}

	public static Technology getTechnology(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.getTechnology()
			: null;
	}

	public static Technology withTechnology(Flow f) {
		return f.withFlowInfo().withTechnology();
	}

	public static Modelling getModelling(Flow f) {
		return f != null
			? f.getModelling()
			: null;
	}

	public static InventoryMethod getInventoryMethod(Flow f) {
		Modelling m = getModelling(f);
		return m != null
			? m.getInventoryMethod()
			: null;
	}

	public static InventoryMethod withInventoryMethod(Flow f) {
		return f.withModelling().withInventoryMethod();
	}

	public static FlowType getFlowType(Flow f) {
		var m = getInventoryMethod(f);
		return m != null
			? m.getFlowType()
			: null;
	}

	public static void withFlowType(Flow flow, FlowType type) {
		withInventoryMethod(flow).withFlowType(type);
	}

	public static List<FlowPropertyRef> getFlowProperties(Flow f) {
		return f != null
			? f.getFlowProperties()
			: Collections.emptyList();
	}

	public static FlowPropertyRef getReferenceFlowProperty(Flow f) {
		Integer qref = getReferenceFlowPropertyID(f);
		if (qref == null)
			return null;
		for (FlowPropertyRef ref : getFlowProperties(f)) {
			var id = ref.getDataSetInternalID();
			if (id != null && id.intValue() == qref.intValue())
				return ref;
		}
		return null;
	}

}
