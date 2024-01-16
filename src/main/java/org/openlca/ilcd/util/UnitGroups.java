package org.openlca.ilcd.util;

import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.units.AdminInfo;
import org.openlca.ilcd.units.DataSetInfo;
import org.openlca.ilcd.units.QuantitativeReference;
import org.openlca.ilcd.units.Unit;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.units.UnitGroupInfo;
import org.openlca.ilcd.units.UnitList;

public final class UnitGroups {

	private UnitGroups() {
	}

	public static UnitGroupInfo getUnitGroupInfo(UnitGroup u) {
		return u != null
			? u.unitGroupInfo
			: null;
	}

	public static UnitGroupInfo forceUnitGroupInfo(UnitGroup u) {
		if (u.unitGroupInfo == null) {
			u.unitGroupInfo = new UnitGroupInfo();
		}
		return u.unitGroupInfo;
	}

	public static DataSetInfo getDataSetInfo(UnitGroup u) {
		var info = getUnitGroupInfo(u);
		return info != null
			? info.dataSetInfo
			: null;
	}

	public static DataSetInfo foceDataSetInfo(UnitGroup u) {
		var info = forceUnitGroupInfo(u);
		if (info.dataSetInfo == null) {
			info.dataSetInfo = new DataSetInfo();
		}
		return info.dataSetInfo;
	}

	public static QuantitativeReference getQuantitativeReference(UnitGroup u) {
		var info = getUnitGroupInfo(u);
		return info != null
			? info.quantitativeReference
			: null;
	}

	public static QuantitativeReference forceQuantitativeReference(UnitGroup u) {
		var info = forceUnitGroupInfo(u);
		if (info.quantitativeReference == null) {
			info.quantitativeReference = new QuantitativeReference();
		}
		return info.quantitativeReference;
	}

	public static AdminInfo getAdminInfo(UnitGroup u) {
		return u != null
			? u.adminInfo
			: null;
	}

	public static AdminInfo forceAdminInfo(UnitGroup u) {
		if (u.adminInfo == null) {
			u.adminInfo = new AdminInfo();
		}
		return u.adminInfo;
	}

	public static DataEntry getDataEntry(UnitGroup u) {
		var info = getAdminInfo(u);
		return info != null
			? info.dataEntry
			: null;
	}

	public static DataEntry forceDataEntry(UnitGroup u) {
		var info = forceAdminInfo(u);
		if (info.dataEntry == null) {
			info.dataEntry = new DataEntry();
		}
		return info.dataEntry;
	}

	public static Publication getPublication(UnitGroup u) {
		var info = getAdminInfo(u);
		return info != null
			? info.publication
			: null;
	}

	public static Publication forcePublication(UnitGroup u) {
		var info = forceAdminInfo(u);
		if (info.publication == null) {
			info.publication = new Publication();
		}
		return info.publication;
	}

	public static List<Unit> getUnits(UnitGroup u) {
		return u == null || u.unitList == null
			? Collections.emptyList()
			: u.unitList.units;
	}

	public static List<Unit> forceUnits(UnitGroup u) {
		if (u.unitList == null) {
			u.unitList = new UnitList();
		}
		return u.unitList.units;
	}

	public static Unit getReferenceUnit(UnitGroup u) {
		var qRef = getQuantitativeReference(u);
		if (qRef == null)
			return null;
		for (Unit unit : getUnits(u)) {
			if (unit == null)
				continue;
			if (unit.id == qRef.referenceUnit)
				return unit;
		}
		return null;
	}
}
