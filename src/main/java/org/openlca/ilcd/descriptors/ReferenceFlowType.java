
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceFlowType", propOrder = {
		"name",
		"flowProperty",
		"unit",
		"meanValue",
		"reference"
})
public class ReferenceFlowType implements Copyable<ReferenceFlowType> {

	private List<LangString> name;

	private List<LangString> flowProperty;

	private String unit;

	private Double meanValue;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private DataSetReference reference;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	@XmlAttribute(name = "internalId")
	private Integer internalId;

	// region getters

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
	}

	public List<LangString> getFlowProperty() {
		return flowProperty != null ? flowProperty : Collections.emptyList();
	}

	public String getUnit() {
		return unit;
	}

	public Double getMeanValue() {
		return meanValue;
	}

	public DataSetReference getReference() {
		return reference;
	}

	public String getHref() {
		return href;
	}

	public Integer getInternalId() {
		return internalId;
	}

	// endregion

	// region setters

	public ReferenceFlowType withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public ReferenceFlowType withFlowProperty(List<LangString> flowProperty) {
		this.flowProperty = flowProperty;
		return this;
	}

	public ReferenceFlowType withUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public ReferenceFlowType withMeanValue(Double meanValue) {
		this.meanValue = meanValue;
		return this;
	}

	public ReferenceFlowType withReference(DataSetReference reference) {
		this.reference = reference;
		return this;
	}

	public ReferenceFlowType withHref(String href) {
		this.href = href;
		return this;
	}

	public ReferenceFlowType withInternalId(Integer internalId) {
		this.internalId = internalId;
		return this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	public List<LangString> withFlowProperty() {
		if (flowProperty == null) {
			flowProperty = new ArrayList<>();
		}
		return flowProperty;
	}

	public DataSetReference withReference() {
		if (reference == null) {
			reference = new DataSetReference();
		}
		return reference;
	}

	// endregion

	@Override
	public ReferenceFlowType copy() {
		var copy = new ReferenceFlowType();
		Val.copy(name, copy::withName);
		Val.copy(flowProperty, copy::withFlowProperty);
		copy.withUnit(unit);
		copy.withMeanValue(meanValue);
		Val.copy(reference, copy::withReference);
		copy.withHref(href);
		copy.withInternalId(internalId);
		return copy;
	}
}
