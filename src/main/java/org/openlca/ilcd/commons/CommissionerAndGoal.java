package org.openlca.ilcd.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommissionerAndGoalType", propOrder = { "commissioners",
		"project", "intendedApplications", "other" })
public class CommissionerAndGoal implements Copyable<CommissionerAndGoal> {

	@XmlElement(name = "referenceToCommissioner")
	private List<Ref> commissioners;

	@Label
	private LangString2 project;

	@FreeText
	private LangString2 intendedApplications;

	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<Ref> getCommissioners() {
		return commissioners;
	}

	public LangString2 getProject() {
		return project != null
			? project
			: LangString2.empty();
	}

	public LangString2 getIntendedApplications() {
		return intendedApplications != null
			? intendedApplications
			: LangString2.empty();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

	// endregion

  // region fluent setters

	public List<Ref> withCommissioners() {
		if (commissioners == null) {
			commissioners = new ArrayList<>();
		}
		return commissioners;
	}

	public LangString2 withProject() {
		if (project == null) {
			project = new LangString2();
		}
		return project;
	}

	public LangString2 withIntendedApplications() {
		if (intendedApplications == null) {
			intendedApplications = new LangString2();
		}
		return intendedApplications;
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
	public CommissionerAndGoal copy() {
		var copy = new CommissionerAndGoal();
		Ref.copy(commissioners, copy.commissioners);
		LangString2.copy(project, copy::withProject);
		LangString2.copy(intendedApplications, copy::withIntendedApplications);
		if (other != null) {
			copy.other = other.copy();
		}
		copy.otherAttributes.putAll(otherAttributes);
		return copy;
	}

}
