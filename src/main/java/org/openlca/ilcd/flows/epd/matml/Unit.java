package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import org.aopalliance.reflect.Code;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Unit", propOrder = {"name"})
public class Unit implements Copyable<Unit> {

	@XmlElement(name = "Name", namespace = Vocab.MATML, required = true)
	private String name;

	@XmlAttribute(name = "power")
	private String power;

	// region getters

	public String getName() {
		return name;
	}

	public String getPower() {
		return power;
	}

	// endregion

	// region setters

	public Unit withName(String name) {
		this.name = name;
		return this;
	}

	public Unit withPower(String power) {
		this.power = power;
		return this;
	}

	// endregion

	@Override
	public Unit copy() {
		var copy = new Unit();
		copy.withName(name);
		copy.withPower(power);
		return copy;
	}

}
