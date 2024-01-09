package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ILCDLocationsType", namespace = "http://lca.jrc.it/ILCD/Locations", propOrder = { "locations" })
public class LocationList {

	@XmlElement(name = "location", required = true)
	public final List<Location> locations = new ArrayList<>();

	@Override
	public LocationList clone() {
		LocationList clone = new LocationList();
		for (Location loc : locations) {
			if (loc == null)
				continue;
			clone.locations.add(loc.clone());
		}
		return clone;
	}
}
