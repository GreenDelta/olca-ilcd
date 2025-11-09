package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllocationType")
public class AllocationFactor implements Copyable<AllocationFactor> {

	@XmlAttribute(name = "internalReferenceToCoProduct")
	private int productExchangeId;

	@XmlAttribute(name = "allocatedFraction")
	private double fraction;

	// region getters

	public int getProductExchangeId() {
		return productExchangeId;
	}

	public double getFraction() {
		return fraction;
	}

	// endregion

	// region setters

	public AllocationFactor withProductExchangeId(int productExchangeId) {
		this.productExchangeId = productExchangeId;
		return this;
	}

	public AllocationFactor withFraction(double fraction) {
		this.fraction = fraction;
		return this;
	}

	// endregion

	@Override
	public AllocationFactor copy() {
		var copy = new AllocationFactor();
		copy.withProductExchangeId(productExchangeId);
		copy.withFraction(fraction);
		return copy;
	}

}
