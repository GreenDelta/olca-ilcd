package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "refProcess" })
public class QuantitativeReference implements Copyable<QuantitativeReference> {

	@XmlElement(name = "referenceToReferenceProcess")
	private Integer refProcess;

	// region getters

	public Integer getRefProcess() {
		return refProcess;
	}

	// endregion

	// region setters

	public QuantitativeReference withRefProcess(Integer refProcess) {
		this.refProcess = refProcess;
		return this;
	}

	// endregion

	@Override
	public QuantitativeReference copy() {
		var copy = new QuantitativeReference();
		copy.withRefProcess(refProcess);
		return copy;
	}

}
