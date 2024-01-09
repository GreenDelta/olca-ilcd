package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.commons.annotations.Label;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIAResultType", propOrder = {
		"method",
		"amount",
		"uncertaintyDistribution",
		"relativeStandardDeviation95In",
		"comment",
		"other"
})
public class LCIAResult implements Copyable<LCIAResult> {

	@XmlElement(name = "referenceToLCIAMethodDataSet", required = true)
	public Ref method;

	@XmlElement(name = "meanAmount")
	public double amount;

	@XmlElement(name = "uncertaintyDistributionType")
	public UncertaintyDistribution uncertaintyDistribution;

	public Double relativeStandardDeviation95In;

	@Label
	@XmlElement(name = "generalComment")
	public final List<LangString> comment = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public LCIAResult copy() {
		var clone = new LCIAResult();
		if (method != null)
			clone.method = method.copy();
		clone.amount = amount;
		clone.uncertaintyDistribution = uncertaintyDistribution;
		clone.relativeStandardDeviation95In = relativeStandardDeviation95In;
		LangString.copy(comment, clone.comment);
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
