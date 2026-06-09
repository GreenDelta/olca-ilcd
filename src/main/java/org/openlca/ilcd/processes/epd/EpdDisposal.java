package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdDisposal implements Copyable<EpdDisposal> {

	@XmlAttribute(name = "finalDeposition", namespace = Vocab.EPD_2024)
	private Double finalDeposition;

	// region getters

	public Double getFinalDeposition() {
		return finalDeposition;
	}

	// endregion

	// region setters

	public EpdDisposal withFinalDeposition(Double finalDeposition) {
		this.finalDeposition = finalDeposition;
		return this;
	}

	// endregion

	@Override
	public EpdDisposal copy() {
		var copy = new EpdDisposal();
		copy.withFinalDeposition(finalDeposition);
		return copy;
	}
}
