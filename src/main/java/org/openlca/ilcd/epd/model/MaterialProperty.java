package org.openlca.ilcd.epd.model;

import org.openlca.ilcd.commons.Copyable;

import java.util.Objects;

public final class MaterialProperty implements Copyable<MaterialProperty> {

	public String id;
	public String name;
	public String unit;
	public String unitDescription;

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof MaterialProperty other))
			return false;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public int hashCode() {
		return id == null
			? super.hashCode()
			: id.hashCode();
	}

	@Override
	public MaterialProperty copy() {
		var clone = new MaterialProperty();
		clone.id = id;
		clone.name = name;
		clone.unit = unit;
		clone.unitDescription = unitDescription;
		return clone;
	}
}
