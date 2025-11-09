package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationType", propOrder = {
		"code"
})
public class Location implements Copyable<Location> {

	@XmlValue
	private String code;

	@XmlAttribute(name = "latitudeAndLongitude")
	private String latLong;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getCode() {
		return code;
	}

	public String getLatLong() {
		return latLong;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Location withCode(String code) {
		this.code = code;
		return this;
	}

	public Location withLatLong(String latLong) {
		this.latLong = latLong;
		return this;
	}

	public Location withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
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
		copy.withCode(code);
		copy.withLatLong(latLong);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
