package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.FlowCompleteness;
import org.openlca.ilcd.commons.ImpactCategory;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletenessElementaryFlowsType")
public class FlowCompletenessEntry implements Copyable<FlowCompletenessEntry> {

	@XmlAttribute(name = "type")
	private ImpactCategory impact;

	@XmlAttribute(name = "value")
	private FlowCompleteness value;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public ImpactCategory getImpact() {
		return impact;
	}

	public FlowCompleteness getValue() {
		return value;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public FlowCompletenessEntry withImpact(ImpactCategory impact) {
		this.impact = impact;
		return this;
	}

	public FlowCompletenessEntry withValue(FlowCompleteness value) {
		this.value = value;
		return this;
	}

	public FlowCompletenessEntry withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public FlowCompletenessEntry copy() {
		var copy = new FlowCompletenessEntry();
		copy.withImpact(impact);
		copy.withValue(value);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
