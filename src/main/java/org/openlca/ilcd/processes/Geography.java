package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeographyType", propOrder = {"location", "subLocations",
	"other"})
public class Geography implements Copyable<Geography> {

	@XmlElement(name = "locationOfOperationSupplyOrProduction")
	private Location location;

	@XmlElement(name = "subLocationOfOperationSupplyOrProduction")
	private List<SubLocation> subLocations;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public Location getLocation() {
		return location;
	}

	public List<SubLocation> getSubLocations() {
		return subLocations != null ? subLocations : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Geography withLocation(Location location) {
		this.location = location;
		return this;
	}

	public Geography withSubLocations(List<SubLocation> subLocations) {
		this.subLocations = subLocations;
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

	public Location withLocation() {
		if (location == null) {
			location = new Location();
		}
		return location;
	}

	public List<SubLocation> withSubLocations() {
		if (subLocations == null) {
			subLocations = new ArrayList<>();
		}
		return subLocations;
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
		Val.copy(location, copy::withLocation);
		Val.copy(subLocations, copy::withSubLocations);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
