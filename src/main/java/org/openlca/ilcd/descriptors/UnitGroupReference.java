
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"name",
		"defaultUnit",
		"reference"
})
public class UnitGroupReference implements Copyable<UnitGroupReference> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/FlowProperty", required = true)
	private List<LangString> name;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/FlowProperty", required = true)
	private String defaultUnit;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private DataSetReference reference;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	// region getters

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
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

	public UnitGroupReference withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public UnitGroupReference withDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
		return this;
	}

	public UnitGroupReference withReference(DataSetReference reference) {
		this.reference = reference;
		return this;
	}

	public UnitGroupReference withHref(String href) {
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
	public UnitGroupReference copy() {
		var copy = new UnitGroupReference();
		Val.copy(name, copy::withName);
		copy.withDefaultUnit(defaultUnit);
		Val.copy(reference, copy::withReference);
		copy.withHref(href);
		return copy;
	}

}
