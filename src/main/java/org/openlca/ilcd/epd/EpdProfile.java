package org.openlca.ilcd.epd;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profile", namespace = Vocab.OLCA_EPD)
@XmlAccessorType(XmlAccessType.FIELD)
public class EpdProfile implements Copyable<EpdProfile> {

	@XmlElement(name = "id", namespace = Vocab.OLCA_EPD)
	private String id;

	@XmlElement(name = "name", namespace = Vocab.OLCA_EPD)
	private String name;

	@XmlElement(name = "description", namespace = Vocab.OLCA_EPD)
	private String description;

	@XmlElement(name="dataUrl", namespace = Vocab.OLCA_EPD)
	private String dataUrl;

	@XmlElement(name="complianceSystem", namespace = Vocab.OLCA_EPD)
	private Ref complianceSystem;

	@XmlElementWrapper(name="modules", namespace = Vocab.OLCA_EPD)
	@XmlElement(name ="module", namespace = Vocab.OLCA_EPD)
	private List<EpdProfileModule> modules;

	@XmlElementWrapper(name = "indicators", namespace = Vocab.OLCA_EPD)
	@XmlElement(name="indicator", namespace = Vocab.OLCA_EPD)
	private List<EpdProfileIndicator> indicators;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public Ref getComplianceSystem() {
		return complianceSystem;
	}

	public List<EpdProfileModule> getModules() {
		return modules != null ? modules : Collections.emptyList();
	}

	public List<EpdProfileIndicator> getIndicators() {
		return indicators;
	}

	public EpdProfile withId(String id) {
		this.id = id;
		return this;
	}

	public EpdProfile withName(String name) {
		this.name = name;
		return this;
	}

	public EpdProfile withDescription(String description) {
		this.description = description;
		return this;
	}

	public EpdProfile withDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
		return this;
	}

	public EpdProfile withComplianceSystem(Ref complianceSystem) {
		this.complianceSystem = complianceSystem;
		return this;
	}

	public Ref withComplianceSystem() {
		if (complianceSystem == null) {
			complianceSystem = new Ref();
		}
		return complianceSystem;
	}

	public EpdProfile withModules(List<EpdProfileModule> modules) {
		this.modules = modules;
		return this;
	}

	public List<EpdProfileModule> withModules() {
		if (modules == null) {
			modules = new ArrayList<>();
		}
		return modules;
	}

	public EpdProfile withIndicators(List<EpdProfileIndicator> indicators) {
		this.indicators = indicators;
		return this;
	}

	public List<EpdProfileIndicator> withIndicators() {
		if (indicators == null) {
			indicators = new ArrayList<>();
		}
		return indicators;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof EpdProfile other))
			return false;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public int hashCode() {
		return id != null
			? id.hashCode()
			: super.hashCode();
	}

	@Override
	public EpdProfile copy() {
		var copy = new EpdProfile()
			.withId(id)
			.withName(name)
			.withDescription(description)
			.withDataUrl(dataUrl);
		Val.copy(complianceSystem, copy::withComplianceSystem);
		Val.copy(modules, copy::withModules);
		Val.copy(indicators, copy::withIndicators);
		return copy;
	}
}

