package org.openlca.ilcd.contacts;

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
@XmlType(name = "ContactInformationType", propOrder = {
		"dataSetInfo",
		"other"
})
public class ContactInfo implements Copyable<ContactInfo> {

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
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public ContactInfo withDataSetInfo(DataSetInfo dataSetInfo) {
		this.dataSetInfo = dataSetInfo;
		return this;
	}

	public ContactInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public ContactInfo withOtherAttributes(Map<QName, String> otherAttributes) {
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
	public ContactInfo copy() {
		var copy = new ContactInfo();
		Val.copy(dataSetInfo, this::withDataSetInfo);
		Val.copy(other, this::withOther);
		Val.copy(otherAttributes, this::withOtherAttributes);
		return copy;
	}
}
