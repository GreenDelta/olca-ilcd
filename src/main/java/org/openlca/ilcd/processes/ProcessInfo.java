
package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessInformationType", propOrder = {
		"dataSetInfo",
		"quantitativeReference",
		"time",
		"geography",
		"technology",
		"parameterModel",
		"other"
})
public class ProcessInfo implements Copyable<ProcessInfo> {

	@XmlElement(required = true, name = "dataSetInformation")
	private DataSetInfo dataSetInfo;

	private QuantitativeReference quantitativeReference;

	private Time time;

	private Geography geography;

	private Technology technology;

	@XmlElement(name = "mathematicalRelations")
	private ParameterModel parameterModel;

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

	public Technology getTechnology() {
		return technology;
	}

	public ParameterModel getParameterModel() {
		return parameterModel;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public ProcessInfo withDataSetInfo(DataSetInfo dataSetInfo) {
		this.dataSetInfo = dataSetInfo;
		return this;
	}

	public ProcessInfo withQuantitativeReference(QuantitativeReference quantitativeReference) {
		this.quantitativeReference = quantitativeReference;
		return this;
	}

	public ProcessInfo withTime(Time time) {
		this.time = time;
		return this;
	}

	public ProcessInfo withGeography(Geography geography) {
		this.geography = geography;
		return this;
	}

	public ProcessInfo withTechnology(Technology technology) {
		this.technology = technology;
		return this;
	}

	public ProcessInfo withParameterModel(ParameterModel model) {
		this.parameterModel = model;
		return this;
	}

	public ProcessInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public ProcessInfo withOtherAttributes(Map<QName, String> otherAttributes) {
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

	public Technology withTechnology() {
		if (technology == null) {
			technology = new Technology();
		}
		return technology;
	}

	public ParameterModel withParameterModel() {
		if (parameterModel == null) {
			parameterModel = new ParameterModel();
		}
		return parameterModel;
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
	public ProcessInfo copy() {
		var copy = new ProcessInfo();
		Val.copy(dataSetInfo, copy::withDataSetInfo);
		Val.copy(quantitativeReference, copy::withQuantitativeReference);
		Val.copy(time, copy::withTime);
		Val.copy(geography, copy::withGeography);
		Val.copy(technology, copy::withTechnology);
		Val.copy(parameterModel, copy::withParameterModel);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
