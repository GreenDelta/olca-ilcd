package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.ModellingApproach;
import org.openlca.ilcd.commons.ModellingPrinciple;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "methodPrinciple", "approach" })
@XmlRootElement(name = "lciMethodInformation")
public class InventoryMethod implements Copyable<InventoryMethod> {

	private ModellingPrinciple methodPrinciple;
	private List<ModellingApproach> approach;

	// region getters

	public ModellingPrinciple getMethodPrinciple() {
		return methodPrinciple;
	}

	public List<ModellingApproach> getApproach() {
		return approach != null ? approach : List.of();
	}

	// endregion

	// region setters

	public InventoryMethod withMethodPrinciple(ModellingPrinciple methodPrinciple) {
		this.methodPrinciple = methodPrinciple;
		return this;
	}

	public InventoryMethod withApproach(List<ModellingApproach> approach) {
		this.approach = approach;
		return this;
	}

	public List<ModellingApproach> withApproach() {
		if (approach == null) {
			approach = new ArrayList<>();
		}
		return approach;
	}

	// endregion

	@Override
	public InventoryMethod copy() {
		var copy = new InventoryMethod();
		copy.withMethodPrinciple(methodPrinciple);
		if (approach != null) {
			copy.withApproach().addAll(approach);
		}
		return copy;
	}

}
