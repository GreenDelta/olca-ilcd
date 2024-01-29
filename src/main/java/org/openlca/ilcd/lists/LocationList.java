package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ILCDLocationsType", namespace = "http://lca.jrc.it/ILCD/Locations", propOrder = {"locations"})
public class LocationList implements Copyable<LocationList> {

	@XmlElement(name = "location", required = true)
	private List<Location> locations;

	// region getters

	public List<Location> getLocations() {
		return locations != null ? locations : List.of();
	}

	// endregion

	// region setters

	public LocationList withLocations(List<Location> locations) {
		this.locations = locations;
		return this;
	}

	public List<Location> withLocations() {
		if (locations == null) {
			locations = new ArrayList<>();
		}
		return locations;
	}

	// endregion

	@Override
	public LocationList copy() {
		var copy = new LocationList();
		Val.copy(locations, this::withLocations);
		return copy;
	}
}
