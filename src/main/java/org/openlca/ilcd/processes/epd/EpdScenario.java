package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdScenario implements Copyable<EpdScenario> {

	@XmlAttribute(name = "name", namespace = Vocab.EPD_2013)
	private String name;

	@XmlAttribute(name = "default", namespace = Vocab.EPD_2013)
	private boolean defaultScenario;

	@XmlAttribute(name = "group", namespace = Vocab.EPD_2013)
	private String group;

	@XmlElement(name="description", namespace = Vocab.EPD_2013)
	private List<LangString> description;

	// region getters

	public String getName() {
		return name;
	}

	public boolean isDefaultScenario() {
		return defaultScenario;
	}

	public String getGroup() {
		return group;
	}

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	// endregion

	// region setters

	public EpdScenario withName(String name) {
		this.name = name;
		return this;
	}

	public EpdScenario withDefaultScenario(boolean defaultScenario) {
		this.defaultScenario = defaultScenario;
		return this;
	}

	public EpdScenario withGroup(String group) {
		this.group = group;
		return this;
	}

	public EpdScenario withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	// endregion

	@Override
	public EpdScenario copy() {
		var copy = new EpdScenario();
		copy.withName(name);
		copy.withDefaultScenario(defaultScenario);
		copy.withGroup(group);
		Val.copy(description, copy::withDescription);
		return copy;
	}

	@Override
	public String toString() {
		return "Scenario {name=" + name + ", group=" + group + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		var other = (EpdScenario) o;
		return Objects.equals(name, other.name)
				&& Objects.equals(group, other.group);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, group);
	}
}
