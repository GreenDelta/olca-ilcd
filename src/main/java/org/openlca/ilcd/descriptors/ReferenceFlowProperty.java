
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"name",
		"defaultUnit",
		"reference"
})
@XmlRootElement(name = "referenceFlowProperty", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow")
public class ReferenceFlowProperty implements Copyable<ReferenceFlowProperty> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow")
	private List<LangString> name;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow", required = true)
	private String defaultUnit;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private DataSetReference reference;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	// region getters

	public List<LangString> getName() {
		return name != null ? name : List.of();
	}

	public String getDefaultUnit() {
		return defaultUnit;
	}

	public DataSetReference getReference() {
		return reference;
	}

	public String getHref() {
		return href;
	}

	// endregion

	// region setters

	public ReferenceFlowProperty withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public ReferenceFlowProperty withDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
		return this;
	}

	public ReferenceFlowProperty withReference(DataSetReference reference) {
		this.reference = reference;
		return this;
	}

	public ReferenceFlowProperty withHref(String href) {
		this.href = href;
		return this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	public DataSetReference withReference() {
		if (reference == null) {
			reference = new DataSetReference();
		}
		return reference;
	}

	// endregion

	@Override
	public ReferenceFlowProperty copy() {
		var copy = new ReferenceFlowProperty();
		Val.copy(name, copy::withName);
		copy.withDefaultUnit(defaultUnit);
		Val.copy(reference, copy::withReference);
		copy.withHref(href);
		return copy;
	}

}
