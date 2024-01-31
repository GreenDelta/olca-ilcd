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
@XmlType(name = "SubLocationOfOperationSupplyOrProductionType", propOrder = {
		"description",
		"other"
})
public class SubLocation implements Copyable<SubLocation> {

	@FreeText
	@XmlElement(name = "descriptionOfRestrictions")
	private List<LangString> description;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "subLocation")
	private String subLocation;

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

	public String getSubLocation() {
		return subLocation;
	}

	public String getLatitudeAndLongitude() {
		return latitudeAndLongitude;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public SubLocation withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public SubLocation withOther(Other other) {
		this.other = other;
		return this;
	}

	public SubLocation withSubLocation(String subLocation) {
		this.subLocation = subLocation;
		return this;
	}

	public SubLocation withLatitudeAndLongitude(String latitudeAndLongitude) {
		this.latitudeAndLongitude = latitudeAndLongitude;
		return this;
	}

	public SubLocation withOtherAttributes(Map<QName, String> otherAttributes) {
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
	public SubLocation copy() {
		var copy = new SubLocation();
		Val.copy(description, copy::withDescription);
		Val.copy(other, copy::withOther);
		copy.withSubLocation(subLocation);
		copy.withLatitudeAndLongitude(latitudeAndLongitude);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
