package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "dataSetInfo", "quantitativeReference", "technology" })
public class ModelInfo implements Copyable<ModelInfo> {

	@XmlElement(name = "dataSetInformation")
	private DataSetInfo dataSetInfo;

	@XmlElement(name = "quantitativeReference")
	private QuantitativeReference quantitativeReference;

	@XmlElement(name = "technology")
	private Technology technology;

	// region getters

	public DataSetInfo getDataSetInfo() {
		return dataSetInfo;
	}

	public QuantitativeReference getQuantitativeReference() {
		return quantitativeReference;
	}

	public Technology getTechnology() {
		return technology;
	}

	// endregion

	// region setters

	public ModelInfo withDataSetInfo(DataSetInfo dataSetInfo) {
		this.dataSetInfo = dataSetInfo;
		return this;
	}

	public ModelInfo withQuantitativeReference(QuantitativeReference quantitativeReference) {
		this.quantitativeReference = quantitativeReference;
		return this;
	}

	public ModelInfo withTechnology(Technology technology) {
		this.technology = technology;
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

	public Technology withTechnology() {
		if (technology == null) {
			technology = new Technology();
		}
		return technology;
	}

	// endregion

	@Override
	public ModelInfo copy() {
		var copy = new ModelInfo();
		Val.copy(dataSetInfo, copy::withDataSetInfo);
		Val.copy(quantitativeReference, copy::withQuantitativeReference);
		Val.copy(technology, copy::withTechnology);
		return copy;
	}

}
