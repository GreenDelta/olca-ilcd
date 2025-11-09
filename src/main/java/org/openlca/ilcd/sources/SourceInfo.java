package org.openlca.ilcd.sources;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SourceInformationType", propOrder = {
	"dataSetInfo",
	"other"
})
public class SourceInfo implements Copyable<SourceInfo> {

	@XmlElement(required = true, name = "dataSetInformation")
	private DataSetInfo dataSetInfo;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public DataSetInfo getDataSetInfo() {
		return dataSetInfo;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public SourceInfo withDataSetInfo(DataSetInfo dataSetInfo) {
		this.dataSetInfo = dataSetInfo;
		return this;
	}

	public SourceInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public SourceInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public DataSetInfo withDataSetInfo() {
		if (dataSetInfo == null) {
			dataSetInfo = new DataSetInfo();
		}
		return dataSetInfo;
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
	public SourceInfo copy() {
		var copy = new SourceInfo();
		Val.copy(dataSetInfo, copy::withDataSetInfo);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
