package org.openlca.ilcd.contacts;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdEntityId implements Copyable<EpdEntityId> {

	@XmlAttribute(name = "type", namespace = Vocab.ENTITY_IDS_2024)
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

	public EpdEntityId withType(String type) {
		this.type = type;
		return this;
	}

	public EpdEntityId withValue(String value) {
		this.value = value;
		return this;
	}

	// endregion

	@Override
	public EpdEntityId copy() {
		return new EpdEntityId()
			.withType(type)
			.withValue(value);
	}
}
