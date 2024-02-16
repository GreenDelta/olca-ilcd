package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "name" })
public class Group implements Copyable<Group> {

	@XmlAttribute(name = "id")
	private int id;

	@XmlElement(name = "groupName")
	private List<LangString> name;

	// region getters

	public int getId() {
		return id;
	}

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
	}

	// endregion

	// region setters

	public Group withId(int id) {
		this.id = id;
		return this;
	}

	public Group withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	// endregion

	@Override
	public Group copy() {
		var copy = new Group();
		copy.withId(id);
		Val.copy(name, copy::withName);
		return copy;
	}

}
