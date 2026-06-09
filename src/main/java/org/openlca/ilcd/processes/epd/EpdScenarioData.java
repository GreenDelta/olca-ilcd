package org.openlca.ilcd.processes.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdScenarioData implements Copyable<EpdScenarioData> {

	@XmlElement(name = "useStageScenarioData", namespace = Vocab.EPD_2024)
	private List<EpdUseStageData> useStageData;

	@XmlElement(name = "eolScenarioData", namespace = Vocab.EPD_2024)
	private List<EpdEolData> eolData;

	// region getters

	public List<EpdUseStageData> getUseStageData() {
		return useStageData != null ? useStageData : List.of();
	}

	public List<EpdEolData> getEolData() {
		return eolData != null ? eolData : List.of();
	}

	// endregion

	// region setters

	public EpdScenarioData withUseStageData(List<EpdUseStageData> useStageData) {
		this.useStageData = useStageData;
		return this;
	}

	public EpdScenarioData withEolData(List<EpdEolData> eolData) {
		this.eolData = eolData;
		return this;
	}

	public List<EpdUseStageData> withUseStageData() {
		if (useStageData == null) {
			useStageData = new ArrayList<>();
		}
		return useStageData;
	}

	public List<EpdEolData> withEolData() {
		if (eolData == null) {
			eolData = new ArrayList<>();
		}
		return eolData;
	}

	// endregion

	@Override
	public EpdScenarioData copy() {
		var copy = new EpdScenarioData();
		Val.copy(useStageData, copy::withUseStageData);
		Val.copy(eolData, copy::withEolData);
		return copy;
	}
}
