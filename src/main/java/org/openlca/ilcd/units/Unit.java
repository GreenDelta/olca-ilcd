package org.openlca.ilcd.units;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitType", propOrder = {
		"name",
		"factor",
		"comment",
		"other"
})
public class Unit implements Copyable<Unit> {

	@XmlElement(required = true)
	private String name;

	@XmlElement(name = "meanValue")
	private double factor;

	@XmlElement(name = "generalComment")
	private List<LangString> comment;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "dataSetInternalID")
	private int id;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getName() {
		return name;
	}

	public double getFactor() {
		return factor;
	}

	public List<LangString> getComment() {
		return comment != null ? comment : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public int getId() {
		return id;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Unit withName(String name) {
		this.name = name;
		return this;
	}

	public Unit withFactor(double factor) {
		this.factor = factor;
		return this;
	}

	public Unit withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public Unit withOther(Other other) {
		this.other = other;
		return this;
	}

	public Unit withId(int id) {
		this.id = id;
		return this;
	}

	public Unit withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
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
	public Unit copy() {
		var copy = new Unit();
		copy.withName(name);
		copy.withFactor(factor);
		Val.copy(comment, copy::withComment);
		Val.copy(other, copy::withOther);
		copy.withId(id);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
