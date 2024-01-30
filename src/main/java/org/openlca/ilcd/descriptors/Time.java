
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"referenceYear",
	"validUntil"
})
@XmlRootElement(name = "time")
public class Time implements Copyable<Time> {

	private Integer referenceYear;
	private Integer validUntil;

	// region getters

	public Integer getReferenceYear() {
		return referenceYear;
	}

	public Integer getValidUntil() {
		return validUntil;
	}

	// endregion

	// region setters

	public Time withReferenceYear(Integer referenceYear) {
		this.referenceYear = referenceYear;
		return this;
	}

	public Time withValidUntil(Integer validUntil) {
		this.validUntil = validUntil;
		return this;
	}

	// endregion

	@Override
	public Time copy() {
		var copy = new Time();
		copy.withReferenceYear(referenceYear);
		copy.withValidUntil(validUntil);
		return copy;
	}

}
