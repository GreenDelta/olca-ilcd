package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdSvhc implements Copyable<EpdSvhc> {

	@XmlAttribute(name = "present", namespace = Vocab.EPD_2024)
	private boolean present;

	// region getters

	public boolean isPresent() {
		return present;
	}

	// endregion

	// region setters

	public EpdSvhc withPresent(boolean present) {
		this.present = present;
		return this;
	}

	// endregion

	@Override
	public EpdSvhc copy() {
		return new EpdSvhc().withPresent(present);
	}
}
