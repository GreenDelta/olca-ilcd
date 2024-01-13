package org.openlca.ilcd.util;

import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.methods.AdminInfo;
import org.openlca.ilcd.methods.DataEntry;
import org.openlca.ilcd.methods.DataSetInfo;
import org.openlca.ilcd.methods.Factor;
import org.openlca.ilcd.methods.FactorList;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.methods.MethodInfo;
import org.openlca.ilcd.methods.Publication;
import org.openlca.ilcd.methods.QuantitativeReference;
import org.openlca.ilcd.methods.Time;

public final class Methods {

	private Methods() {
	}

	public static MethodInfo getMethodInfo(ImpactMethod m) {
		return m != null
			? m.methodInfo
			: null;
	}

	public static MethodInfo forceMethodInfo(ImpactMethod m) {
		if (m.methodInfo == null)
			m.methodInfo = new MethodInfo();
		return m.methodInfo;
	}

	public static Time getTime(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info == null
			? null
			: info.time;
	}

	public static Time forceTime(ImpactMethod m) {
		var info = forceMethodInfo(m);
		if (info.time == null) {
			info.time = new Time();
		}
		return info.time;
	}

	public static QuantitativeReference getQuantitativeReference(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info != null
			? info.quantitativeReference
			: null;
	}

	public static QuantitativeReference forceQuantitativeReference(ImpactMethod m) {
		var info = forceMethodInfo(m);
		if (info.quantitativeReference == null) {
			info.quantitativeReference = new QuantitativeReference();
		}
		return info.quantitativeReference;
	}

	public static DataSetInfo getDataSetInfo(ImpactMethod m) {
		var info = getMethodInfo(m);
		return info != null
			? info.dataSetInfo
			: null;
	}

	public static DataSetInfo forceDataSetInfo(ImpactMethod m) {
		var info = forceMethodInfo(m);
		if (info.dataSetInfo == null) {
			info.dataSetInfo = new DataSetInfo();
		}
		return info.dataSetInfo;
	}

	public static AdminInfo getAdminInfo(ImpactMethod m) {
		return m != null
			? m.adminInfo
			: null;
	}

	public static AdminInfo forceAdminInfo(ImpactMethod m) {
		if (m.adminInfo == null) {
			m.adminInfo = new AdminInfo();
		}
		return m.adminInfo;
	}

	public static DataEntry getDataEntry(ImpactMethod m) {
		var info = getAdminInfo(m);
		return info != null
			? info.dataEntry
			: null;
	}

	public static DataEntry forceDataEntry(ImpactMethod m) {
		var info = forceAdminInfo(m);
		if (info.dataEntry == null) {
			info.dataEntry = new DataEntry();
		}
		return info.dataEntry;
	}

	public static Publication getPublication(ImpactMethod m) {
		var info = getAdminInfo(m);
		return info != null
			? info.publication
			: null;
	}

	public static Publication forcePublication(ImpactMethod m) {
		var info = forceAdminInfo(m);
		if (info.publication == null) {
			info.publication = new Publication();
		}
		return info.publication;
	}

	public static List<Factor> getFactors(ImpactMethod m) {
		return m == null || m.characterisationFactors == null
			? Collections.emptyList()
			: m.characterisationFactors.factors;
	}

	public static List<Factor> forceFactors(ImpactMethod m) {
		if (m.characterisationFactors == null) {
			m.characterisationFactors = new FactorList();
		}
		return m.characterisationFactors.factors;
	}

}
