
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.FlowDataDerivation;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlowPropertyType", propOrder = {
		"flowProperty",
		"meanValue",
		"minimumValue",
		"maximumValue",
		"uncertaintyDistribution",
		"relativeStandardDeviation95In",
		"dataDerivation",
		"comment",
		"other"
})
public class FlowPropertyRef implements Copyable<FlowPropertyRef> {

	@XmlElement(name = "referenceToFlowPropertyDataSet", required = true)
	private Ref flowProperty;

	private double meanValue;

	private Double minimumValue;

	private Double maximumValue;

	@XmlElement(name = "uncertaintyDistributionType")
	private UncertaintyDistribution uncertaintyDistribution;

	private Double relativeStandardDeviation95In;

	@XmlElement(name = "dataDerivationTypeStatus")
	private FlowDataDerivation dataDerivation;

	@Label
	@XmlElement(name = "generalComment")
	private List<LangString> comment;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "dataSetInternalID")
	private Integer dataSetInternalID;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public Ref getFlowProperty() {
		return flowProperty;
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

	public UncertaintyDistribution getUncertaintyDistribution() {
		return uncertaintyDistribution;
	}

	public Double getRelativeStandardDeviation95In() {
		return relativeStandardDeviation95In;
	}

	public FlowDataDerivation getDataDerivation() {
		return dataDerivation;
	}

	public List<LangString> getComment() {
		return comment != null ? comment : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Integer getDataSetInternalID() {
		return dataSetInternalID;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public FlowPropertyRef withFlowProperty(Ref flowProperty) {
		this.flowProperty = flowProperty;
		return this;
	}

	public FlowPropertyRef withMeanValue(double meanValue) {
		this.meanValue = meanValue;
		return this;
	}

	public FlowPropertyRef withMinimumValue(Double minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}

	public FlowPropertyRef withMaximumValue(Double maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}

	public FlowPropertyRef withUncertaintyDistribution(UncertaintyDistribution uncertaintyDistribution) {
		this.uncertaintyDistribution = uncertaintyDistribution;
		return this;
	}

	public FlowPropertyRef withRelativeStandardDeviation95In(Double relativeStandardDeviation95In) {
		this.relativeStandardDeviation95In = relativeStandardDeviation95In;
		return this;
	}

	public FlowPropertyRef withDataDerivation(FlowDataDerivation dataDerivation) {
		this.dataDerivation = dataDerivation;
		return this;
	}

	public FlowPropertyRef withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public FlowPropertyRef withOther(Other other) {
		this.other = other;
		return this;
	}

	public FlowPropertyRef withDataSetInternalID(Integer dataSetInternalID) {
		this.dataSetInternalID = dataSetInternalID;
		return this;
	}

	public FlowPropertyRef withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Ref withFlowProperty() {
		if (flowProperty == null) {
			flowProperty = new Ref();
		}
		return flowProperty;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
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
	public FlowPropertyRef copy() {
		var copy = new FlowPropertyRef();
		Val.copy(flowProperty, copy::withFlowProperty);
		copy.withMeanValue(meanValue);
		copy.withMinimumValue(minimumValue);
		copy.withMaximumValue(maximumValue);
		copy.withUncertaintyDistribution(uncertaintyDistribution);
		copy.withRelativeStandardDeviation95In(relativeStandardDeviation95In);
		copy.withDataDerivation(dataDerivation);
		Val.copy(comment, copy::withComment);
		Val.copy(other, copy::withOther);
		copy.withDataSetInternalID(dataSetInternalID);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
