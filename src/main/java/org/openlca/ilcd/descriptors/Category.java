
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.ilcd.commons.Copyable;

public class Category implements Copyable<Category> {

	@XmlValue
	private String name;

	@XmlAttribute(name = "level", required = true)
	private int level;

	// region getters

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	// endregion

	// region setters

	public Category withName(String name) {
		this.name = name;
		return this;
	}

	public Category withLevel(int level) {
		this.level = level;
		return this;
	}

	// endregion

	@Override
	public Category copy() {
		var copy = new Category();
		copy.withName(name);
		copy.withLevel(level);
		return copy;
	}

}
