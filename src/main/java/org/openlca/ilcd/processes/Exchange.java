package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataDerivation;
import org.openlca.ilcd.commons.ExchangeDirection;
import org.openlca.ilcd.commons.ExchangeFunction;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.processes.epd.EpdResultExtension;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangeType", propOrder = {
		"flow",
		"location",
		"exchangeFunction",
		"direction",
		"variable",
		"meanAmount",
		"resultingAmount",
		"minimumAmount",
		"maximumAmount",
		"uncertaintyDistribution",
		"relativeStandardDeviation95In",
		"allocations",
		"dataSourceType",
		"dataDerivation",
		"sources",
		"comment",
		"epdExtension" })
public class Exchange implements Copyable<Exchange> {

	/** The data set internal ID (dataSetInternalID) of the exchange. */
	@XmlAttribute(name = "dataSetInternalID", required = true)
	private int id;

	@XmlElement(name = "referenceToFlowDataSet", required = true)
	private Ref flow;

	private String location;

	@XmlElement(name = "functionType")
	private ExchangeFunction exchangeFunction;

	@XmlElement(name = "exchangeDirection")
	private ExchangeDirection direction;

	@XmlElement(name = "referenceToVariable")
	private String variable;

	@XmlElement(name = "meanAmount")
	private double meanAmount;

	@XmlElement(name = "resultingAmount")
	private Double resultingAmount;

	private Double minimumAmount;

	private Double maximumAmount;

	@XmlElement(name = "uncertaintyDistributionType")
	private UncertaintyDistribution uncertaintyDistribution;

	private Double relativeStandardDeviation95In;

	@XmlElementWrapper(name = "allocations")
	@XmlElement(name = "allocation", required = true)
	private List<AllocationFactor> allocations;

	private String dataSourceType;

	@XmlElement(name = "dataDerivationTypeStatus")
	private DataDerivation dataDerivation;

	@XmlElementWrapper(name = "referencesToDataSource")
	@XmlElement(name = "referenceToDataSource")
	private List<Ref> sources;

	@Label
	@XmlElement(name = "generalComment")
	private List<LangString> comment;

	@XmlElement(name = "other", namespace = Vocab.COMMON)
	private EpdResultExtension epdExtension;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public int getId() {
		return id;
	}

	public Ref getFlow() {
		return flow;
	}

	public String getLocation() {
		return location;
	}

	public ExchangeFunction getExchangeFunction() {
		return exchangeFunction;
	}

	public ExchangeDirection getDirection() {
		return direction;
	}

	public String getVariable() {
		return variable;
	}

	public double getMeanAmount() {
		return meanAmount;
	}

	public Double getResultingAmount() {
		return resultingAmount;
	}

	public Double getMinimumAmount() {
		return minimumAmount;
	}

	public Double getMaximumAmount() {
		return maximumAmount;
	}

	public UncertaintyDistribution getUncertaintyDistribution() {
		return uncertaintyDistribution;
	}

	public Double getRelativeStandardDeviation95In() {
		return relativeStandardDeviation95In;
	}

	public List<AllocationFactor> getAllocations() {
		return allocations != null ? allocations : Collections.emptyList();
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public DataDerivation getDataDerivation() {
		return dataDerivation;
	}

	public List<Ref> getSources() {
		return sources != null ? sources : Collections.emptyList();
	}

	public List<LangString> getComment() {
		return comment != null ? comment : Collections.emptyList();
	}

	public EpdResultExtension getEpdExtension() {
		return epdExtension;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Exchange withId(int id) {
		this.id = id;
		return this;
	}

	public Exchange withFlow(Ref flow) {
		this.flow = flow;
		return this;
	}

	public Exchange withLocation(String location) {
		this.location = location;
		return this;
	}

	public Exchange withExchangeFunction(ExchangeFunction exchangeFunction) {
		this.exchangeFunction = exchangeFunction;
		return this;
	}

	public Exchange withDirection(ExchangeDirection direction) {
		this.direction = direction;
		return this;
	}

	public Exchange withVariable(String variable) {
		this.variable = variable;
		return this;
	}

	public Exchange withMeanAmount(double meanAmount) {
		this.meanAmount = meanAmount;
		return this;
	}

	public Exchange withResultingAmount(Double resultingAmount) {
		this.resultingAmount = resultingAmount;
		return this;
	}

	public Exchange withMinimumAmount(Double minimumAmount) {
		this.minimumAmount = minimumAmount;
		return this;
	}

	public Exchange withMaximumAmount(Double maximumAmount) {
		this.maximumAmount = maximumAmount;
		return this;
	}

	public Exchange withUncertaintyDistribution(UncertaintyDistribution uncertaintyDistribution) {
		this.uncertaintyDistribution = uncertaintyDistribution;
		return this;
	}

	public Exchange withRelativeStandardDeviation95In(Double relativeStandardDeviation95In) {
		this.relativeStandardDeviation95In = relativeStandardDeviation95In;
		return this;
	}

	public Exchange withAllocations(List<AllocationFactor> allocations) {
		this.allocations = allocations;
		return this;
	}

	public Exchange withDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
		return this;
	}

	public Exchange withDataDerivation(DataDerivation dataDerivation) {
		this.dataDerivation = dataDerivation;
		return this;
	}

	public Exchange withSources(List<Ref> sources) {
		this.sources = sources;
		return this;
	}

	public Exchange withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public Exchange withEpdExtension(EpdResultExtension epdExtension) {
		this.epdExtension = epdExtension;
		return this;
	}

	public Exchange withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Ref withFlow() {
		if (flow == null) {
			flow = new Ref();
		}
		return flow;
	}

	public List<AllocationFactor> withAllocations() {
		if (allocations == null) {
			allocations = new ArrayList<>();
		}
		return allocations;
	}

	public List<Ref> withSources() {
		if (sources == null) {
			sources = new ArrayList<>();
		}
		return sources;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
	}

	public EpdResultExtension withEpdExtension() {
		if (epdExtension == null) {
			epdExtension = new EpdResultExtension();
		}
		return epdExtension;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Exchange copy() {
		var copy = new Exchange();
		copy.withId(id);
		Val.copy(flow, copy::withFlow);
		copy.withLocation(location);
		copy.withExchangeFunction(exchangeFunction);
		copy.withDirection(direction);
		copy.withVariable(variable);
		copy.withMeanAmount(meanAmount);
		copy.withResultingAmount(resultingAmount);
		copy.withMinimumAmount(minimumAmount);
		copy.withMaximumAmount(maximumAmount);
		copy.withUncertaintyDistribution(uncertaintyDistribution);
		copy.withRelativeStandardDeviation95In(relativeStandardDeviation95In);
		Val.copy(allocations, copy::withAllocations);
		copy.withDataSourceType(dataSourceType);
		copy.withDataDerivation(dataDerivation);
		Val.copy(sources, copy::withSources);
		Val.copy(comment, copy::withComment);
		Val.copy(epdExtension, copy::withEpdExtension);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
