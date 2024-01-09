package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryType", propOrder = { "value" })
public class Compartment {

	@XmlValue
	public String value;

	@XmlAttribute(name = "level", required = true)
	public int level;

	@XmlAttribute(name = "catId")
	public String catId;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
