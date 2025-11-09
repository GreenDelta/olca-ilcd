package org.openlca.ilcd.lists;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.XmlRoot;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategorySystemType", propOrder = {"source", "categories"})
public class CategorySystem implements XmlRoot, Copyable<CategorySystem> {

	@XmlElement(name = "referenceToSource")
	private Ref source;

	@XmlElement(required = true)
	private List<CategoryList> categories;

	@XmlAttribute(name = "name")
	private String name;

	// region getters

	public Ref getSource() {
		return source;
	}

	public List<CategoryList> getCategories() {
		return categories != null ? categories : Collections.emptyList();
	}

	public String getName() {
		return name;
	}

	// endregion

	// region setters

	public CategorySystem withSource(Ref source) {
		this.source = source;
		return this;
	}

	public CategorySystem withCategories(List<CategoryList> categories) {
		this.categories = categories;
		return this;
	}

	public CategorySystem withName(String name) {
		this.name = name;
		return this;
	}

	public Ref withSource() {
		if (source == null) {
			source = new Ref();
		}
		return source;
	}

	public List<CategoryList> withCategories() {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		return categories;
	}

	// endregion

	@Override
	public CategorySystem copy() {
		var copy = new CategorySystem();
		Val.copy(source, copy::withSource);
		Val.copy(categories, copy::withCategories);
		copy.withName(name);
		return copy;
	}

	@Override
	public JAXBElement<? extends XmlRoot> toElement() {
		var qname = new QName("http://lca.jrc.it/ILCD/Categories", "CategorySystem");
		return new JAXBElement<>(qname, CategorySystem.class, null, this);
	}
}
