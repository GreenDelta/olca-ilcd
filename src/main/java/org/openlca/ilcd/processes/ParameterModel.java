package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MathematicalRelationsType", propOrder = { "description",
		"parameters", "other" })
public class ParameterModel implements Copyable<ParameterModel> {

	@FreeText
	@XmlElement(name = "modelDescription")
	private List<LangString> description;

	@XmlElement(name = "variableParameter")
	private List<Parameter> parameters;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<LangString> getDescription() {
		return description != null ? description : Collections.emptyList();
	}

	public List<Parameter> getParameters() {
		return parameters != null ? parameters : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public ParameterModel withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public ParameterModel withParameters(List<Parameter> parameters) {
		this.parameters = parameters;
		return this;
	}

	public ParameterModel withOther(Other other) {
		this.other = other;
		return this;
	}

	public ParameterModel withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	public List<Parameter> withParameters() {
		if (parameters == null) {
			parameters = new ArrayList<>();
		}
		return parameters;
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
	public ParameterModel copy() {
		var copy = new ParameterModel();
		Val.copy(description, copy::withDescription);
		Val.copy(parameters, copy::withParameters);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
