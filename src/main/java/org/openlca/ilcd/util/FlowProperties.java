package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.flowproperties.AdminInfo;
import org.openlca.ilcd.flowproperties.DataSetInfo;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flowproperties.FlowPropertyInfo;
import org.openlca.ilcd.flowproperties.QuantitativeReference;

public final class FlowProperties {

	private FlowProperties() {
	}

	public static FlowPropertyInfo getFlowPropertyInfo(FlowProperty fp) {
		return fp != null
			? fp.flowPropertyInfo
			: null;
	}

	public static FlowPropertyInfo forceFlowPropertyInfo(FlowProperty fp) {
		if (fp.flowPropertyInfo == null) {
			fp.flowPropertyInfo = new FlowPropertyInfo();
		}
		return fp.flowPropertyInfo;
	}

	public static QuantitativeReference getQuantitativeReference(
		FlowProperty fp) {
		var info = getFlowPropertyInfo(fp);
		return info != null
			? info.quantitativeReference
			: null;
	}

	public static QuantitativeReference forceQuantitativeReference(
		FlowProperty fp) {
		var info = forceFlowPropertyInfo(fp);
		if (info.quantitativeReference == null) {
			info.quantitativeReference = new QuantitativeReference();
		}
		return info.quantitativeReference;
	}

	public static Ref getUnitGroupRef(FlowProperty fp) {
		var qref = getQuantitativeReference(fp);
		return qref != null
			? qref.unitGroup
			: null;
	}

	public static Ref forceUnitGroupRef(FlowProperty fp) {
		var qref = forceQuantitativeReference(fp);
		if (qref.unitGroup == null) {
			qref.unitGroup = new Ref();
			qref.unitGroup.type = DataSetType.UNIT_GROUP;
		}
		return qref.unitGroup;
	}

	public static DataSetInfo getDataSetInfo(FlowProperty fp) {
		var info = getFlowPropertyInfo(fp);
		return info != null
			? info.dataSetInfo
			: null;
	}

	public static DataSetInfo forceDataSetInfo(FlowProperty fp) {
		var info = forceFlowPropertyInfo(fp);
		if (info.dataSetInfo == null) {
			info.dataSetInfo = new DataSetInfo();
		}
		return info.dataSetInfo;
	}

	public static AdminInfo getAdminInfo(FlowProperty fp) {
		return fp != null
			? fp.adminInfo
			: null;
	}

	public static AdminInfo forceAdminInfo(FlowProperty fp) {
		if (fp.adminInfo == null) {
			fp.adminInfo = new AdminInfo();
		}
		return fp.adminInfo;
	}

	public static DataEntry getDataEntry(FlowProperty fp) {
		var info = getAdminInfo(fp);
		return info != null
			? info.dataEntry
			: null;
	}

	public static DataEntry forceDataEntry(FlowProperty fp) {
		var info = forceAdminInfo(fp);
		if (info.dataEntry == null) {
			info.dataEntry = new DataEntry();
		}
		return info.dataEntry;
	}

	public static Publication getPublication(FlowProperty fp) {
		var info = getAdminInfo(fp);
		return info != null
			? info.publication
			: null;
	}

	public static Publication forcePublication(FlowProperty fp) {
		var info = forceAdminInfo(fp);
		if (info.publication == null) {
			info.publication = new Publication();
		}
		return info.publication;
	}

}
