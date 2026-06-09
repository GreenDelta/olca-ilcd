package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdRecovery implements Copyable<EpdRecovery> {

	@XmlAttribute(name = "reuse", namespace = Vocab.EPD_2024)
	private Double reuse;

	@XmlAttribute(name = "recycling", namespace = Vocab.EPD_2024)
	private Double recycling;

	@XmlAttribute(name = "energyRecovery", namespace = Vocab.EPD_2024)
	private Double energyRecovery;

	// region getters

	public Double getReuse() {
		return reuse;
	}

	public Double getRecycling() {
		return recycling;
	}

	public Double getEnergyRecovery() {
		return energyRecovery;
	}

	// endregion

	// region setters

	public EpdRecovery withReuse(Double reuse) {
		this.reuse = reuse;
		return this;
	}

	public EpdRecovery withRecycling(Double recycling) {
		this.recycling = recycling;
		return this;
	}

	public EpdRecovery withEnergyRecovery(Double energyRecovery) {
		this.energyRecovery = energyRecovery;
		return this;
	}

	// endregion

	@Override
	public EpdRecovery copy() {
		var copy = new EpdRecovery();
		copy.withReuse(reuse);
		copy.withRecycling(recycling);
		copy.withEnergyRecovery(energyRecovery);
		return copy;
	}
}
