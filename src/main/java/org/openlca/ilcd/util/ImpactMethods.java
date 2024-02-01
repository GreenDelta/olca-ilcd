package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.methods.AdminInfo;
import org.openlca.ilcd.methods.DataEntry;
import org.openlca.ilcd.methods.DataSetInfo;
import org.openlca.ilcd.methods.Factor;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.methods.MethodInfo;
import org.openlca.ilcd.methods.Publication;
import org.openlca.ilcd.methods.QuantitativeReference;
import org.openlca.ilcd.methods.Time;

import java.util.Collections;
import java.util.List;

public final class ImpactMethods {

	private ImpactMethods() {
	}

	public static String getUUID(ImpactMethod m) {
		var info = getDataSetInfo(m);
		return info != null
			? info.getUUID()
			: null;
	}

	public static String getVersion(ImpactMethod m) {
		var pub = getPublication(m);
		return pub != null ? pub.getVersion() : null;
	}

	public static List<LangString> getName(ImpactMethod m) {
		var info = getDataSetInfo(m);
		return info != null
			? info.getName()
			: Collections.emptyList();
	}

	public static String getUri(ImpactMethod m) {
		var pub =	getPublication(m);
		return pub != null ? pub.getUri() : null;
	}


	public static List<Classification> getClassifications(ImpactMethod m) {
		var info = getDataSetInfo(m);
		return info != null
			? info.getClassifications()
			: List.of();
	}

	public static MethodInfo getMethodInfo(ImpactMethod m) {
		return m != null
			? m.getMethodInfo()
			: null;
	}

	public static Time getTime(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info == null
			? null
			: info.getTime();
	}

	public static QuantitativeReference getQuantitativeReference(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info != null
			? info.getQuantitativeReference()
			: null;
	}

	public static DataSetInfo getDataSetInfo(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info != null
			? info.getDataSetInfo()
			: null;
	}

	public static AdminInfo getAdminInfo(ImpactMethod m) {
		return m != null
			? m.getAdminInfo()
			: null;
	}

	public static DataEntry getDataEntry(ImpactMethod m) {
		var info = getAdminInfo(m);
		return info != null
			? info.getDataEntry()
			: null;
	}

	public static Publication getPublication(ImpactMethod m) {
		var info = getAdminInfo(m);
		return info != null
			? info.getPublication()
			: null;
	}

	public static List<Factor> getFactors(ImpactMethod m) {
		return m.getFactors();
	}

}
