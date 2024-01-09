package org.openlca.ilcd.descriptors;

import java.io.Serializable;

import org.openlca.ilcd.commons.LangString;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"copyright",
		"licenseType",
		"useRestrictions"
})
@XmlRootElement(name = "accessInformation")
public class AccessInfo {
	public Boolean copyright;
	public String licenseType;
	public LangString useRestrictions;
}
