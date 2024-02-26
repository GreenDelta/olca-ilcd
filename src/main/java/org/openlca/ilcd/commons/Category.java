
package org.openlca.ilcd.commons;

import java.util.Collections;
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
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassType", propOrder = {
	"name"
})
public class Category implements Copyable<Category> {

	@XmlValue
	private String name;

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

	public String getName() {
		return name;
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
			: Collections.emptyMap();
	}

	// endregion

	// region fluent setters

	public Category withName(String value) {
		this.name = value;
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

	public Category withOtherAttributes(Map<QName, String> attributes) {
		this.otherAttributes = attributes;
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
			.withName(name)
			.withLevel(level)
			.withClassId(classId);
		Val.copy(otherAttributes, copy::withOtherAttributes);
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
		return Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		if (classId != null)
			return classId.hashCode();
		return Objects.hash(level, name);
	}
}
