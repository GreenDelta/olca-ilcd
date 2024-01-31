package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "clazz" })
@XmlRootElement(name = "classification", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
public class Classification implements Copyable<Classification> {

	@XmlElement(name = "class", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI", required = true)
	private List<Category> categories;

	// region getters

	public List<Category> getCategories() {
		return categories != null ? categories : List.of();
	}

	// endregion

	// region setters

	public Classification withCategories(List<Category> categories) {
		this.categories = categories;
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
	public Classification copy() {
		var copy = new Classification();
		Val.copy(categories, copy::withCategories);
		return copy;
	}

}
