package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeographyType", propOrder = { "location", "subLocations",
		"other" })
public class Geography implements Copyable<Geography> {

	@XmlElement(name = "locationOfOperationSupplyOrProduction")
	public Location location;

	@XmlElement(name = "subLocationOfOperationSupplyOrProduction")
	public final List<SubLocation> subLocations = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Geography copy() {
		var clone = new Geography();
		if (location != null)
			clone.location = location.copy();
		for (SubLocation sub : subLocations)
			clone.subLocations.add(sub.copy());
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
