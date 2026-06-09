package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdEolData implements Copyable<EpdEolData> {

	@XmlAttribute(name = "scenario", namespace = Vocab.EPD_2024)
	private String scenario;

	@XmlElement(name = "collection", namespace = Vocab.EPD_2024)
	private EpdCollection collection;

	@XmlElement(name = "recovery", namespace = Vocab.EPD_2024)
	private EpdRecovery recovery;

	@XmlElement(name = "disposal", namespace = Vocab.EPD_2024)
	private EpdDisposal disposal;

	// region getters

	public String getScenario() {
		return scenario;
	}

	public EpdCollection getCollection() {
		return collection;
	}

	public EpdRecovery getRecovery() {
		return recovery;
	}

	public EpdDisposal getDisposal() {
		return disposal;
	}

	// endregion

	// region setters

	public EpdEolData withScenario(String scenario) {
		this.scenario = scenario;
		return this;
	}

	public EpdEolData withCollection(EpdCollection collection) {
		this.collection = collection;
		return this;
	}

	public EpdEolData withRecovery(EpdRecovery recovery) {
		this.recovery = recovery;
		return this;
	}

	public EpdEolData withDisposal(EpdDisposal disposal) {
		this.disposal = disposal;
		return this;
	}

	// endregion

	@Override
	public EpdEolData copy() {
		var copy = new EpdEolData();
		copy.withScenario(scenario);
		Val.copy(collection, copy::withCollection);
		Val.copy(recovery, copy::withRecovery);
		Val.copy(disposal, copy::withDisposal);
		return copy;
	}
}
