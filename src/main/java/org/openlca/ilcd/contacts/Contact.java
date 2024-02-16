
package org.openlca.ilcd.contacts;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactDataSetType", propOrder = {
	"contactInfo",
	"adminInfo",
	"other"
})
public class Contact implements IDataSet, Copyable<Contact> {

	@XmlElement(required = true, name = "contactInformation")
	private ContactInfo contactInfo;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "version", required = true)
	private String version;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public Other getOther() {
		return other;
	}

	public String getVersion() {
		return version;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Contact withContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
		return this;
	}

	public Contact withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public Contact withOther(Other other) {
		this.other = other;
		return this;
	}

	public Contact withVersion(String version) {
		this.version = version;
		return this;
	}

	public Contact withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public ContactInfo withContactInfo() {
		if (contactInfo == null) {
			contactInfo = new ContactInfo();
		}
		return contactInfo;
	}

	public AdminInfo withAdminInfo() {
		if (adminInfo == null) {
			adminInfo = new AdminInfo();
		}
		return adminInfo;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Contact copy() {
		var copy = new Contact();
		Val.copy(contactInfo, copy::withContactInfo);
		Val.copy(adminInfo, copy::withAdminInfo);
		Val.copy(other, copy::withOther);
		copy.withVersion(version);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<Contact> toElement() {
		var qname = new QName("http://lca.jrc.it/ILCD/Contact", "contactDataSet");
		return new JAXBElement<>(qname, Contact.class, null, this);
	}
}
