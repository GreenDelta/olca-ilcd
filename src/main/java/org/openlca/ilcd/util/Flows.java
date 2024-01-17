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

	public static AdminInfo getAdminInfo(Flow f) {
		return f != null
			? f.adminInfo
			: null;
	}

	public static AdminInfo forceAdminInfo(Flow f) {
		if (f.adminInfo == null)
			f.adminInfo = new AdminInfo();
		return f.adminInfo;
	}

	public static DataEntry getDataEntry(Flow f) {
		AdminInfo ai = getAdminInfo(f);
		return ai != null
			? ai.dataEntry
			: null;
	}

	public static DataEntry forceDataEntry(Flow f) {
		AdminInfo ai = forceAdminInfo(f);
		if (ai.dataEntry == null)
			ai.dataEntry = new DataEntry();
		return ai.dataEntry;
	}

	public static Publication getPublication(Flow f) {
		AdminInfo ai = getAdminInfo(f);
		return ai != null
			? ai.publication
			: null;
	}

	public static Publication forcePublication(Flow f) {
		AdminInfo ai = forceAdminInfo(f);
		if (ai.publication == null)
			ai.publication = new Publication();
		return ai.publication;
	}

	public static FlowInfo getFlowInfo(Flow f) {
		return f != null
			? f.flowInfo
			: null;
	}

	public static FlowInfo forceFlowInfo(Flow f) {
		if (f.flowInfo == null)
			f.flowInfo = new FlowInfo();
		return f.flowInfo;
	}

	public static DataSetInfo getDataSetInfo(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.dataSetInfo
			: null;
	}

	public static DataSetInfo forceDataSetInfo(Flow f) {
		FlowInfo fi = forceFlowInfo(f);
		if (fi.dataSetInfo == null)
			fi.dataSetInfo = new DataSetInfo();
		return fi.dataSetInfo;
	}

	public static FlowName getFlowName(Flow f) {
		DataSetInfo dsi = getDataSetInfo(f);
		return dsi != null
			? dsi.name
			: null;
	}

	public static String getFullName(Flow f, String... langs) {
		var name = getFlowName(f);
		if (name == null)
			return null;
		return Stream.of(
				name.baseName,
				name.flowProperties,
				name.mixAndLocationTypes,
				name.treatmentStandardsRoutes)
			.map(strings -> LangString.getFirst(strings, langs))
			.filter(Strings::notEmpty)
			.collect(Collectors.joining(", "));
	}

	public static FlowName forceFlowName(Flow f) {
		DataSetInfo dsi = forceDataSetInfo(f);
		if (dsi.name == null)
			dsi.name = new FlowName();
		return dsi.name;
	}

	public static QuantitativeReference getQuantitativeReference(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.quantitativeReference
			: null;
	}

	public static QuantitativeReference forceQuantitativeReference(Flow f) {
		FlowInfo fi = forceFlowInfo(f);
		if (fi.quantitativeReference == null)
			fi.quantitativeReference = new QuantitativeReference();
		return fi.quantitativeReference;
	}

	/**
	 * Returns the data set internal ID of the reference flow property of the
	 * given flow or null if it is not defined.
	 */
	public static Integer getReferenceFlowPropertyID(Flow f) {
		QuantitativeReference qref = getQuantitativeReference(f);
		return qref != null
			? qref.referenceFlowProperty
			: null;
	}

	public static Geography getGeography(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.geography
			: null;
	}

	public static Geography forceGeography(Flow f) {
		FlowInfo fi = forceFlowInfo(f);
		if (fi.geography == null)
			fi.geography = new Geography();
		return fi.geography;
	}

	public static Technology getTechnology(Flow f) {
		FlowInfo fi = getFlowInfo(f);
		return fi != null
			? fi.technology
			: null;
	}

	public static Technology forceTechnology(Flow f) {
		FlowInfo fi = forceFlowInfo(f);
		if (fi.technology == null)
			fi.technology = new Technology();
		return fi.technology;
	}

	public static List<Classification> getClassifications(Flow f) {
		DataSetInfo info = getDataSetInfo(f);
		if (info == null || info.classificationInformation == null)
			return Collections.emptyList();
		return info.classificationInformation.classifications;
	}

	public static List<Classification> forceClassifications(Flow f) {
		DataSetInfo info = forceDataSetInfo(f);
		if (info.classificationInformation == null)
			info.classificationInformation = new FlowCategoryInfo();
		return info.classificationInformation.classifications;
	}

	public static Modelling getModelling(Flow f) {
		return f != null
			? f.modelling
			: null;
	}

	public static Modelling forceModelling(Flow f) {
		if (f.modelling == null)
			f.modelling = new Modelling();
		return f.modelling;
	}

	public static LCIMethod getInventoryMethod(Flow f) {
		Modelling m = getModelling(f);
		return m != null
			? m.lciMethod
			: null;
	}

	public static LCIMethod forceInventoryMethod(Flow f) {
		Modelling m = forceModelling(f);
		if (m.lciMethod == null)
			m.lciMethod = new LCIMethod();
		return m.lciMethod;
	}

	public static FlowType getType(Flow f) {
		LCIMethod m = getInventoryMethod(f);
		return m != null
			? m.flowType
			: null;
	}

	public static List<FlowPropertyRef> getFlowProperties(Flow f) {
		if (f == null || f.flowPropertyList == null)
			return Collections.emptyList();
		return f.flowPropertyList.flowProperties;
	}

	public static List<FlowPropertyRef> forceFlowProperties(Flow f) {
		if (f.flowPropertyList == null)
			f.flowPropertyList = new FlowPropertyList();
		return f.flowPropertyList.flowProperties;
	}

	public static FlowPropertyRef getReferenceFlowProperty(Flow f) {
		Integer qref = getReferenceFlowPropertyID(f);
		if (qref == null)
			return null;
		for (FlowPropertyRef ref : getFlowProperties(f)) {
			if (ref.dataSetInternalID == null)
				continue;
			if (ref.dataSetInternalID.intValue() == qref.intValue())
				return ref;
		}
		return null;
	}

}
