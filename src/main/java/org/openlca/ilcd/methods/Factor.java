package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataDerivation;
import org.openlca.ilcd.commons.ExchangeDirection;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.RecommendationLevel;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharacterisationFactorType", propOrder = {
	"flow",
	"location",
	"direction",
	"meanValue",
	"minimumValue",
	"maximumValue",
	"uncertaintyDistributionType",
	"relativeStandardDeviation95In",
	"dataDerivation",
	"recommendation",
	"dataSources",
	"comment"
})
public class Factor implements Copyable<Factor> {

	@XmlElement(name = "referenceToFlowDataSet", required = true)
	private Ref flow;

	private String location;

	@XmlElement(name = "exchangeDirection", required = true)
	private ExchangeDirection direction;

	private double meanValue;

	private Double minimumValue;

	private Double maximumValue;

	private UncertaintyDistribution uncertaintyDistributionType;

	private Double relativeStandardDeviation95In;

	@XmlElement(name = "dataDerivationTypeStatus")
	private DataDerivation dataDerivation;

	@XmlElement(name = "deviatingRecommendation")
	private RecommendationLevel recommendation;

	@XmlElementWrapper(name = "referencesToDataSource")
	@XmlElement(name = "referenceToDataSource")
	private List<Ref> dataSources;

	@Label
	@XmlElement(name = "generalComment")
	private List<LangString> comment;

	// region getters

	public Ref getFlow() {
		return flow;
	}

	public String getLocation() {
		return location;
	}

	public ExchangeDirection getDirection() {
		return direction;
	}

	public double getMeanValue() {
		return meanValue;
	}

	public Double getMinimumValue() {
		return minimumValue;
	}

	public Double getMaximumValue() {
		return maximumValue;
	}

	public UncertaintyDistribution getUncertaintyDistributionType() {
		return uncertaintyDistributionType;
	}

	public Double getRelativeStandardDeviation95In() {
		return relativeStandardDeviation95In;
	}

	public DataDerivation getDataDerivation() {
		return dataDerivation;
	}

	public RecommendationLevel getRecommendation() {
		return recommendation;
	}

	public List<Ref> getDataSources() {
		return dataSources != null ? dataSources : List.of();
	}

	public List<LangString> getComment() {
		return comment != null ? comment : List.of();
	}

	// endregion

	// region setters

	public Factor withFlow(Ref flow) {
		this.flow = flow;
		return this;
	}

	public Factor withLocation(String location) {
		this.location = location;
		return this;
	}

	public Factor withDirection(ExchangeDirection direction) {
		this.direction = direction;
		return this;
	}

	public Factor withMeanValue(double meanValue) {
		this.meanValue = meanValue;
		return this;
	}

	public Factor withMinimumValue(Double minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}

	public Factor withMaximumValue(Double maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}

	public Factor withUncertaintyDistributionType(UncertaintyDistribution uncertaintyDistributionType) {
		this.uncertaintyDistributionType = uncertaintyDistributionType;
		return this;
	}

	public Factor withRelativeStandardDeviation95In(Double relativeStandardDeviation95In) {
		this.relativeStandardDeviation95In = relativeStandardDeviation95In;
		return this;
	}

	public Factor withDataDerivation(DataDerivation dataDerivation) {
		this.dataDerivation = dataDerivation;
		return this;
	}

	public Factor withRecommendation(RecommendationLevel recommendation) {
		this.recommendation = recommendation;
		return this;
	}

	public Factor withDataSources(List<Ref> dataSources) {
		this.dataSources = dataSources;
		return this;
	}

	public Factor withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public Ref withFlow() {
		if (flow == null) {
			flow = new Ref();
		}
		return flow;
	}

	public List<Ref> withDataSources() {
		if (dataSources == null) {
			dataSources = new ArrayList<>();
		}
		return dataSources;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
	}

	// endregion

	@Override
	public Factor copy() {
		var copy = new Factor();
		Val.copy(flow, copy::withFlow);
		copy.withLocation(location);
		copy.withDirection(direction);
		copy.withMeanValue(meanValue);
		copy.withMinimumValue(minimumValue);
		copy.withMaximumValue(maximumValue);
		copy.withUncertaintyDistributionType(uncertaintyDistributionType);
		copy.withRelativeStandardDeviation95In(relativeStandardDeviation95In);
		copy.withDataDerivation(dataDerivation);
		copy.withRecommendation(recommendation);
		Val.copy(dataSources, copy::withDataSources);
		Val.copy(comment, copy::withComment);
		return copy;
	}

}
