package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.List;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.annotations.Label;

import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.util.Val;

public class ModelName implements Copyable<ModelName> {

	@Label
	@XmlElement(name = "baseName", required = true)
	private List<LangString> name;

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

	public List<LangString> getName() {
		return name != null ? name : List.of();
	}

	public List<LangString> getTechnicalDetails() {
		return technicalDetails != null ? technicalDetails : List.of();
	}

	public List<LangString> getMixAndLocation() {
		return mixAndLocation != null ? mixAndLocation : List.of();
	}

	public List<LangString> getFlowProperties() {
		return flowProperties != null ? flowProperties : List.of();
	}

	// endregion

	// region setters

	public ModelName withName(List<LangString> name) {
		this.name = name;
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

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
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
		Val.copy(name, copy::withName);
		Val.copy(technicalDetails, copy::withTechnicalDetails);
		Val.copy(mixAndLocation, copy::withMixAndLocation);
		Val.copy(flowProperties, copy::withFlowProperties);
		return copy;
	}

}
