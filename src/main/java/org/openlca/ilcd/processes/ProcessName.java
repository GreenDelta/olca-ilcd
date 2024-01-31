
package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameType", propOrder = {
		"name",
		"technicalDetails",
		"mixAndLocation",
		"flowProperties",
		"other"
})
public class ProcessName implements Copyable<ProcessName> {

	/**
	 * General descriptive name of the process and/or its main good(s) or
	 * service(s) and/or it's level of processing.
	 */
	@Label
	@XmlElement(name = "baseName", required = true)
	private List<LangString> name;

	/**
	 * Specifying information on the good, service, or process in technical
	 * term(s): treatment received, standard fulfilled, product quality, use
	 * information, production route name, educt name, primary / secondary etc.
	 * Separated by commata.
	 */
	@Label
	@XmlElement(name = "treatmentStandardsRoutes")
	private List<LangString> technicalDetails;

	/**
	 * Specifying information on the good, service, or process whether being a
	 * production mix or consumption mix, location type of availability (such as
	 * e.g. "to consumer" or "at plant"). Separated by commata.
	 */
	@Label
	@XmlElement(name = "mixAndLocationTypes")
	private List<LangString> mixAndLocation;

	/**
	 * Further, quantitative specifying information on the good, service or
	 * process in technical term(s): qualifying constituent(s)-content and / or
	 * energy-content per unit etc. as appropriate. Separated by commata. (Note:
	 * non-qualifying flow properties, CAS No, Synonyms, Chemical formulas etc.
	 * are documented exclusively in the "Flow data set".)
	 */
	@Label
	@XmlElement(name = "functionalUnitFlowProperties")
	private List<LangString> flowProperties;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

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

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public ProcessName withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public ProcessName withTechnicalDetails(List<LangString> technicalDetails) {
		this.technicalDetails = technicalDetails;
		return this;
	}

	public ProcessName withMixAndLocation(List<LangString> mixAndLocation) {
		this.mixAndLocation = mixAndLocation;
		return this;
	}

	public ProcessName withFlowProperties(List<LangString> flowProperties) {
		this.flowProperties = flowProperties;
		return this;
	}

	public ProcessName withOther(Other other) {
		this.other = other;
		return this;
	}

	public ProcessName withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
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
	public ProcessName copy() {
		var copy = new ProcessName();
		Val.copy(name, copy::withName);
		Val.copy(technicalDetails, copy::withTechnicalDetails);
		Val.copy(mixAndLocation, copy::withMixAndLocation);
		Val.copy(flowProperties, copy::withFlowProperties);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
