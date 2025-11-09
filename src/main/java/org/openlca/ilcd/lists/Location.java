
package org.openlca.ilcd.lists;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.commons.Copyable;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationType", namespace = "http://lca.jrc.it/ILCD/Locations", propOrder = {
	"name"
})
public class Location implements Copyable<Location> {

	@XmlValue
	private String name;

	@XmlAttribute(name = "value", required = true)
	private String code;

	// region getters

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	// endregion

	// region setters

	public Location withName(String name) {
		this.name = name;
		return this;
	}

	public Location withCode(String code) {
		this.code = code;
		return this;
	}

	// endregion

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Location other))
			return false;
		return Objects.equals(this.code, other.code)
			&& Objects.equals(this.name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, name);
	}

	@Override
	public Location copy() {
		var clone = new Location();
		clone.code = code;
		clone.name = name;
		return clone;
	}
}
