package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourcesTreatmentAndRepresentativenessType", propOrder = {
	"completeness",
	"completenessComment",
	"dataSelection",
	"dataSelectionComment",
	"dataTreatment",
	"datatTreatmentComment",
	"dataHandlingSources",
	"sources",
	"coveredProduction",
	"productionVolume",
	"samplingProcedure",
	"dataCollectionPeriod",
	"uncertaintyAdjustments",
	"useAdvice",
	"other"
})
public class Representativeness implements Copyable<Representativeness> {

	@FreeText
	@XmlElement(name = "dataCutOffAndCompletenessPrinciples")
	private List<LangString> completeness;

	@FreeText
	@XmlElement(name = "deviationsFromCutOffAndCompletenessPrinciples")
	private List<LangString> completenessComment;

	@FreeText
	@XmlElement(name = "dataSelectionAndCombinationPrinciples")
	private List<LangString> dataSelection;

	@FreeText
	@XmlElement(name = "deviationsFromSelectionAndCombinationPrinciples")
	private List<LangString> dataSelectionComment;

	@FreeText
	@XmlElement(name = "dataTreatmentAndExtrapolationsPrinciples")
	private List<LangString> dataTreatment;

	@FreeText
	@XmlElement(name = "deviationsFromTreatmentAndExtrapolationPrinciples")
	private List<LangString> datatTreatmentComment;

	@XmlElement(name = "referenceToDataHandlingPrinciples")
	private List<Ref> dataHandlingSources;

	@XmlElement(name = "referenceToDataSource")
	private List<Ref> sources;

	@XmlElement(name = "percentageSupplyOrProductionCovered")
	private Double coveredProduction;

	@Label
	@XmlElement(name = "annualSupplyOrProductionVolume")
	private List<LangString> productionVolume;

	@FreeText
	@XmlElement(name = "samplingProcedure")
	private List<LangString> samplingProcedure;

	@Label
	@XmlElement(name = "dataCollectionPeriod")
	private List<LangString> dataCollectionPeriod;

	@FreeText
	@XmlElement(name = "uncertaintyAdjustments")
	private List<LangString> uncertaintyAdjustments;

	@FreeText
	@XmlElement(name = "useAdviceForDataSet")
	private List<LangString> useAdvice;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<LangString> getCompleteness() {
		return completeness != null ? completeness : List.of();
	}

	public List<LangString> getCompletenessComment() {
		return completenessComment != null ? completenessComment : List.of();
	}

	public List<LangString> getDataSelection() {
		return dataSelection != null ? dataSelection : List.of();
	}

	public List<LangString> getDataSelectionComment() {
		return dataSelectionComment != null ? dataSelectionComment : List.of();
	}

	public List<LangString> getDataTreatment() {
		return dataTreatment != null ? dataTreatment : List.of();
	}

	public List<LangString> getDatatTreatmentComment() {
		return datatTreatmentComment != null ? datatTreatmentComment : List.of();
	}

	public List<Ref> getDataHandlingSources() {
		return dataHandlingSources != null ? dataHandlingSources : List.of();
	}

	public List<Ref> getSources() {
		return sources != null ? sources : List.of();
	}

	public Double getCoveredProduction() {
		return coveredProduction;
	}

	public List<LangString> getProductionVolume() {
		return productionVolume != null ? productionVolume : List.of();
	}

	public List<LangString> getSamplingProcedure() {
		return samplingProcedure != null ? samplingProcedure : List.of();
	}

	public List<LangString> getDataCollectionPeriod() {
		return dataCollectionPeriod != null ? dataCollectionPeriod : List.of();
	}

	public List<LangString> getUncertaintyAdjustments() {
		return uncertaintyAdjustments != null ? uncertaintyAdjustments : List.of();
	}

	public List<LangString> getUseAdvice() {
		return useAdvice != null ? useAdvice : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Representativeness withCompleteness(List<LangString> completeness) {
		this.completeness = completeness;
		return this;
	}

	public Representativeness withCompletenessComment(List<LangString> completenessComment) {
		this.completenessComment = completenessComment;
		return this;
	}

	public Representativeness withDataSelection(List<LangString> dataSelection) {
		this.dataSelection = dataSelection;
		return this;
	}

	public Representativeness withDataSelectionComment(List<LangString> dataSelectionComment) {
		this.dataSelectionComment = dataSelectionComment;
		return this;
	}

	public Representativeness withDataTreatment(List<LangString> dataTreatment) {
		this.dataTreatment = dataTreatment;
		return this;
	}

	public Representativeness withDatatTreatmentComment(List<LangString> datatTreatmentComment) {
		this.datatTreatmentComment = datatTreatmentComment;
		return this;
	}

	public Representativeness withDataHandlingSources(List<Ref> dataHandlingSources) {
		this.dataHandlingSources = dataHandlingSources;
		return this;
	}

	public Representativeness withSources(List<Ref> sources) {
		this.sources = sources;
		return this;
	}

	public Representativeness withCoveredProduction(Double coveredProduction) {
		this.coveredProduction = coveredProduction;
		return this;
	}

	public Representativeness withProductionVolume(List<LangString> productionVolume) {
		this.productionVolume = productionVolume;
		return this;
	}

	public Representativeness withSamplingProcedure(List<LangString> samplingProcedure) {
		this.samplingProcedure = samplingProcedure;
		return this;
	}

	public Representativeness withDataCollectionPeriod(List<LangString> dataCollectionPeriod) {
		this.dataCollectionPeriod = dataCollectionPeriod;
		return this;
	}

	public Representativeness withUncertaintyAdjustments(List<LangString> uncertaintyAdjustments) {
		this.uncertaintyAdjustments = uncertaintyAdjustments;
		return this;
	}

	public Representativeness withUseAdvice(List<LangString> useAdvice) {
		this.useAdvice = useAdvice;
		return this;
	}

	public Representativeness withOther(Other other) {
		this.other = other;
		return this;
	}

	public Representativeness withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withCompleteness() {
		if (completeness == null) {
			completeness = new ArrayList<>();
		}
		return completeness;
	}

	public List<LangString> withCompletenessComment() {
		if (completenessComment == null) {
			completenessComment = new ArrayList<>();
		}
		return completenessComment;
	}

	public List<LangString> withDataSelection() {
		if (dataSelection == null) {
			dataSelection = new ArrayList<>();
		}
		return dataSelection;
	}

	public List<LangString> withDataSelectionComment() {
		if (dataSelectionComment == null) {
			dataSelectionComment = new ArrayList<>();
		}
		return dataSelectionComment;
	}

	public List<LangString> withDataTreatment() {
		if (dataTreatment == null) {
			dataTreatment = new ArrayList<>();
		}
		return dataTreatment;
	}

	public List<LangString> withDatatTreatmentComment() {
		if (datatTreatmentComment == null) {
			datatTreatmentComment = new ArrayList<>();
		}
		return datatTreatmentComment;
	}

	public List<Ref> withDataHandlingSources() {
		if (dataHandlingSources == null) {
			dataHandlingSources = new ArrayList<>();
		}
		return dataHandlingSources;
	}

	public List<Ref> withSources() {
		if (sources == null) {
			sources = new ArrayList<>();
		}
		return sources;
	}

	public List<LangString> withProductionVolume() {
		if (productionVolume == null) {
			productionVolume = new ArrayList<>();
		}
		return productionVolume;
	}

	public List<LangString> withSamplingProcedure() {
		if (samplingProcedure == null) {
			samplingProcedure = new ArrayList<>();
		}
		return samplingProcedure;
	}

	public List<LangString> withDataCollectionPeriod() {
		if (dataCollectionPeriod == null) {
			dataCollectionPeriod = new ArrayList<>();
		}
		return dataCollectionPeriod;
	}

	public List<LangString> withUncertaintyAdjustments() {
		if (uncertaintyAdjustments == null) {
			uncertaintyAdjustments = new ArrayList<>();
		}
		return uncertaintyAdjustments;
	}

	public List<LangString> withUseAdvice() {
		if (useAdvice == null) {
			useAdvice = new ArrayList<>();
		}
		return useAdvice;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Representativeness copy() {
		var copy = new Representativeness();
		Val.copy(completeness, copy::withCompleteness);
		Val.copy(completenessComment, copy::withCompletenessComment);
		Val.copy(dataSelection, copy::withDataSelection);
		Val.copy(dataSelectionComment, copy::withDataSelectionComment);
		Val.copy(dataTreatment, copy::withDataTreatment);
		Val.copy(datatTreatmentComment, copy::withDatatTreatmentComment);
		Val.copy(dataHandlingSources, copy::withDataHandlingSources);
		Val.copy(sources, copy::withSources);
		copy.withCoveredProduction(coveredProduction);
		Val.copy(productionVolume, copy::withProductionVolume);
		Val.copy(samplingProcedure, copy::withSamplingProcedure);
		Val.copy(dataCollectionPeriod, copy::withDataCollectionPeriod);
		Val.copy(uncertaintyAdjustments, copy::withUncertaintyAdjustments);
		Val.copy(useAdvice, copy::withUseAdvice);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
