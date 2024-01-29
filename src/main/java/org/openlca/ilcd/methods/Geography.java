package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
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
@XmlType(name = "GeographyType", propOrder = {
		"interventionLocation",
		"interventionSubLocations",
		"impactLocation",
		"description",
		"other" })
public class Geography implements Copyable<Geography> {

	private Location interventionLocation;

	@XmlElement(name = "interventionSubLocation")
	private List<Location> interventionSubLocations;

	private Location impactLocation;

	@FreeText
	@XmlElement(name = "geographicalRepresentativenessDescription")
	private List<LangString> description;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public Location getInterventionLocation() {
		return interventionLocation;
	}

	public List<Location> getInterventionSubLocations() {
		return interventionSubLocations != null ? interventionSubLocations : List.of();
	}

	public Location getImpactLocation() {
		return impactLocation;
	}

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Geography withInterventionLocation(Location interventionLocation) {
		this.interventionLocation = interventionLocation;
		return this;
	}

	public Geography withInterventionSubLocations(List<Location> interventionSubLocations) {
		this.interventionSubLocations = interventionSubLocations;
		return this;
	}

	public Geography withImpactLocation(Location impactLocation) {
		this.impactLocation = impactLocation;
		return this;
	}

	public Geography withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public Geography withOther(Other other) {
		this.other = other;
		return this;
	}

	public Geography withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Location withInterventionLocation() {
		if (interventionLocation == null) {
			interventionLocation = new Location();
		}
		return interventionLocation;
	}

	public List<Location> withInterventionSubLocations() {
		if (interventionSubLocations == null) {
			interventionSubLocations = new ArrayList<>();
		}
		return interventionSubLocations;
	}

	public Location withImpactLocation() {
		if (impactLocation == null) {
			impactLocation = new Location();
		}
		return impactLocation;
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
	public Geography copy() {
		var copy = new Geography();
		Val.copy(interventionLocation, copy::withInterventionLocation);
		Val.copy(interventionSubLocations, copy::withInterventionSubLocations);
		Val.copy(impactLocation, copy::withImpactLocation);
		Val.copy(description, copy::withDescription);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
