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
public final class EpdSoilAndWaterImpacts implements Copyable<EpdSoilAndWaterImpacts> {

	@XmlElement(name = "soilAndWaterImpactsDescription", namespace = Vocab.EPD_2024)
	private List<LangString> descriptions;

	// region getters

	public List<LangString> getDescriptions() {
		return descriptions != null ? descriptions : List.of();
	}

	// endregion

	// region setters

	public EpdSoilAndWaterImpacts withDescriptions(List<LangString> descriptions) {
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
	public EpdSoilAndWaterImpacts copy() {
		var copy = new EpdSoilAndWaterImpacts();
		Val.copy(descriptions, copy::withDescriptions);
		return copy;
	}
}
