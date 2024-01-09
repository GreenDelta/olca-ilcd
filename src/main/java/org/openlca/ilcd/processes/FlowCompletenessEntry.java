package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.FlowCompleteness;
import org.openlca.ilcd.commons.ImpactCategory;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletenessElementaryFlowsType")
public class FlowCompletenessEntry implements Copyable<FlowCompletenessEntry> {

	@XmlAttribute(name = "type")
	public ImpactCategory impact;

	@XmlAttribute(name = "value")
	public FlowCompleteness value;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public FlowCompletenessEntry copy() {
		var clone = new FlowCompletenessEntry();
		clone.impact = impact;
		clone.value = value;
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
