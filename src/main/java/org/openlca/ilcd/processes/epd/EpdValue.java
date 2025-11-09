package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdValue implements Copyable<EpdValue> {

	@XmlValue
	private Double amount;

	@XmlAttribute(name="module", namespace = Vocab.EPD_2013)
	private String module;

	@XmlAttribute(name="scenario", namespace = Vocab.EPD_2013)
	private String scenario;

	// region getters

	public Double getAmount() {
		return amount;
	}

	public String getModule() {
		return module;
	}

	public String getScenario() {
		return scenario;
	}

	// endregion

	// region setters

	public EpdValue withAmount(Double amount) {
		this.amount = amount;
		return this;
	}

	public EpdValue withModule(String module) {
		this.module = module;
		return this;
	}

	public EpdValue withScenario(String scenario) {
		this.scenario = scenario;
		return this;
	}

	// endregion

	@Override
	public EpdValue copy() {
		var copy = new EpdValue();
		copy.withAmount(amount);
		copy.withModule(module);
		copy.withScenario(scenario);
		return copy;
	}
}
