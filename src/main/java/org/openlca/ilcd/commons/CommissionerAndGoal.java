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
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommissionerAndGoalType", propOrder = {"commissioners",
	"project", "intendedApplications", "other"})
public class CommissionerAndGoal implements Copyable<CommissionerAndGoal> {

	@XmlElement(name = "referenceToCommissioner")
	private List<Ref> commissioners;

	@Label
	private List<LangString> project;

	@FreeText
	private List<LangString> intendedApplications;

	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	public boolean isEmpty() {
		return Val.isEmpty(commissioners)
			&& Val.isEmpty(project)
			&& Val.isEmpty(intendedApplications)
			&& Val.isEmpty(other)
			&& Val.isEmpty(otherAttributes);
	}

	public void trim() {
		if (Val.isEmpty(commissioners)) {
			commissioners = null;
		}
		if (Val.isEmpty(project)) {
			project = null;
		}
		if (Val.isEmpty(intendedApplications)) {
			intendedApplications = null;
		}
		if (Val.isEmpty(other)) {
			other = null;
		}
		if (Val.isEmpty(otherAttributes)) {
			otherAttributes = null;
		}
	}

	// region getters

	public List<Ref> getCommissioners() {
		return commissioners;
	}

	public List<LangString> getProject() {
		return project != null ? project : List.of();
	}

	public List<LangString> getIntendedApplications() {
		return intendedApplications != null ? intendedApplications : List.of();
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

	public List<LangString> withProject() {
		if (project == null) {
			project = new ArrayList<>();
		}
		return project;
	}

	public List<LangString> withIntendedApplications() {
		if (intendedApplications == null) {
			intendedApplications = new ArrayList<>();
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
		LangString.copy(project, copy::withProject);
		LangString.copy(intendedApplications, copy::withIntendedApplications);
		if (other != null) {
			copy.other = other.copy();
		}
		copy.otherAttributes.putAll(otherAttributes);
		return copy;
	}

}
