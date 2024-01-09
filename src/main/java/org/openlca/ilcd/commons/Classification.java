package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassificationType", propOrder = { "categories", "other" })
public class Classification implements Copyable<Classification> {

	@XmlElement(name = "class", required = true)
	public final List<Category> categories = new ArrayList<>();

	public Other other;

	@XmlAttribute(name = "name")
	public String name;

	@XmlAttribute(name = "classes")
	@XmlSchemaType(name = "anyURI")
	public String url;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Classification copy() {
		var clone = new Classification();
		for (Category c : categories)
			clone.categories.add(c.copy());
		if (other != null)
			clone.other = other.copy();
		clone.name = name;
		clone.url = url;
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
