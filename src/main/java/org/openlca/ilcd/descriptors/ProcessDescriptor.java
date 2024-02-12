package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.FlowCompleteness;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"uuid",
		"uri",
		"version",
		"name",
		"classification",
		"comment",
		"synonyms",
		"type",
		"location",
		"time",
		"parameterized",
		"hasResults",
		"inventoryMethod",
		"completenessProductModel",
		"complianceSystem",
		"review",
		"overallQuality",
		"useAdvice",
		"technicalPurpose",
		"accessInformation",
		"format",
		"ownership",
		"approvedBy" })
public class ProcessDescriptor extends Descriptor<ProcessDescriptor> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<LangString> synonyms;

	private ProcessType type;

	private String location;

	private Time time;

	private Boolean parameterized;

	private Boolean hasResults;

	@XmlElement(name = "lciMethodInformation")
	private InventoryMethod inventoryMethod;

	private FlowCompleteness completenessProductModel;

	private List<ComplianceDeclaration> complianceSystem;

	private Review review;

	private String overallQuality;

	private List<LangString> useAdvice;

	private String technicalPurpose;

	private AccessInfo accessInformation;

	private String format;

	private DataSetReference ownership;

	private DataSetReference approvedBy;

	@XmlAttribute(name = "accessRestricted", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private Boolean accessRestricted;

	@Override
	protected DataSetType getType() {
		return DataSetType.PROCESS;
	}

	// region getters

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : Collections.emptyList();
	}

	public ProcessType getProcessType() {
		return type;
	}

	public String getLocation() {
		return location;
	}

	public Time getTime() {
		return time;
	}

	public Boolean getParameterized() {
		return parameterized;
	}

	public Boolean getHasResults() {
		return hasResults;
	}

	public InventoryMethod getInventoryMethod() {
		return inventoryMethod;
	}

	public FlowCompleteness getCompletenessProductModel() {
		return completenessProductModel;
	}

	public List<ComplianceDeclaration> getComplianceSystem() {
		return complianceSystem != null ? complianceSystem : Collections.emptyList();
	}

	public Review getReview() {
		return review;
	}

	public String getOverallQuality() {
		return overallQuality;
	}

	public List<LangString> getUseAdvice() {
		return useAdvice != null ? useAdvice : Collections.emptyList();
	}

	public String getTechnicalPurpose() {
		return technicalPurpose;
	}

	public AccessInfo getAccessInformation() {
		return accessInformation;
	}

	public String getFormat() {
		return format;
	}

	public DataSetReference getOwnership() {
		return ownership;
	}

	public DataSetReference getApprovedBy() {
		return approvedBy;
	}

	public Boolean getAccessRestricted() {
		return accessRestricted;
	}

	// endregion

	// region setters

	public ProcessDescriptor withSynonyms(List<LangString> synonyms) {
		this.synonyms = synonyms;
		return this;
	}

	public ProcessDescriptor withProcessType(ProcessType type) {
		this.type = type;
		return this;
	}

	public ProcessDescriptor withLocation(String location) {
		this.location = location;
		return this;
	}

	public ProcessDescriptor withTime(Time time) {
		this.time = time;
		return this;
	}

	public ProcessDescriptor withParameterized(Boolean parameterized) {
		this.parameterized = parameterized;
		return this;
	}

	public ProcessDescriptor withHasResults(Boolean hasResults) {
		this.hasResults = hasResults;
		return this;
	}

	public ProcessDescriptor withInventoryMethod(InventoryMethod inventoryMethod) {
		this.inventoryMethod = inventoryMethod;
		return this;
	}

	public ProcessDescriptor withCompletenessProductModel(FlowCompleteness completenessProductModel) {
		this.completenessProductModel = completenessProductModel;
		return this;
	}

	public ProcessDescriptor withComplianceSystem(List<ComplianceDeclaration> complianceSystem) {
		this.complianceSystem = complianceSystem;
		return this;
	}

	public ProcessDescriptor withReview(Review review) {
		this.review = review;
		return this;
	}

	public ProcessDescriptor withOverallQuality(String overallQuality) {
		this.overallQuality = overallQuality;
		return this;
	}

	public ProcessDescriptor withUseAdvice(List<LangString> useAdvice) {
		this.useAdvice = useAdvice;
		return this;
	}

	public ProcessDescriptor withTechnicalPurpose(String technicalPurpose) {
		this.technicalPurpose = technicalPurpose;
		return this;
	}

	public ProcessDescriptor withAccessInformation(AccessInfo accessInformation) {
		this.accessInformation = accessInformation;
		return this;
	}

	public ProcessDescriptor withFormat(String format) {
		this.format = format;
		return this;
	}

	public ProcessDescriptor withOwnership(DataSetReference ownership) {
		this.ownership = ownership;
		return this;
	}

	public ProcessDescriptor withApprovedBy(DataSetReference approvedBy) {
		this.approvedBy = approvedBy;
		return this;
	}

	public ProcessDescriptor withAccessRestricted(Boolean accessRestricted) {
		this.accessRestricted = accessRestricted;
		return this;
	}

	public List<LangString> withSynonyms() {
		if (synonyms == null) {
			synonyms = new ArrayList<>();
		}
		return synonyms;
	}

	public Time withTime() {
		if (time == null) {
			time = new Time();
		}
		return time;
	}

	public InventoryMethod withInventoryMethod() {
		if (inventoryMethod == null) {
			inventoryMethod = new InventoryMethod();
		}
		return inventoryMethod;
	}

	public List<ComplianceDeclaration> withComplianceSystem() {
		if (complianceSystem == null) {
			complianceSystem = new ArrayList<>();
		}
		return complianceSystem;
	}

	public Review withReview() {
		if (review == null) {
			review = new Review();
		}
		return review;
	}

	public List<LangString> withUseAdvice() {
		if (useAdvice == null) {
			useAdvice = new ArrayList<>();
		}
		return useAdvice;
	}

	public AccessInfo withAccessInformation() {
		if (accessInformation == null) {
			accessInformation = new AccessInfo();
		}
		return accessInformation;
	}

	public DataSetReference withOwnership() {
		if (ownership == null) {
			ownership = new DataSetReference();
		}
		return ownership;
	}

	public DataSetReference withApprovedBy() {
		if (approvedBy == null) {
			approvedBy = new DataSetReference();
		}
		return approvedBy;
	}

	// endregion

	@Override
	public ProcessDescriptor copy() {
		var copy = new ProcessDescriptor();
		copyBase(copy);
		Val.copy(synonyms, copy::withSynonyms);
		copy.withProcessType(type);
		copy.withLocation(location);
		Val.copy(time, copy::withTime);
		copy.withParameterized(parameterized);
		copy.withHasResults(hasResults);
		Val.copy(inventoryMethod, copy::withInventoryMethod);
		copy.withCompletenessProductModel(completenessProductModel);
		Val.copy(complianceSystem, copy::withComplianceSystem);
		Val.copy(review, copy::withReview);
		copy.withOverallQuality(overallQuality);
		Val.copy(useAdvice, copy::withUseAdvice);
		copy.withTechnicalPurpose(technicalPurpose);
		Val.copy(accessInformation, copy::withAccessInformation);
		copy.withFormat(format);
		Val.copy(ownership, copy::withOwnership);
		Val.copy(approvedBy, copy::withApprovedBy);
		copy.withAccessRestricted(accessRestricted);
		return copy;
	}
}
