package org.openlca.ilcd.util;

import org.openlca.ilcd.methods.AdminInfo;
import org.openlca.ilcd.methods.DataEntry;
import org.openlca.ilcd.methods.DataSetInfo;
import org.openlca.ilcd.methods.Factor;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.methods.MethodInfo;
import org.openlca.ilcd.methods.Publication;
import org.openlca.ilcd.methods.QuantitativeReference;
import org.openlca.ilcd.methods.Time;

import java.util.List;

public final class Methods {

	private Methods() {
	}

	public static MethodInfo getMethodInfo(ImpactMethod m) {
		return m != null
			? m.getMethodInfo()
			: null;
	}

	public static MethodInfo withMethodInfo(ImpactMethod m) {
		return m.withMethodInfo();
	}

	public static Time getTime(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info == null
			? null
			: info.getTime();
	}

	public static Time withTime(ImpactMethod m) {
		return m.withMethodInfo().withTime();
	}

	public static QuantitativeReference getQuantitativeReference(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info != null
			? info.getQuantitativeReference()
			: null;
	}

	public static QuantitativeReference withQuantitativeReference(ImpactMethod m) {
		return m.withMethodInfo().withQuantitativeReference();
	}

	public static DataSetInfo getDataSetInfo(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info != null
			? info.getDataSetInfo()
			: null;
	}

	public static DataSetInfo withDataSetInfo(ImpactMethod m) {
		return m.withMethodInfo().withDataSetInfo();
	}

	public static AdminInfo getAdminInfo(ImpactMethod m) {
		return m != null
			? m.getAdminInfo()
			: null;
	}

	public static AdminInfo withAdminInfo(ImpactMethod m) {
		return m.withAdminInfo();
	}

	public static DataEntry getDataEntry(ImpactMethod m) {
		var info = getAdminInfo(m);
		return info != null
			? info.getDataEntry()
			: null;
	}

	public static DataEntry withDataEntry(ImpactMethod m) {
		return m.withAdminInfo().withDataEntry();
	}

	public static Publication getPublication(ImpactMethod m) {
		var info = getAdminInfo(m);
		return info != null
			? info.getPublication()
			: null;
	}

	public static Publication withPublication(ImpactMethod m) {
		return m.withAdminInfo().withPublication();
	}

	public static List<Factor> getFactors(ImpactMethod m) {
		return m.getFactors();
	}

	public static List<Factor> withFactors(ImpactMethod m) {
		return m.withFactors();
	}

}
