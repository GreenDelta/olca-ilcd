package org.openlca.ilcd.epd;

import java.util.Objects;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Strings;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdProfileModule
	implements Comparable<EpdProfileModule>, Copyable<EpdProfileModule> {

	@XmlAttribute(name = "index")
	private int index;

	@XmlAttribute(name = "name")
	private String name;

	@XmlElement(name = "description")
	private String description;

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public EpdProfileModule withIndex(int index) {
		this.index = index;
		return this;
	}

	public EpdProfileModule withName(String name) {
		this.name = name;
		return this;
	}

	public EpdProfileModule withDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public int compareTo(EpdProfileModule other) {
		if (other == null)
			return 1;
		return Integer.compare(this.index, other.index);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof EpdProfileModule other))
			return false;
		return Strings.nullOrEqual(this.name, other.name);
	}

	@Override
	public EpdProfileModule copy() {
		return new EpdProfileModule()
			.withIndex(index)
			.withName(name)
			.withDescription(description);
	}
}

