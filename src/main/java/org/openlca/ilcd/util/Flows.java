package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.flows.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Flows {

	private Flows() {
	}

	public static String getUUID(Flow f) {
		var info = getDataSetInfo(f);
		return info != null
			? info.getUUID()
			: null;
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

	public static Publication getPublication(Flow f) {
		AdminInfo ai = getAdminInfo(f);
		return ai != null
			? ai.getPublication()
			: null;
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

	public static FlowName getFlowName(Flow f) {
		DataSetInfo dsi = getDataSetInfo(f);
		return dsi != null
			? dsi.getName()
			: null;
	}

	public static String getFullName(Flow f, String... langs) {
		var name = getFlowName(f);
		if (name == null)
			return null;
		return Stream.of(
				name.getBaseName(),
				name.getFlowProperties(),
				name.getMixAndLocationTypes(),
				name.getTreatmentStandardsRoutes())
			.map(strings -> LangString.getFirst(strings, langs))
			.filter(Strings::notEmpty)
			.collect(Collectors.joining(", "));
	}

	public static QuantitativeReference getQuantitativeReference(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.getQuantitativeReference()
			: null;
	}

	/**
	 * Returns the data set internal ID of the reference flow property of the
	 * given flow or null if it is not defined.
	 */
	public static Integer getReferenceFlowPropertyID(Flow f) {
		QuantitativeReference qref = getQuantitativeReference(f);
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

	public static Technology getTechnology(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.getTechnology()
			: null;
	}

	public static List<Classification> getClassifications(Flow f) {
		DataSetInfo info = getDataSetInfo(f);
		if (info == null || info.getClassificationInformation() == null)
			return Collections.emptyList();
		return info.getClassificationInformation().getClassifications();
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

	public static FlowType getType(Flow f) {
		InventoryMethod m = getInventoryMethod(f);
		return m != null
			? m.getFlowType()
			: null;
	}

	public static List<FlowPropertyRef> getFlowProperties(Flow f) {
		return f != null
			? f.getFlowProperties()
			: List.of();
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
