
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;

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
public class ContactDescriptor extends Descriptor<ContactDescriptor>
	implements Copyable<ContactDescriptor> {

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
		return copy;
	}
}
