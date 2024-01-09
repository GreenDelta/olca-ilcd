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
	public final List<Ref> commissioners = new ArrayList<>();

	@Label
	public final List<LangString> project = new ArrayList<>();

	@FreeText
	public final List<LangString> intendedApplications = new ArrayList<>();

	public Other other;

	@XmlAnyAttribute
	private final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public CommissionerAndGoal copy() {
		var clone = new CommissionerAndGoal();
		Ref.copy(commissioners, clone.commissioners);
		LangString.copy(project, clone.project);
		LangString.copy(intendedApplications, clone.intendedApplications);
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}

}
