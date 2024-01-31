package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationOfOperationSupplyOrProductionType", propOrder = {
		"description",
		"other"
})
public class Location implements Copyable<Location> {

	/**
	 * Further explanations about additional aspects of the location: e.g. a
	 * company and/or site description and address, whether for certain
	 * sub-areas within the "Location" the data set is not valid, whether data
	 * is only valid for certain regions within the location indicated, or
	 * whether certain elementary flows or intermediate product flows are
	 * extrapolated from another geographical area.
	 */
	@FreeText
	@XmlElement(name = "descriptionOfRestrictions")
	private List<LangString> description;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	/**
	 * Location, country or region the data set represents. [Note 1: This field
	 * does not refer to e.g. the country in which a specific site is located
	 * that is represented by this data set but to the actually represented
	 * country, region, or site. Note 2: Entry can be of type "two-letter ISO
	 * 3166 country code" for countries, "seven-letter regional codes" for
	 * regions or continents, or "market areas and market organisations", as
	 * predefined for the ILCD. Also a name for e.g. a specific plant etc. can
	 * be given here (e.g. "FR, Lyon, XY Company, Z Site"; user defined). Note
	 * 3: The fact whether the entry refers to production or to consumption /
	 * supply has to be stated in the name-field "Mix and location types" e.g.
	 * as "Production mix".]
	 */
	@XmlAttribute(name = "location")
	private String code;

	/**
	 * Geographical latitude and longitude reference of "Location" /
	 * "Sub-location". For area-type locations (e.g. countries, continents) the
	 * field is empty.
	 */
	@XmlAttribute(name = "latitudeAndLongitude")
	private String latitudeAndLongitude;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	public Other getOther() {
		return other;
	}

	public String getCode() {
		return code;
	}

	public String getLatitudeAndLongitude() {
		return latitudeAndLongitude;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Location withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public Location withOther(Other other) {
		this.other = other;
		return this;
	}

	public Location withCode(String code) {
		this.code = code;
		return this;
	}

	public Location withLatitudeAndLongitude(String latitudeAndLongitude) {
		this.latitudeAndLongitude = latitudeAndLongitude;
		return this;
	}

	public Location withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
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
	public Location copy() {
		var copy = new Location();
		Val.copy(description, copy::withDescription);
		Val.copy(other, copy::withOther);
		copy.withCode(code);
		copy.withLatitudeAndLongitude(latitudeAndLongitude);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
