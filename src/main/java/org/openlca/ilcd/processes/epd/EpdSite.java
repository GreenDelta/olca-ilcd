package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdSite implements Copyable<EpdSite> {

	@XmlElement(name = "name", namespace = Vocab.EPD_2024)
	private String name;

	@XmlElement(name = "facilityIdentifier", namespace = Vocab.EPD_2024)
	private String facilityIdentifier;

	@XmlElement(name = "olc", namespace = Vocab.EPD_2024)
	private String olc;

	@XmlElement(name = "geoCode", namespace = Vocab.EPD_2024)
	private String geoCode;

	@XmlElement(name = "streetAddress", namespace = Vocab.EPD_2024)
	private String streetAddress;

	// region getters

	public String getName() {
		return name;
	}

	public String getFacilityIdentifier() {
		return facilityIdentifier;
	}

	public String getOlc() {
		return olc;
	}

	public String getGeoCode() {
		return geoCode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	// endregion

	// region setters

	public EpdSite withName(String name) {
		this.name = name;
		return this;
	}

	public EpdSite withFacilityIdentifier(String facilityIdentifier) {
		this.facilityIdentifier = facilityIdentifier;
		return this;
	}

	public EpdSite withOlc(String olc) {
		this.olc = olc;
		return this;
	}

	public EpdSite withGeoCode(String geoCode) {
		this.geoCode = geoCode;
		return this;
	}

	public EpdSite withStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
		return this;
	}

	// endregion

	@Override
	public EpdSite copy() {
		var copy = new EpdSite();
		copy.withName(name);
		copy.withFacilityIdentifier(facilityIdentifier);
		copy.withOlc(olc);
		copy.withGeoCode(geoCode);
		copy.withStreetAddress(streetAddress);
		return copy;
	}
}
