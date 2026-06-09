package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdProductId implements Copyable<EpdProductId> {

	@XmlAttribute(name = "type", namespace = Vocab.EPD_2024)
	private String type;

	@XmlValue
	private String value;

	// region getters

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	// endregion

	// region setters

	public EpdProductId withType(String type) {
		this.type = type;
		return this;
	}

	public EpdProductId withValue(String value) {
		this.value = value;
		return this;
	}

	// endregion

	@Override
	public EpdProductId copy() {
		var copy = new EpdProductId();
		copy.withType(type);
		copy.withValue(value);
		return copy;
	}
}
