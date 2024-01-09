package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Ref;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategorySystemType", propOrder = { "source", "categories" })
public class CategorySystem {

	@XmlElement(name = "referenceToSource")
	public Ref source;

	@XmlElement(required = true)
	public final List<CategoryList> categories = new ArrayList<>();

	@XmlAttribute(name = "name")
	public String name;

}
