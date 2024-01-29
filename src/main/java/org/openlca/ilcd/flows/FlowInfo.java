
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlowInformationType", propOrder = {
		"dataSetInfo",
		"quantitativeReference",
		"geography",
		"technology",
		"other"
})
public class FlowInfo implements Copyable<FlowInfo> {

	@XmlElement(required = true, name = "dataSetInformation")
	private DataSetInfo dataSetInfo;

	private QuantitativeReference quantitativeReference;

	private Geography geography;

	private Technology technology;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public DataSetInfo getDataSetInfo() {
		return dataSetInfo;
	}

	public QuantitativeReference getQuantitativeReference() {
		return quantitativeReference;
	}

	public Geography getGeography() {
		return geography;
	}

	public Technology getTechnology() {
		return technology;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public FlowInfo withDataSetInfo(DataSetInfo dataSetInfo) {
		this.dataSetInfo = dataSetInfo;
		return this;
	}

	public FlowInfo withQuantitativeReference(QuantitativeReference quantitativeReference) {
		this.quantitativeReference = quantitativeReference;
		return this;
	}

	public FlowInfo withGeography(Geography geography) {
		this.geography = geography;
		return this;
	}

	public FlowInfo withTechnology(Technology technology) {
		this.technology = technology;
		return this;
	}

	public FlowInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public FlowInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public DataSetInfo withDataSetInfo() {
		if (dataSetInfo == null) {
			dataSetInfo = new DataSetInfo();
		}
		return dataSetInfo;
	}

	public QuantitativeReference withQuantitativeReference() {
		if (quantitativeReference == null) {
			quantitativeReference = new QuantitativeReference();
		}
		return quantitativeReference;
	}

	public Geography withGeography() {
		if (geography == null) {
			geography = new Geography();
		}
		return geography;
	}

	public Technology withTechnology() {
		if (technology == null) {
			technology = new Technology();
		}
		return technology;
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
	public FlowInfo copy() {
		var copy = new FlowInfo();
		Val.copy(dataSetInfo, this::withDataSetInfo);
		Val.copy(quantitativeReference, this::withQuantitativeReference);
		Val.copy(geography, this::withGeography);
		Val.copy(technology, this::withTechnology);
		Val.copy(other, this::withOther);
		Val.copy(otherAttributes, this::withOtherAttributes);
		return copy;
	}

}
