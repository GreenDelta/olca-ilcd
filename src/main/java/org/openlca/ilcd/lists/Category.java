package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryType", propOrder = { "category" })
public class Category {

	public final List<Category> category = new ArrayList<>();

	@XmlAttribute(name = "id", required = true)
	public String id;

	@XmlAttribute(name = "name", required = true)
	public String name;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
