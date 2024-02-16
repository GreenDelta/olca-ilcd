package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlowCategorizationType", propOrder = { "compartments", "other" })
public class CompartmentList implements Copyable<CompartmentList> {

	@XmlElement(name = "category", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private List<Compartment> compartments;

	private Other other;

	@XmlAttribute(name = "name")
	private String name;

	@XmlAttribute(name = "categories")
	@XmlSchemaType(name = "anyURI")
	private String url;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<Compartment> getCompartments() {
		return compartments != null ? compartments : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public CompartmentList withCompartments(List<Compartment> compartments) {
		this.compartments = compartments;
		return this;
	}

	public CompartmentList withOther(Other other) {
		this.other = other;
		return this;
	}

	public CompartmentList withName(String name) {
		this.name = name;
		return this;
	}

	public CompartmentList withUrl(String url) {
		this.url = url;
		return this;
	}

	public CompartmentList withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Compartment> withCompartments() {
		if (compartments == null) {
			compartments = new ArrayList<>();
		}
		return compartments;
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
	public CompartmentList copy() {
		var copy = new CompartmentList();
		Val.copy(compartments, copy::withCompartments);
		Val.copy(other, copy::withOther);
		copy.withName(name);
		copy.withUrl(url);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
