package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoriesType", propOrder = {"categories"})
public class CategoryList implements Copyable<CategoryList> {

	@XmlElement(name = "category", required = true)
	private List<Category> categories;

	@XmlAttribute(name = "dataType", required = true)
	private ContentType type;

	// region getters

	public List<Category> getCategories() {
		return categories != null ? categories : Collections.emptyList();
	}

	public ContentType getType() {
		return type;
	}

	// endregion

	// region setters

	public CategoryList withCategories(List<Category> categories) {
		this.categories = categories;
		return this;
	}

	public CategoryList withType(ContentType type) {
		this.type = type;
		return this;
	}

	public List<Category> withCategories() {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		return categories;
	}


	// endregion

	@Override
	public CategoryList copy() {
		var copy = new CategoryList();
		Val.copy(categories, copy::withCategories);
		copy.withType(type);
		return copy;
	}

}
