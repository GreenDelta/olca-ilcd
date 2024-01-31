package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.units.AdminInfo;
import org.openlca.ilcd.units.DataSetInfo;
import org.openlca.ilcd.units.QuantitativeReference;
import org.openlca.ilcd.units.Unit;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.units.UnitGroupInfo;

import java.util.List;

public final class UnitGroups {

	private UnitGroups() {
	}

	public static UnitGroupInfo getUnitGroupInfo(UnitGroup u) {
		return u != null
			? u.getUnitGroupInfo()
			: null;
	}

	public static UnitGroupInfo withUnitGroupInfo(UnitGroup u) {
		return u.withUnitGroupInfo();
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

	public static AdminInfo withAdminInfo(UnitGroup u) {
		return u.withAdminInfo();
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
		return u != null ? u.getUnits() : List.of();
	}

	public static List<Unit> withUnits(UnitGroup u) {
		return u.withUnits();
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
