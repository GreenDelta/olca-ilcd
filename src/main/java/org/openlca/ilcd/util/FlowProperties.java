package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.flowproperties.AdminInfo;
import org.openlca.ilcd.flowproperties.DataSetInfo;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flowproperties.FlowPropertyInfo;
import org.openlca.ilcd.flowproperties.QuantitativeReference;

import java.util.Collections;
import java.util.List;

public final class FlowProperties {

	private FlowProperties() {
	}

	public static String getUUID(FlowProperty fp) {
		var info = getDataSetInfo(fp);
		return info != null ? info.getUUID() : null;
	}

	public static String getVersion(FlowProperty fp) {
		var pub = getPublication(fp);
		return pub != null ? pub.getVersion() : null;
	}

	public static List<LangString> getName(FlowProperty fp) {
		var info = getDataSetInfo(fp);
		return info != null
			? info.getName()
			: Collections.emptyList();
	}

	public static String getUri(FlowProperty fp) {
		var pub = getPublication(fp);
		return pub != null ? pub.getUri() : null;
	}

	public static List<Classification> getClassifications(FlowProperty fp) {
		var info = getDataSetInfo(fp);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static FlowPropertyInfo getFlowPropertyInfo(FlowProperty fp) {
		return fp != null
			? fp.getFlowPropertyInfo()
			: null;
	}

	public static QuantitativeReference getQuantitativeReference(
		FlowProperty fp) {
		var info = getFlowPropertyInfo(fp);
		return info != null
			? info.getQuantitativeReference()
			: null;
	}

	public static Ref getUnitGroupRef(FlowProperty fp) {
		var qref = getQuantitativeReference(fp);
		return qref != null
			? qref.getUnitGroup()
			: null;
	}

	public static DataSetInfo getDataSetInfo(FlowProperty fp) {
		var info = getFlowPropertyInfo(fp);
		return info != null
			? info.getDataSetInfo()
			: null;
	}

	public static AdminInfo getAdminInfo(FlowProperty fp) {
		return fp != null
			? fp.getAdminInfo()
			: null;
	}

	public static DataEntry getDataEntry(FlowProperty fp) {
		var info = getAdminInfo(fp);
		return info != null
			? info.getDataEntry()
			: null;
	}

	public static Publication getPublication(FlowProperty fp) {
		var info = getAdminInfo(fp);
		return info != null
			? info.getPublication()
			: null;
	}

}
