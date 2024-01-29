package org.openlca.ilcd.methods;

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
@XmlType(name = "LCIAMethodInformationType", propOrder = {
		"dataSetInfo",
		"quantitativeReference",
		"time",
		"geography",
		"impactModel",
		"other"
})
public class MethodInfo implements Copyable<MethodInfo> {

	@XmlElement(name = "dataSetInformation", required = true)
	private DataSetInfo dataSetInfo;

	private QuantitativeReference quantitativeReference;

	private Time time;

	@XmlElement(required = true)
	private Geography geography;

	private ImpactModel impactModel;

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

	public Time getTime() {
		return time;
	}

	public Geography getGeography() {
		return geography;
	}

	public ImpactModel getImpactModel() {
		return impactModel;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public MethodInfo withDataSetInfo(DataSetInfo dataSetInfo) {
		this.dataSetInfo = dataSetInfo;
		return this;
	}

	public MethodInfo withQuantitativeReference(QuantitativeReference quantitativeReference) {
		this.quantitativeReference = quantitativeReference;
		return this;
	}

	public MethodInfo withTime(Time time) {
		this.time = time;
		return this;
	}

	public MethodInfo withGeography(Geography geography) {
		this.geography = geography;
		return this;
	}

	public MethodInfo withImpactModel(ImpactModel impactModel) {
		this.impactModel = impactModel;
		return this;
	}

	public MethodInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public MethodInfo withOtherAttributes(Map<QName, String> otherAttributes) {
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

	public Time withTime() {
		if (time == null) {
			time = new Time();
		}
		return time;
	}

	public Geography withGeography() {
		if (geography == null) {
			geography = new Geography();
		}
		return geography;
	}

	public ImpactModel withImpactModel() {
		if (impactModel == null) {
			impactModel = new ImpactModel();
		}
		return impactModel;
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
	public MethodInfo copy() {
		var copy = new MethodInfo();
		Val.copy(dataSetInfo, copy::withDataSetInfo);
		Val.copy(quantitativeReference, copy::withQuantitativeReference);
		Val.copy(time, copy::withTime);
		Val.copy(geography, copy::withGeography);
		Val.copy(impactModel, copy::withImpactModel);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
