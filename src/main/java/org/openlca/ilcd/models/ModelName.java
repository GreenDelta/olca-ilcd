package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.annotations.Label;

import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.util.Val;

public class ModelName implements Copyable<ModelName> {

	@Label
	@XmlElement(name = "baseName", required = true)
	private List<LangString> baseName;

	@Label
	@XmlElement(name = "treatmentStandardsRoutes")
	private List<LangString> technicalDetails;

	@Label
	@XmlElement(name = "mixAndLocationTypes")
	private List<LangString> mixAndLocation;

	@Label
	@XmlElement(name = "functionalUnitFlowProperties")
	private List<LangString> flowProperties;

	// region getters

	public List<LangString> getBaseName() {
		return baseName != null ? baseName : Collections.emptyList();
	}

	public List<LangString> getTechnicalDetails() {
		return technicalDetails != null ? technicalDetails : Collections.emptyList();
	}

	public List<LangString> getMixAndLocation() {
		return mixAndLocation != null ? mixAndLocation : Collections.emptyList();
	}

	public List<LangString> getFlowProperties() {
		return flowProperties != null ? flowProperties : Collections.emptyList();
	}

	// endregion

	// region setters

	public ModelName withBaseName(List<LangString> name) {
		this.baseName = name;
		return this;
	}

	public ModelName withTechnicalDetails(List<LangString> technicalDetails) {
		this.technicalDetails = technicalDetails;
		return this;
	}

	public ModelName withMixAndLocation(List<LangString> mixAndLocation) {
		this.mixAndLocation = mixAndLocation;
		return this;
	}

	public ModelName withFlowProperties(List<LangString> flowProperties) {
		this.flowProperties = flowProperties;
		return this;
	}

	public List<LangString> withBaseName() {
		if (baseName == null) {
			baseName = new ArrayList<>();
		}
		return baseName;
	}

	public List<LangString> withTechnicalDetails() {
		if (technicalDetails == null) {
			technicalDetails = new ArrayList<>();
		}
		return technicalDetails;
	}

	public List<LangString> withMixAndLocation() {
		if (mixAndLocation == null) {
			mixAndLocation = new ArrayList<>();
		}
		return mixAndLocation;
	}

	public List<LangString> withFlowProperties() {
		if (flowProperties == null) {
			flowProperties = new ArrayList<>();
		}
		return flowProperties;
	}

	// endregion

	@Override
	public ModelName copy() {
		var copy = new ModelName();
		Val.copy(baseName, copy::withBaseName);
		Val.copy(technicalDetails, copy::withTechnicalDetails);
		Val.copy(mixAndLocation, copy::withMixAndLocation);
		Val.copy(flowProperties, copy::withFlowProperties);
		return copy;
	}

}
