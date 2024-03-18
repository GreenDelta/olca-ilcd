package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdModuleEntry implements Copyable<EpdModuleEntry> {

	@XmlAttribute(name = "name")
	private String module;

	@XmlAttribute(name = "scenario")
	private String scenario;

	@XmlAttribute(name = "productsystem-id")
	private String productSystemId;

	@XmlValue
	private String description;

	// region getters

	public String getModule() {
		return module;
	}

	public String getScenario() {
		return scenario;
	}

	public String getProductSystemId() {
		return productSystemId;
	}

	public String getDescription() {
		return description;
	}

	// endregion

	// region setters

	public EpdModuleEntry withModule(String name) {
		this.module = name;
		return this;
	}

	public EpdModuleEntry withScenario(String scenario) {
		this.scenario = scenario;
		return this;
	}

	public EpdModuleEntry withProductSystemId(String productSystemId) {
		this.productSystemId = productSystemId;
		return this;
	}

	public EpdModuleEntry withDescription(String description) {
		this.description = description;
		return this;
	}

	// endregion

	@Override
	public EpdModuleEntry copy() {
		return new EpdModuleEntry()
			.withModule(module)
			.withScenario(scenario)
			.withProductSystemId(productSystemId)
			.withDescription(description);
	}

}
