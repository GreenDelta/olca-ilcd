
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIMethodType", propOrder = {
	"flowType",
	"other"
})
public class InventoryMethod implements Copyable<InventoryMethod> {

	@XmlElement(name = "typeOfDataSet", required = true)
	private FlowType flowType;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public FlowType getFlowType() {
		return flowType;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public InventoryMethod withFlowType(FlowType flowType) {
		this.flowType = flowType;
		return this;
	}

	public InventoryMethod withOther(Other other) {
		this.other = other;
		return this;
	}

	public InventoryMethod withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
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
	public InventoryMethod copy() {
		var copy = new InventoryMethod();
		copy.withFlowType(flowType);
		Val.copy(other, this::withOther);
		Val.copy(otherAttributes, this::withOtherAttributes);
		return copy;
	}
}
