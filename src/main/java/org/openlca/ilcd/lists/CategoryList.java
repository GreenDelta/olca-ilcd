package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoriesType", propOrder = { "categories" })
public class CategoryList {

	@XmlElement(name = "category", required = true)
	public final List<Category> categories = new ArrayList<>();

	@XmlAttribute(name = "dataType", required = true)
	public ContentType type;

}
