package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyDetails	", propOrder = {"name", "units"})
public class PropertyDetails implements Copyable<PropertyDetails> {

	@XmlElement(name = "Name", namespace = Vocab.MATML, required = true)
	private Name name;

	@XmlElement(name = "Units", namespace = Vocab.MATML, required = true)
	private Units units;

	@XmlID
	@XmlAttribute(name = "id", required = true)
	private String id;

	// region getters

	public Name getName() {
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

	public PropertyDetails withName(Name name) {
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

	public Name withName() {
		if (name == null) {
			name = new Name();
		}
		return name;
	}

	public Object withUnits() {
		if (units == null) {
			units = new Units();
		}
		return units;
	}

	// endregion

	@Override
	public PropertyDetails copy() {
		var copy = new PropertyDetails();
		Val.copy(name, copy::withName);
		copy.withUnits(units);
		copy.withId(id);
		return copy;
	}

}
