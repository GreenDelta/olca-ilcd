
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameType", propOrder = {
	"baseName",
	"treatmentStandardsRoutes",
	"mixAndLocationTypes",
	"flowProperties",
	"other"
})
public class FlowName implements Copyable<FlowName> {

	@Label
	@XmlElement(required = true)
	private List<LangString> baseName;

	@Label
	private List<LangString> treatmentStandardsRoutes;

	@Label
	private List<LangString> mixAndLocationTypes;

	@Label
	private List<LangString> flowProperties;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<LangString> getBaseName() {
		return baseName != null ? baseName : Collections.emptyList();
	}

	public List<LangString> getTreatmentStandardsRoutes() {
		return treatmentStandardsRoutes != null
			? treatmentStandardsRoutes
			: Collections.emptyList();
	}

	public List<LangString> getMixAndLocationTypes() {
		return mixAndLocationTypes != null
			? mixAndLocationTypes
			: Collections.emptyList();
	}

	public List<LangString> getFlowProperties() {
		return flowProperties != null
			? flowProperties
			: Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public FlowName withBaseName(List<LangString> baseName) {
		this.baseName = baseName;
		return this;
	}

	public FlowName withTreatmentStandardsRoutes(List<LangString> treatmentStandardsRoutes) {
		this.treatmentStandardsRoutes = treatmentStandardsRoutes;
		return this;
	}

	public FlowName withMixAndLocationTypes(List<LangString> mixAndLocationTypes) {
		this.mixAndLocationTypes = mixAndLocationTypes;
		return this;
	}

	public FlowName withFlowProperties(List<LangString> flowProperties) {
		this.flowProperties = flowProperties;
		return this;
	}

	public FlowName withOther(Other other) {
		this.other = other;
		return this;
	}

	public FlowName withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withBaseName() {
		if (baseName == null) {
			baseName = new ArrayList<>();
		}
		return baseName;
	}

	public List<LangString> withTreatmentStandardsRoutes() {
		if (treatmentStandardsRoutes == null) {
			treatmentStandardsRoutes = new ArrayList<>();
		}
		return treatmentStandardsRoutes;
	}

	public List<LangString> withMixAndLocationTypes() {
		if (mixAndLocationTypes == null) {
			mixAndLocationTypes = new ArrayList<>();
		}
		return mixAndLocationTypes;
	}

	public List<LangString> withFlowProperties() {
		if (flowProperties == null) {
			flowProperties = new ArrayList<>();
		}
		return flowProperties;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public FlowName copy() {
		var copy = new FlowName();
		Val.copy(baseName, copy::withBaseName);
		Val.copy(treatmentStandardsRoutes, copy::withTreatmentStandardsRoutes);
		Val.copy(mixAndLocationTypes, copy::withMixAndLocationTypes);
		Val.copy(flowProperties, copy::withFlowProperties);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
