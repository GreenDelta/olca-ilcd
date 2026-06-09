package org.openlca.ilcd.processes.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdUseStageData implements Copyable<EpdUseStageData> {

	@XmlAttribute(name = "scenario", namespace = Vocab.EPD_2024)
	private String scenario;

	@XmlElementWrapper(name = "soilAndWaterImpacts", namespace = Vocab.EPD_2024)
	@XmlElement(name = "soilAndWaterImpactsDescription", namespace = Vocab.EPD_2024)
	private List<LangString> soilAndWaterImpacts;

	// region getters

	public String getScenario() {
		return scenario;
	}

	public List<LangString> getSoilAndWaterImpacts() {
		return soilAndWaterImpacts != null ? soilAndWaterImpacts : List.of();
	}

	// endregion

	// region setters

	public EpdUseStageData withScenario(String scenario) {
		this.scenario = scenario;
		return this;
	}

	public EpdUseStageData withSoilAndWaterImpacts(List<LangString> impacts) {
		this.soilAndWaterImpacts = impacts;
		return this;
	}

	public List<LangString> withSoilAndWaterImpacts() {
		if (soilAndWaterImpacts == null) {
			soilAndWaterImpacts = new ArrayList<>();
		}
		return soilAndWaterImpacts;
	}

	// endregion

	@Override
	public EpdUseStageData copy() {
		var copy = new EpdUseStageData();
		copy.withScenario(scenario);
		Val.copy(soilAndWaterImpacts, copy::withSoilAndWaterImpacts);
		return copy;
	}
}
