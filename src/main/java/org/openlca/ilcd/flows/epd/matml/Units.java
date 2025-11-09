package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Units", propOrder = {"units"})
public class Units implements Copyable<Units> {

	@XmlElement(name = "Unit", namespace = Vocab.MATML, required = true)
	private List<Unit> units;

	@XmlAttribute(name = "name")
	private String name;

	@XmlAttribute(name = "description")
	private String description;

	// region getters

	public List<Unit> getUnits() {
		return units != null ? units : List.of();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	// endregion

	// region setters

	public Units withUnits(List<Unit> units) {
		this.units = units;
		return this;
	}

	public Units withName(String name) {
		this.name = name;
		return this;
	}

	public Units withDescription(String description) {
		this.description = description;
		return this;
	}

	public List<Unit> withUnits() {
		if (units == null) {
			units = new ArrayList<>();
		}
		return units;
	}

	// endregion

	@Override
	public Units copy() {
		var copy = new Units();
		Val.copy(units, copy::withUnits);
		copy.withName(name);
		copy.withDescription(description);
		return copy;
	}

}
