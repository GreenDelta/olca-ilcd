
package org.openlca.ilcd.commons;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.namespace.QName;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassType", propOrder = {
	"value"
})
public class Category implements Copyable<Category> {

	@XmlValue
	private String value;

	/**
	 * If more than one class is specified in a hierarchical classification
	 * system, the hierarchy level (1,2,...) could be specified with this
	 * attribute of class.
	 */
	@XmlAttribute(name = "level", required = true)
	private int level;

	/**
	 * Unique identifier for the class. [ If such identifiers are also defined
	 * in the referenced category file, they should be identical. Identifiers
	 * can be UUID's, but also other forms are allowed.]
	 */
	@XmlAttribute(name = "classId")
	private String classId;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getValue() {
		return value;
	}

	public int getLevel() {
		return level;
	}

	public String getClassId() {
		return classId;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null
			? otherAttributes
			: Map.of();
	}

	// endregion

	// region fluent setters

	public Category withValue(String value) {
		this.value = value;
		return this;
	}

	public Category withLevel(int level) {
		this.level = level;
		return this;
	}

	public Category withClassId(String classId) {
		this.classId = classId;
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
	public Category copy() {
		var copy = new Category()
			.withValue(value)
			.withLevel(level)
			.withClassId(classId);
		if (otherAttributes != null) {
			copy.withOtherAttributes().putAll(otherAttributes);
		}
		return copy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Category other))
			return false;
		if (classId != null || other.classId != null)
			return Objects.equals(classId, other.classId);
		if (level != other.level)
			return false;
		return Objects.equals(value, other.value);
	}

	@Override
	public int hashCode() {
		if (classId != null)
			return classId.hashCode();
		return Objects.hash(level, value);
	}
}
