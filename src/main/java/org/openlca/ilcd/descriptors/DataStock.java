package org.openlca.ilcd.descriptors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataStock implements Copyable<DataStock> {

	@XmlAttribute(name = "root")
	private boolean root;

	@XmlElement(name = "uuid", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String uuid;

	@XmlElement(name = "shortName", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String shortName;

	@XmlElement(name = "name", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<LangString> name;

	@XmlElement(name = "description", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<LangString> description;

	/**
	 * Contains the user roles. Is only used when the data stock description is
	 * returned in authentication information.
	 */
	@XmlElement(name = "role", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<String> roles;

	// region getters

	public boolean isRoot() {
		return root;
	}

	public String getUUID() {
		return uuid;
	}

	public String getShortName() {
		return shortName;
	}

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
	}

	public List<LangString> getDescription() {
		return description != null ? description : Collections.emptyList();
	}

	public List<String> getRoles() {
		return roles != null ? roles : Collections.emptyList();
	}

	// endregion

	// region setters

	public DataStock withRoot(boolean root) {
		this.root = root;
		return this;
	}

	public DataStock withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public DataStock withShortName(String shortName) {
		this.shortName = shortName;
		return this;
	}

	public DataStock withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public DataStock withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public DataStock withRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	public List<String> withRoles() {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		return roles;
	}

	// endregion

	@Override
	public DataStock copy() {
		var copy = new DataStock();
		copy.withRoot(root);
		copy.withUUID(uuid);
		copy.withShortName(shortName);
		Val.copy(name, copy::withName);
		Val.copy(description, copy::withDescription);
		if (roles != null) {
			copy.withRoles().addAll(roles);
		}
		return copy;
	}

	@Override
	public String toString() {
		return "DataStock [ " + shortName + "/" + uuid + "/root=" + root + "]";
	}

	public boolean isReadAllowed() {
		return this.roles.contains("READ");
	}

	public boolean isExportAllowed() {
		return this.roles.contains("EXPORT");
	}

}
