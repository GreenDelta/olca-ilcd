package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyDetails	", propOrder = {"name", "units"})
public class PropertyDetails implements Copyable<PropertyDetails> {

	@XmlElement(name = "Name", namespace = Vocab.MATML, required = true)
	private String name;

	@XmlElement(name = "Units", namespace = Vocab.MATML, required = true)
	private Units units;

	@XmlAttribute(name = "id", required = true)
	private String id;

	// region getters

	public String getName() {
		return name;
	}

	public Units getUnits() {
		return units;
	}

	public String getId() {
		return id;
	}

	// endregion

	// region setters

	public PropertyDetails withName(String name) {
		this.name = name;
		return this;
	}

	public PropertyDetails withUnits(Units units) {
		this.units = units;
		return this;
	}

	public PropertyDetails withId(String id) {
		this.id = id;
		return this;
	}

	public Units withUnits() {
		if (units == null) {
			units = new Units();
		}
		return units;
	}

	// endregion

	@Override
	public PropertyDetails copy() {
		var copy = new PropertyDetails()
			.withName(name);
		copy.withUnits(units);
		copy.withId(id);
		return copy;
	}

}
