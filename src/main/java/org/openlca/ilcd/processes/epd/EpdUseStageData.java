package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdUseStageData implements Copyable<EpdUseStageData> {

	@XmlAttribute(name = "scenario", namespace = Vocab.EPD_2024)
	private String scenario;

	@XmlElement(name = "soilAndWaterImpacts", namespace = Vocab.EPD_2024)
	private EpdSoilAndWaterImpacts soilAndWaterImpacts;

	// region getters

	public String getScenario() {
		return scenario;
	}

	public EpdSoilAndWaterImpacts getSoilAndWaterImpacts() {
		return soilAndWaterImpacts;
	}

	// endregion

	// region setters

	public EpdUseStageData withScenario(String scenario) {
		this.scenario = scenario;
		return this;
	}

	public EpdUseStageData withSoilAndWaterImpacts(EpdSoilAndWaterImpacts soilAndWaterImpacts) {
		this.soilAndWaterImpacts = soilAndWaterImpacts;
		return this;
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
