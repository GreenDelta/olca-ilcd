package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.units.AdminInfo;
import org.openlca.ilcd.units.DataSetInfo;
import org.openlca.ilcd.units.QuantitativeReference;
import org.openlca.ilcd.units.Unit;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.units.UnitGroupInfo;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collections;
import java.util.List;

public final class UnitGroups {

	private UnitGroups() {
	}

	public static String getUUID(UnitGroup u) {
		var info = getDataSetInfo(u);
		return info != null
			? info.getUUID()
			: null;
	}

	public static String getVersion(UnitGroup u) {
		var pub = getPublication(u);
		return pub != null ? pub.getVersion() : null;
	}

	public static List<LangString> getName(UnitGroup u) {
		var info = getDataSetInfo(u);
		return info != null
			? info.getName()
			: Collections.emptyList();
	}

	public static String getUri(UnitGroup u) {
		var pub =	getPublication(u);
		return pub != null ? pub.getUri() : null;
	}

	public static XMLGregorianCalendar getTimeStamp(UnitGroup u) {
		var entry = getDataEntry(u);
		return entry != null
			? entry.getTimeStamp()
			: null;
	}

	public static List<Classification> getClassifications(UnitGroup u) {
		var info = getDataSetInfo(u);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static UnitGroupInfo getUnitGroupInfo(UnitGroup u) {
		return u != null
			? u.getUnitGroupInfo()
			: null;
	}

	public static DataSetInfo getDataSetInfo(UnitGroup u) {
		var info = getUnitGroupInfo(u);
		return info != null
			? info.getDataSetInfo()
			: null;
	}

	public static DataSetInfo withDataSetInfo(UnitGroup u) {
		return u.withUnitGroupInfo().withDataSetInfo();
	}

	public static QuantitativeReference getQuantitativeReference(UnitGroup u) {
		var info = getUnitGroupInfo(u);
		return info != null
			? info.getQuantitativeReference()
			: null;
	}

	public static QuantitativeReference withQuantitativeReference(UnitGroup u) {
		return u.withUnitGroupInfo().withQuantitativeReference();
	}

	public static AdminInfo getAdminInfo(UnitGroup u) {
		return u != null
			? u.getAdminInfo()
			: null;
	}

	public static DataEntry getDataEntry(UnitGroup u) {
		var info = getAdminInfo(u);
		return info != null
			? info.getDataEntry()
			: null;
	}

	public static DataEntry withDataEntry(UnitGroup u) {
		return u.withAdminInfo().withDataEntry();
	}

	public static Publication getPublication(UnitGroup u) {
		var info = getAdminInfo(u);
		return info != null
			? info.getPublication()
			: null;
	}

	public static Publication withPublication(UnitGroup u) {
		return u.withAdminInfo().withPublication();
	}

	public static List<Unit> getUnits(UnitGroup u) {
		return u != null ? u.getUnits() : Collections.emptyList();
	}

	public static Unit getReferenceUnit(UnitGroup u) {
		var qRef = getQuantitativeReference(u);
		if (qRef == null)
			return null;
		for (Unit unit : getUnits(u)) {
			if (unit == null)
				continue;
			if (unit.getId() == qRef.getReferenceUnit())
				return unit;
		}
		return null;
	}
}
