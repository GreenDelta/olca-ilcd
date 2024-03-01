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

import javax.xml.datatype.XMLGregorianCalendar;
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

	public static void withUUID(ImpactMethod m, String uuid) {
		withDataSetInfo(m).withUUID(uuid);
	}

	public static String getVersion(ImpactMethod m) {
		var pub = getPublication(m);
		return pub != null ? pub.getVersion() : null;
	}

	public static void withVersion(ImpactMethod m, String version) {
		withPublication(m).withVersion(version);
	}

	public static List<LangString> getName(ImpactMethod m) {
		var info = getDataSetInfo(m);
		return info != null
			? info.getName()
			: Collections.emptyList();
	}

	public static List<LangString> withName(ImpactMethod m) {
		return withDataSetInfo(m).withName();
	}

	public static void withName(ImpactMethod m, LangString name) {
		var names = withName(m);
		names.clear();
		if (name != null) {
			names.add(name);
		}
	}

	public static String getUri(ImpactMethod m) {
		var pub =	getPublication(m);
		return pub != null ? pub.getUri() : null;
	}

	public static void withUri(ImpactMethod m, String uri) {
		withPublication(m).withUri(uri);
	}

	public static XMLGregorianCalendar getTimeStamp(ImpactMethod m) {
		var entry = getDataEntry(m);
		return entry != null
			? entry.getTimeStamp()
			: null;
	}

	public static void withTimeStamp(ImpactMethod m, XMLGregorianCalendar t) {
		withDataEntry(m).withTimeStamp(t);
	}

	public static List<Classification> getClassifications(ImpactMethod m) {
		var info = getDataSetInfo(m);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static List<Classification> withClassifications(ImpactMethod m) {
		return withDataSetInfo(m).withClassifications();
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

}
