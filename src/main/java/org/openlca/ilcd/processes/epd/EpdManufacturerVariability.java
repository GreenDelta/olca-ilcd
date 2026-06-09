package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdManufacturerVariability implements Copyable<EpdManufacturerVariability> {

	@XmlAttribute(name = "type", namespace = Vocab.EPD_2024)
	private VariabilityType type;

	@XmlAttribute(name = "variation", namespace = Vocab.EPD_2024)
	private Double variation;

	@XmlAttribute(name = "variationRange", namespace = Vocab.EPD_2024)
	private String range;

	// region getters

	public VariabilityType getType() {
		return type;
	}

	public Double getVariation() {
		return variation;
	}

	public String getRange() {
		return range;
	}

	// endregion

	// region setters

	public EpdManufacturerVariability withType(VariabilityType type) {
		this.type = type;
		return this;
	}

	public EpdManufacturerVariability withVariation(Double variation) {
		this.variation = variation;
		return this;
	}

	public EpdManufacturerVariability withRange(String range) {
		this.range = range;
		return this;
	}

	// endregion

	@Override
	public EpdManufacturerVariability copy() {
		var copy = new EpdManufacturerVariability();
		copy.withType(type);
		copy.withVariation(variation);
		copy.withRange(range);
		return copy;
	}

	@XmlEnum
	@XmlType(name = "manufacturerVariabilityValues")
	public enum VariabilityType {

		@XmlEnumValue("Single production site")
		SINGLE_PRODUCTION_SITE("Single production site"),

		@XmlEnumValue("Single manufacturer with multiple production sites")
		SINGLE_MANUFACTURER_MULTIPLE_SITES("Single manufacturer with multiple production sites"),

		@XmlEnumValue("Multiple manufacturers")
		MULTIPLE_MANUFACTURERS("Multiple manufacturers");

		private final String value;

		VariabilityType(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}
}
