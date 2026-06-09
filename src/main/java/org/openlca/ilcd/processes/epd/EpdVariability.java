package org.openlca.ilcd.processes.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdVariability implements Copyable<EpdVariability> {

	@XmlElement(name = "manufacturerVariability", namespace = Vocab.EPD_2024)
	private EpdManufacturerVariability manufacturerVariability;

	@XmlElement(name = "productVariability", namespace = Vocab.EPD_2024)
	private EpdProductVariability productVariability;

	@XmlElement(name = "variabilityDescription", namespace = Vocab.EPD_2024)
	private List<LangString> descriptions;

	// region getters

	public EpdManufacturerVariability getManufacturerVariability() {
		return manufacturerVariability;
	}

	public EpdProductVariability getProductVariability() {
		return productVariability;
	}

	public List<LangString> getDescriptions() {
		return descriptions != null ? descriptions : List.of();
	}

	// endregion

	// region setters

	public EpdVariability withManufacturerVariability(EpdManufacturerVariability v) {
		this.manufacturerVariability = v;
		return this;
	}

	public EpdVariability withProductVariability(EpdProductVariability v) {
		this.productVariability = v;
		return this;
	}

	public EpdVariability withDescriptions(List<LangString> descriptions) {
		this.descriptions = descriptions;
		return this;
	}

	public List<LangString> withDescriptions() {
		if (descriptions == null) {
			descriptions = new ArrayList<>();
		}
		return descriptions;
	}

	// endregion

	@Override
	public EpdVariability copy() {
		var copy = new EpdVariability();
		Val.copy(manufacturerVariability, copy::withManufacturerVariability);
		Val.copy(productVariability, copy::withProductVariability);
		Val.copy(descriptions, copy::withDescriptions);
		return copy;
	}
}
