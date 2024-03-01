package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.CommissionerAndGoal;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.Time;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.*;

import javax.xml.datatype.XMLGregorianCalendar;
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

	public static void withUUID(Process p, String uuid) {
		withDataSetInfo(p).withUUID(uuid);
	}

	public static String getVersion(Process p) {
		var pub = getPublication(p);
		return pub != null ? pub.getVersion() : null;
	}

	public static void withVersion(Process p, String version) {
		withPublication(p).withVersion(version);
	}

	public static List<LangString> getBaseName(Process p) {
		var name = getProcessName(p);
		return name != null
			? name.getBaseName()
			: Collections.emptyList();
	}

	public static List<LangString> withBaseName(Process p) {
		return withProcessName(p).withBaseName();
	}

	public static void withBaseName(Process p, LangString name) {
		var names = withBaseName(p);
		names.clear();
		if (name != null) {
			names.add(name);
		}
	}

	public static String getUri(Process p) {
		var pub =	getPublication(p);
		return pub != null ? pub.getUri() : null;
	}

	public static void withUri(Process p, String uri) {
		withPublication(p).withUri(uri);
	}

	public static XMLGregorianCalendar getTimeStamp(Process p) {
		var entry = getDataEntry(p);
		return entry != null
			? entry.getTimeStamp()
			: null;
	}

	public static void withTimeStamp(Process p, XMLGregorianCalendar t) {
		withDataEntry(p).withTimeStamp(t);
	}

	public static List<Classification> getClassifications(Process p) {
		var info = getDataSetInfo(p);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static List<Classification> withClassifications(Process p) {
		return withDataSetInfo(p).withClassifications();
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

	public static DataSetInfo withDataSetInfo(Process p) {
		return p.withProcessInfo().withDataSetInfo();
	}

	public static ProcessName getProcessName(Process p) {
		var info = getDataSetInfo(p);
		if (info == null)
			return null;
		return info.getProcessName();
	}

	public static ProcessName withProcessName(Process p) {
		return withDataSetInfo(p).withProcessName();
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

	public static Geography withGeography(Process p) {
		return p.withProcessInfo().withGeography();
	}

	public static Location getLocation(Process p) {
		var geo = getGeography(p);
		if (geo == null)
			return null;
		return geo.getLocation();
	}

	public static Location withLocation(Process p) {
		return withGeography(p).withLocation();
	}

	public static QuantitativeReference getQuantitativeReference(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getQuantitativeReference();
	}

	public static QuantitativeReference withQuantitativeReference(Process p) {
		return p.withProcessInfo().withQuantitativeReference();
	}

	public static List<Integer> getReferenceFlows(Process p) {
		var qref = getQuantitativeReference(p);
		return qref != null
			? qref.getReferenceFlows()
			: Collections.emptyList();
	}

	public static List<Integer> withReferenceFlows(Process p) {
		return withQuantitativeReference(p).withReferenceFlows();
	}

	public static Technology getTechnology(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getTechnology();
	}

	public static Technology withTechnology(Process p) {
		return p.withProcessInfo().withTechnology();
	}

	public static Time getTime(Process p) {
		var info = getProcessInfo(p);
		if (info == null)
			return null;
		return info.getTime();
	}

	public static Time withTime(Process p) {
		return p.withProcessInfo().withTime();
	}

	public static ParameterModel getParameterModel(Process p) {
		var info = getProcessInfo(p);
		return info != null
			? info.getParameterModel()
			: null;
	}

	public static ParameterModel withParameterModel(Process p) {
		return p.withProcessInfo().withParameterModel();
	}

	public static List<Parameter> getParameters(Process p) {
		var model = getParameterModel(p);
		return model != null
			? model.getParameters()
			: Collections.emptyList();
	}

	public static List<Parameter> withParameters(Process p) {
		return withParameterModel(p).withParameters();
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

	public static InventoryMethod withInventoryMethod(Process p) {
		return p.withModelling().withInventoryMethod();
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

	public static Completeness withCompleteness(Process p) {
		return p.withModelling().withCompleteness();
	}

	public static Representativeness getRepresentativeness(Process p) {
		var modelling = getModelling(p);
		if (modelling == null)
			return null;
		return modelling.getRepresentativeness();
	}

	public static Representativeness withRepresentativeness(Process p) {
		return p.withModelling().withRepresentativeness();
	}

	public static Validation getValidation(Process p) {
		var modelling = getModelling(p);
		if (modelling == null)
			return null;
		return modelling.getValidation();
	}

	public static Validation withValidation(Process p) {
		return p.withModelling().withValidation();
	}

	public static List<Review> getReviews(Process p) {
		var v = getValidation(p);
		return v != null
			? v.getReviews()
			: Collections.emptyList();
	}

	public static List<Review> withReviews(Process p) {
		return withValidation(p).withReviews();
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

	public static CommissionerAndGoal withCommissionerAndGoal(Process p) {
		return p.withAdminInfo().withCommissionerAndGoal();
	}

	public static Publication getPublication(Process p) {
		var info = getAdminInfo(p);
		if (info == null)
			return null;
		return info.getPublication();
	}

	public static Publication withPublication(Process p) {
		return p.withAdminInfo().withPublication();
	}

	public static DataEntry getDataEntry(Process p) {
		var info = getAdminInfo(p);
		return info == null
			? null
			: info.getDataEntry();
	}

	public static DataEntry withDataEntry(Process p) {
		return p.withAdminInfo().withDataEntry();
	}

	public static DataGenerator getDataGenerator(Process p) {
		AdminInfo ai = getAdminInfo(p);
		if (ai == null)
			return null;
		return ai.getDataGenerator();
	}

	public static DataGenerator withDataGenerator(Process p) {
		return p.withAdminInfo().withDataGenerator();
	}

	public static List<ComplianceDeclaration> getComplianceDeclarations(
		Process p
	) {
		var mod = getModelling(p);
		return mod != null
			? mod.getComplianceDeclarations()
			: Collections.emptyList();
	}

	public static List<ComplianceDeclaration> withComplianceDeclarations(
		Process p
	) {
		return p.withModelling().withComplianceDeclarations();
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
