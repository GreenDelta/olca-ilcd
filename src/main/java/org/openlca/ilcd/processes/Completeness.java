package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.FlowCompleteness;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletenessType", propOrder = {
		"type",
		"supportedImpactMethods",
		"entries",
		"otherDetails",
		"other" })
public class Completeness implements Copyable<Completeness> {

	@XmlElement(name = "completenessProductModel")
	public FlowCompleteness type;

	@XmlElement(name = "referenceToSupportedImpactAssessmentMethods")
	public final List<Ref> supportedImpactMethods = new ArrayList<>();

	@XmlElement(name = "completenessElementaryFlows")
	public final List<FlowCompletenessEntry> entries = new ArrayList<>();

	@FreeText
	@XmlElement(name = "completenessOtherProblemField")
	public final List<LangString> otherDetails = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Completeness copy() {
		var clone = new Completeness();
		clone.type = type;
		Ref.copy(supportedImpactMethods, clone.supportedImpactMethods);
		for (FlowCompletenessEntry e : entries) {
			if (e == null)
				continue;
			clone.entries.add(e.copy());
		}
		LangString.copy(otherDetails, clone.otherDetails);
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
