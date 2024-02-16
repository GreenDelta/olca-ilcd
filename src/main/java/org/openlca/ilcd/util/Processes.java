package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.CommissionerAndGoal;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.Time;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Processes {

	private Processes() {
	}

	public static String getUUID(Process p) {
		var info = getDataSetInfo(p);
		return info != null
			? info.getUUID()
			: null;
	}

	public static String getVersion(Process p) {
		var pub = getPublication(p);
		return pub != null ? pub.getVersion() : null;
	}

	public static List<LangString> getBaseName(Process p) {
		var name = getProcessName(p);
		return name != null
			? name.getBaseName()
			: Collections.emptyList();
	}

	public static String getUri(Process p) {
		var pub =	getPublication(p);
		return pub != null ? pub.getUri() : null;
	}

	public static List<Classification> getClassifications(Process p) {
		var info = getDataSetInfo(p);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static ProcessInfo getProcessInfo(Process p) {
		if (p == null)
			return null;
		return p.getProcessInfo();
	}

	public static DataSetInfo getDataSetInfo(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getDataSetInfo();
	}

	public static ProcessName getProcessName(Process p) {
		var info = getDataSetInfo(p);
		if (info == null)
			return null;
		return info.getProcessName();
	}

	public static String getFullName(Process p, String... langs) {
		var name = getProcessName(p);
		if (name == null)
			return null;
		return Stream.of(
				name.getBaseName(),
				name.getMixAndLocation(),
				name.getTechnicalDetails(),
				name.getFlowProperties())
			.map(strings -> LangString.getFirst(strings, langs))
			.filter(s -> s != null && !s.trim().isEmpty())
			.collect(Collectors.joining(", "));
	}

	public static Geography getGeography(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getGeography();
	}

	public static Location getLocation(Process p) {
		var geo = getGeography(p);
		if (geo == null)
			return null;
		return geo.getLocation();
	}

	public static QuantitativeReference getQuantitativeReference(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getQuantitativeReference();
	}

	public static List<Integer> getReferenceFlows(Process p) {
		var qref = getQuantitativeReference(p);
		return qref != null
			? qref.getReferenceFlows()
			: Collections.emptyList();
	}

	public static Technology getTechnology(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getTechnology();
	}

	public static Time getTime(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getTime();
	}

	public static List<Parameter> getParameters(Process p) {
		var info = getProcessInfo(p);
		if (info == null || info.getParameterModel() == null)
			return Collections.emptyList();
		return info.getParameterModel().getParameters();
	}

	public static Modelling getModelling(Process p) {
		if (p == null)
			return null;
		return p.getModelling();
	}

	public static InventoryMethod getInventoryMethod(Process p) {
		var modelling = getModelling(p);
		if (modelling == null)
			return null;
		return modelling.getInventoryMethod();
	}

	public static ProcessType getProcessType(Process p) {
		var method = getInventoryMethod(p);
		return method != null
			? method.getProcessType()
			: null;
	}

	public static Completeness getCompleteness(Process p) {
		var modelling = getModelling(p);
		return modelling != null
			? modelling.getCompleteness()
			: null;
	}

	public static Representativeness getRepresentativeness(Process p) {
		var modelling = getModelling(p);
		if (modelling == null)
			return null;
		return modelling.getRepresentativeness();
	}

	public static Validation getValidation(Process p) {
		var modelling = getModelling(p);
		if (modelling == null)
			return null;
		return modelling.getValidation();
	}

	public static List<Review> getReviews(Process p) {
		var v = getValidation(p);
		return v != null
			? v.getReviews()
			: Collections.emptyList();
	}

	public static AdminInfo getAdminInfo(Process p) {
		if (p == null)
			return null;
		return p.getAdminInfo();
	}

	public static CommissionerAndGoal getCommissionerAndGoal(Process p) {
		var info = getAdminInfo(p);
		return info == null
			? null
			: info.getCommissionerAndGoal();
	}

	public static Publication getPublication(Process p) {
		var info = getAdminInfo(p);
		if (info == null)
			return null;
		return info.getPublication();
	}

	public static DataEntry getDataEntry(Process p) {
		var info = getAdminInfo(p);
		return info == null
			? null
			: info.getDataEntry();
	}

	public static DataGenerator getDataGenerator(Process p) {
		AdminInfo ai = getAdminInfo(p);
		if (ai == null)
			return null;
		return ai.getDataGenerator();
	}

	public static List<ComplianceDeclaration> getComplianceDeclarations(
		Process p
	) {
		var mod = getModelling(p);
		return mod != null
			? mod.getComplianceDeclarations()
			: Collections.emptyList();
	}

	public static ComplianceDeclaration getComplianceDeclaration(
		Process p, Ref system
	) {
		List<ComplianceDeclaration> list = getComplianceDeclarations(p);
		for (ComplianceDeclaration cd : list) {
			if (Objects.equals(cd.getSystem(), system))
				return cd;
		}
		return null;
	}
}
