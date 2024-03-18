package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// region getters

	public List<Ref> getCommissioners() {
		return commissioners != null ? commissioners : Collections.emptyList();
	}

	public List<LangString> getProject() {
		return project != null ? project : Collections.emptyList();
	}

	public List<LangString> getIntendedApplications() {
		return intendedApplications != null
			? intendedApplications
			: Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region fluent setters

	public CommissionerAndGoal withCommissioners(List<Ref> commissioners) {
		this.commissioners = commissioners;
		return this;
	}

	public CommissionerAndGoal withProject(List<LangString> project) {
		this.project = project;
		return this;
	}

	public CommissionerAndGoal withIntendedApplications(List<LangString> intendedApplications) {
		this.intendedApplications = intendedApplications;
		return this;
	}

	public CommissionerAndGoal withOther(Other other) {
		this.other = other;
		return this;
	}

	public CommissionerAndGoal withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

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
		Val.copy(commissioners, copy::withCommissioners);
		Val.copy(project, copy::withProject);
		Val.copy(intendedApplications, copy::withIntendedApplications);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
