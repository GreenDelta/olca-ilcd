package org.openlca.ilcd.epd.model;

import org.openlca.ilcd.commons.Copyable;

public final class MaterialPropertyValue implements Copyable<MaterialPropertyValue> {

	public MaterialProperty property;
	public double value;

	@Override
	public MaterialPropertyValue copy() {
		var clone = new MaterialPropertyValue();
		if (property != null) {
			clone.property = property.copy();
		}
		clone.value = value;
		return clone;
	}
}
