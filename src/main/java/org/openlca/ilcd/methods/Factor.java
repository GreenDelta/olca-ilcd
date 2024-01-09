package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.DataDerivation;
import org.openlca.ilcd.commons.ExchangeDirection;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.RecommendationLevel;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.commons.annotations.Label;

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
		"dataDerivationTypeStatus",
		"deviatingRecommendation",
		"dataSources",
		"comment"
})
public class Factor {

	@XmlElement(name = "referenceToFlowDataSet", required = true)
	public Ref flow;

	public String location;

	@XmlElement(name = "exchangeDirection", required = true)
	public ExchangeDirection direction;

	public double meanValue;

	public Double minimumValue;

	public Double maximumValue;

	public UncertaintyDistribution uncertaintyDistributionType;

	public Double relativeStandardDeviation95In;

	public DataDerivation dataDerivationTypeStatus;

	public RecommendationLevel deviatingRecommendation;

	@XmlElementWrapper(name = "referencesToDataSource")
	@XmlElement(name = "referenceToDataSource")
	public Ref[] dataSources;

	@Label
	@XmlElement(name = "generalComment")
	public final List<LangString> comment = new ArrayList<>();

}
