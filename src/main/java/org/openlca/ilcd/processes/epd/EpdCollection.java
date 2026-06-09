package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdCollection implements Copyable<EpdCollection> {

	@XmlAttribute(name = "separate", namespace = Vocab.EPD_2024)
	private Double separate;

	@XmlAttribute(name = "withMixedWaste", namespace = Vocab.EPD_2024)
	private Double withMixedWaste;

	// region getters

	public Double getSeparate() {
		return separate;
	}

	public Double getWithMixedWaste() {
		return withMixedWaste;
	}

	// endregion

	// region setters

	public EpdCollection withSeparate(Double separate) {
		this.separate = separate;
		return this;
	}

	public EpdCollection withWithMixedWaste(Double withMixedWaste) {
		this.withMixedWaste = withMixedWaste;
		return this;
	}

	// endregion

	@Override
	public EpdCollection copy() {
		var copy = new EpdCollection();
		copy.withSeparate(separate);
		copy.withWithMixedWaste(withMixedWaste);
		return copy;
	}
}
