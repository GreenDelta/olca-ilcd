package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassificationType", propOrder = {"categories", "other"})
public class Classification implements Copyable<Classification> {

	@XmlElement(name = "class", required = true)
	private List<Category> categories;

	private Other other;

	@XmlAttribute(name = "name")
	private String name;

	@XmlAttribute(name = "classes")
	@XmlSchemaType(name = "anyURI")
	private String url;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<Category> getCategories() {
		return categories != null
			? categories
			: List.of();
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null
			? otherAttributes
			: Map.of();
	}

	// endregion

	// region fluent setters

	public Classification withName(String name) {
		this.name = name;
		return this;
	}

	public Classification withUrl(String url) {
		this.url = url;
		return this;
	}

	public Classification withCategories(List<Category> categories) {
		this.categories = categories;
		return this;
	}

	public Classification withOther(Other other) {
		this.other = other;
		return this;
	}

	public Classification withOtherAttributes(Map<QName, String> attributes) {
		this.otherAttributes = attributes;
		return this;
	}

	public List<Category> withCategories() {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		return categories;
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
	public Classification copy() {
		var copy = new Classification()
			.withName(name)
			.withUrl(url);
		Val.copy(categories, copy::withCategories);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
