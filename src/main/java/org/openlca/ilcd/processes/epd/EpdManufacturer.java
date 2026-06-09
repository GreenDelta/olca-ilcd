package org.openlca.ilcd.processes.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdManufacturer implements Copyable<EpdManufacturer> {

	@XmlAttribute(name = "isProvidingData", namespace = Vocab.EPD_2024)
	private boolean isProvidingData;

	@XmlElement(name = "contact", namespace = Vocab.EPD_2024)
	private Ref contact;

	@XmlElementWrapper(name = "sites", namespace = Vocab.EPD_2024)
	@XmlElement(name = "site", namespace = Vocab.EPD_2024)
	private List<EpdSite> sites;

	// region getters

	public boolean isProvidingData() {
		return isProvidingData;
	}

	public Ref getContact() {
		return contact;
	}

	public List<EpdSite> getSites() {
		return sites != null ? sites : List.of();
	}

	// endregion

	// region setters

	public EpdManufacturer withProvidingData(boolean isProvidingData) {
		this.isProvidingData = isProvidingData;
		return this;
	}

	public EpdManufacturer withContact(Ref contact) {
		this.contact = contact;
		return this;
	}

	public EpdManufacturer withSites(List<EpdSite> sites) {
		this.sites = sites;
		return this;
	}

	public List<EpdSite> withSites() {
		if (sites == null) {
			sites = new ArrayList<>();
		}
		return sites;
	}

	// endregion

	@Override
	public EpdManufacturer copy() {
		var copy = new EpdManufacturer();
		copy.withProvidingData(isProvidingData);
		Val.copy(contact, copy::withContact);
		Val.copy(sites, copy::withSites);
		return copy;
	}
}
