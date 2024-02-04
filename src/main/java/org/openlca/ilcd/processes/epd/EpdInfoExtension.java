package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdInfoExtension implements Copyable<EpdInfoExtension> {

	@XmlElement(name = "safetyMargins", namespace = Vocab.EPD_2013)
	private EpdSafetyMargins safetyMargins;

	@XmlElementWrapper(name = "scenarios", namespace = Vocab.EPD_2013)
	@XmlElement(name = "scenario", namespace = Vocab.EPD_2013)
	private List<EpdScenario> scenarios;

	@XmlElement(name = "modules", namespace = Vocab.EPD_2013)
	private List<EpdModule> modules;

	@XmlElement(name = "contentDeclaration", namespace = Vocab.EPD_2019)
	private EpdContentDeclaration contentDeclaration;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public EpdSafetyMargins getSafetyMargins() {
		return safetyMargins;
	}

	public List<EpdScenario> getScenarios() {
		return scenarios != null ? scenarios : List.of();
	}

	public List<EpdModule> getModules() {
		return modules != null ? modules : List.of();
	}

	public EpdContentDeclaration getContentDeclaration() {
		return contentDeclaration;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdInfoExtension withSafetyMargins(EpdSafetyMargins safetyMargins) {
		this.safetyMargins = safetyMargins;
		return this;
	}

	public EpdInfoExtension withScenarios(List<EpdScenario> scenarios) {
		this.scenarios = scenarios;
		return this;
	}

	public EpdInfoExtension withModules(List<EpdModule> modules) {
		this.modules = modules;
		return this;
	}

	public EpdInfoExtension withContentDeclaration(EpdContentDeclaration contentDeclaration) {
		this.contentDeclaration = contentDeclaration;
		return this;
	}

	public EpdInfoExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public EpdSafetyMargins withSafetyMargins() {
		if (safetyMargins == null) {
			safetyMargins = new EpdSafetyMargins();
		}
		return safetyMargins;
	}

	public List<EpdScenario> withScenarios() {
		if (scenarios == null) {
			scenarios = new ArrayList<>();
		}
		return scenarios;
	}

	public List<EpdModule> withModules() {
		if (modules == null) {
			modules = new ArrayList<>();
		}
		return modules;
	}

	public EpdContentDeclaration withContentDeclaration() {
		if (contentDeclaration == null) {
			contentDeclaration = new EpdContentDeclaration();
		}
		return contentDeclaration;
	}

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdInfoExtension copy() {
		var copy = new EpdInfoExtension();
		Val.copy(safetyMargins, copy::withSafetyMargins);
		Val.copy(scenarios, copy::withScenarios);
		Val.copy(modules, copy::withModules);
		Val.copy(contentDeclaration, copy::withContentDeclaration);
		Val.copyAny(any, copy::withAny);
		return copy;
	}


}
