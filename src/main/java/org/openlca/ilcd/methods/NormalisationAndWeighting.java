package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.ShortText;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIAMethodNormalisationAndWeightingType", propOrder = {
	"type",
	"principles",
	"deviationsFromPrinciples",
	"normalisation",
	"usableNormalisationDataSets",
	"normalisationDescription",
	"includedNormalisationDataSets",
	"weighting",
	"usableWeightingDataSets",
	"weightingDescription",
	"includedWeightingDataSets"})
public class NormalisationAndWeighting implements Copyable<NormalisationAndWeighting> {

	@XmlElement(name = "typeOfDataSet")
	private ImpactMethodType type;

	@XmlElement(name = "LCIAMethodPrinciple")
	private List<ImpactMethodPrinciple> principles;

	@FreeText
	@XmlElement(name = "deviationsFromLCIAMethodPrinciple")
	private List<LangString> deviationsFromPrinciples;

	private Boolean normalisation;

	@XmlElement(name = "referenceToUsableNormalisationDataSets")
	private List<Ref> usableNormalisationDataSets;

	@ShortText
	private List<LangString> normalisationDescription;

	@XmlElement(name = "referenceToIncludedNormalisationDataSets")
	private List<Ref> includedNormalisationDataSets;

	private Boolean weighting;

	@XmlElement(name = "referenceToUsableWeightingDataSets")
	private List<Ref> usableWeightingDataSets;

	@ShortText
	private List<LangString> weightingDescription;

	@XmlElement(name = "referenceToIncludedWeightingDataSets")
	private List<Ref> includedWeightingDataSets;

	// region getters

	public ImpactMethodType getType() {
		return type;
	}

	public List<ImpactMethodPrinciple> getPrinciples() {
		return principles != null ? principles : List.of();
	}

	public List<LangString> getDeviationsFromPrinciples() {
		return deviationsFromPrinciples != null ? deviationsFromPrinciples : List.of();
	}

	public Boolean getNormalisation() {
		return normalisation;
	}

	public List<Ref> getUsableNormalisationDataSets() {
		return usableNormalisationDataSets != null ? usableNormalisationDataSets : List.of();
	}

	public List<LangString> getNormalisationDescription() {
		return normalisationDescription != null ? normalisationDescription : List.of();
	}

	public List<Ref> getIncludedNormalisationDataSets() {
		return includedNormalisationDataSets != null ? includedNormalisationDataSets : List.of();
	}

	public Boolean getWeighting() {
		return weighting;
	}

	public List<Ref> getUsableWeightingDataSets() {
		return usableWeightingDataSets != null ? usableWeightingDataSets : List.of();
	}

	public List<LangString> getWeightingDescription() {
		return weightingDescription != null ? weightingDescription : List.of();
	}

	public List<Ref> getIncludedWeightingDataSets() {
		return includedWeightingDataSets != null ? includedWeightingDataSets : List.of();
	}

	// endregion

	// region setters

	public NormalisationAndWeighting withType(ImpactMethodType type) {
		this.type = type;
		return this;
	}

	public NormalisationAndWeighting withPrinciples(List<ImpactMethodPrinciple> principles) {
		this.principles = principles;
		return this;
	}

	public NormalisationAndWeighting withDeviationsFromPrinciples(List<LangString> deviationsFromPrinciples) {
		this.deviationsFromPrinciples = deviationsFromPrinciples;
		return this;
	}

	public NormalisationAndWeighting withNormalisation(Boolean normalisation) {
		this.normalisation = normalisation;
		return this;
	}

	public NormalisationAndWeighting withUsableNormalisationDataSets(List<Ref> usableNormalisationDataSets) {
		this.usableNormalisationDataSets = usableNormalisationDataSets;
		return this;
	}

	public NormalisationAndWeighting withNormalisationDescription(List<LangString> normalisationDescription) {
		this.normalisationDescription = normalisationDescription;
		return this;
	}

	public NormalisationAndWeighting withIncludedNormalisationDataSets(List<Ref> includedNormalisationDataSets) {
		this.includedNormalisationDataSets = includedNormalisationDataSets;
		return this;
	}

	public NormalisationAndWeighting withWeighting(Boolean weighting) {
		this.weighting = weighting;
		return this;
	}

	public NormalisationAndWeighting withUsableWeightingDataSets(List<Ref> usableWeightingDataSets) {
		this.usableWeightingDataSets = usableWeightingDataSets;
		return this;
	}

	public NormalisationAndWeighting withWeightingDescription(List<LangString> weightingDescription) {
		this.weightingDescription = weightingDescription;
		return this;
	}

	public NormalisationAndWeighting withIncludedWeightingDataSets(List<Ref> includedWeightingDataSets) {
		this.includedWeightingDataSets = includedWeightingDataSets;
		return this;
	}

	public List<ImpactMethodPrinciple> withPrinciples() {
		if (principles == null) {
			principles = new ArrayList<>();
		}
		return principles;
	}

	public List<LangString> withDeviationsFromPrinciples() {
		if (deviationsFromPrinciples == null) {
			deviationsFromPrinciples = new ArrayList<>();
		}
		return deviationsFromPrinciples;
	}

	public List<Ref> withUsableNormalisationDataSets() {
		if (usableNormalisationDataSets == null) {
			usableNormalisationDataSets = new ArrayList<>();
		}
		return usableNormalisationDataSets;
	}

	public List<LangString> withNormalisationDescription() {
		if (normalisationDescription == null) {
			normalisationDescription = new ArrayList<>();
		}
		return normalisationDescription;
	}

	public List<Ref> withIncludedNormalisationDataSets() {
		if (includedNormalisationDataSets == null) {
			includedNormalisationDataSets = new ArrayList<>();
		}
		return includedNormalisationDataSets;
	}

	public List<Ref> withUsableWeightingDataSets() {
		if (usableWeightingDataSets == null) {
			usableWeightingDataSets = new ArrayList<>();
		}
		return usableWeightingDataSets;
	}

	public List<LangString> withWeightingDescription() {
		if (weightingDescription == null) {
			weightingDescription = new ArrayList<>();
		}
		return weightingDescription;
	}

	public List<Ref> withIncludedWeightingDataSets() {
		if (includedWeightingDataSets == null) {
			includedWeightingDataSets = new ArrayList<>();
		}
		return includedWeightingDataSets;
	}

	// endregion

	@Override
	public NormalisationAndWeighting copy() {
		var copy = new NormalisationAndWeighting()
			.withType(type);
		if (principles != null) {
			copy.withPrinciples().addAll(principles);
		}
		Val.copy(deviationsFromPrinciples, copy::withDeviationsFromPrinciples);
		copy.withNormalisation(normalisation);
		Val.copy(usableNormalisationDataSets, copy::withUsableNormalisationDataSets);
		Val.copy(normalisationDescription, copy::withNormalisationDescription);
		Val.copy(includedNormalisationDataSets, copy::withIncludedNormalisationDataSets);
		copy.withWeighting(weighting);
		Val.copy(usableWeightingDataSets, copy::withUsableWeightingDataSets);
		Val.copy(weightingDescription, copy::withWeightingDescription);
		Val.copy(includedWeightingDataSets, copy::withIncludedWeightingDataSets);
		return copy;
	}

}
