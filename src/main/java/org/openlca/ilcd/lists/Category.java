package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryType", propOrder = { "categories" })
public class Category implements Copyable<Category> {

	@XmlElement(name="category")
	private List<Category> categories;

	@XmlAttribute(name = "id", required = true)
	private String id;

	@XmlAttribute(name = "name", required = true)
	private String name;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<Category> getCategories() {
		return categories != null ? categories : Collections.emptyList();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Category withCategories(List<Category> categories) {
		this.categories = categories;
		return this;
	}

	public Category withId(String id) {
		this.id = id;
		return this;
	}

	public Category withName(String name) {
		this.name = name;
		return this;
	}

	public Category withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Category> withCategories() {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		return categories;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Category copy() {
		var copy = new Category();
		Val.copy(categories, copy::withCategories);
		copy.withId(id);
		copy.withName(name);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
