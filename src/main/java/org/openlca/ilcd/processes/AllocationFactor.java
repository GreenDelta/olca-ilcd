package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllocationType")
public class AllocationFactor implements Copyable<AllocationFactor> {

	@XmlAttribute(name = "internalReferenceToCoProduct")
	public int productExchangeId;

	@XmlAttribute(name = "allocatedFraction")
	public double fraction;

	@Override
	public AllocationFactor copy() {
		var clone = new AllocationFactor();
		clone.productExchangeId = productExchangeId;
		clone.fraction = fraction;
		return clone;
	}
}
