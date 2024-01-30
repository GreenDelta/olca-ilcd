
package org.openlca.ilcd.descriptors;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"uuid",
		"uri",
		"version",
		"name",
		"shortName",
		"classification",
		"comment",
		"centralContactPoint",
		"phone",
		"fax",
		"email",
		"www"
})
public class ContactDescriptor extends Descriptor implements Copyable<ContactDescriptor> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String shortName;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Contact")
	private String centralContactPoint;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Contact")
	private String phone;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Contact")
	private String fax;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Contact")
	private String email;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Contact")
	private String www;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	@XmlAttribute(name = "sourceId", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String sourceId;

	@Override
	protected DataSetType getType() {
		return DataSetType.CONTACT;
	}

	// region getters

	public String getShortName() {
		return shortName;
	}

	public String getCentralContactPoint() {
		return centralContactPoint;
	}

	public String getPhone() {
		return phone;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public String getWww() {
		return www;
	}

	public String getHref() {
		return href;
	}

	public String getSourceId() {
		return sourceId;
	}

	// endregion

	// region setters

	public ContactDescriptor withShortName(String shortName) {
		this.shortName = shortName;
		return this;
	}

	public ContactDescriptor withCentralContactPoint(String centralContactPoint) {
		this.centralContactPoint = centralContactPoint;
		return this;
	}

	public ContactDescriptor withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public ContactDescriptor withFax(String fax) {
		this.fax = fax;
		return this;
	}

	public ContactDescriptor withEmail(String email) {
		this.email = email;
		return this;
	}

	public ContactDescriptor withWww(String www) {
		this.www = www;
		return this;
	}

	public ContactDescriptor withHref(String href) {
		this.href = href;
		return this;
	}

	public ContactDescriptor withSourceId(String sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	// endregion

	@Override
	public ContactDescriptor copy() {
		var copy = new ContactDescriptor();
		copyBase(copy);
		copy.withShortName(shortName);
		copy.withCentralContactPoint(centralContactPoint);
		copy.withPhone(phone);
		copy.withFax(fax);
		copy.withEmail(email);
		copy.withWww(www);
		copy.withHref(href);
		copy.withSourceId(sourceId);
		return copy;
	}
}
