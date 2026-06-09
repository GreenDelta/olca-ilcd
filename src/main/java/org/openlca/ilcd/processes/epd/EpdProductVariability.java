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
public final class EpdProductVariability implements Copyable<EpdProductVariability> {

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

	public EpdProductVariability withType(VariabilityType type) {
		this.type = type;
		return this;
	}

	public EpdProductVariability withVariation(Double variation) {
		this.variation = variation;
		return this;
	}

	public EpdProductVariability withRange(String range) {
		this.range = range;
		return this;
	}

	// endregion

	@Override
	public EpdProductVariability copy() {
		var copy = new EpdProductVariability();
		copy.withType(type);
		copy.withVariation(variation);
		copy.withRange(range);
		return copy;
	}

	@XmlEnum
	@XmlType(name = "productVariabilityValues")
	public enum VariabilityType {

		@XmlEnumValue("Single product")
		SINGLE_PRODUCT("Single product"),

		@XmlEnumValue("Range of products where variability is described")
		RANGE_OF_PRODUCTS("Range of products where variability is described");

		private final String value;

		VariabilityType(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}
}
