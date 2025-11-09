
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.flows.epd.EpdMethodExtension;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIMethodType", propOrder = {
	"flowType",
	"epdExtension"
})
public class InventoryMethod implements Copyable<InventoryMethod> {

	@XmlElement(name = "typeOfDataSet", required = true)
	private FlowType flowType;

	@XmlElement(name= "other", namespace = Vocab.COMMON)
	private EpdMethodExtension epdExtension;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public FlowType getFlowType() {
		return flowType;
	}

	public EpdMethodExtension getEpdExtension() {
		return epdExtension;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public InventoryMethod withFlowType(FlowType flowType) {
		this.flowType = flowType;
		return this;
	}

	public InventoryMethod withEpdExtension(EpdMethodExtension ext) {
		this.epdExtension = ext;
		return this;
	}

	public InventoryMethod withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public EpdMethodExtension withEpdExtension() {
		if (epdExtension == null) {
			epdExtension = new EpdMethodExtension();
		}
		return epdExtension;
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
		Val.copy(epdExtension, copy::withEpdExtension);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
