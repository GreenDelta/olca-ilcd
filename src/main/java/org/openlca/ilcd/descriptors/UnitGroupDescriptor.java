
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.DataSetType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"uuid",
	"uri",
	"version",
	"name",
	"classification",
	"comment",
	"referenceUnit"
})
public class UnitGroupDescriptor extends Descriptor<UnitGroupDescriptor> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/UnitGroup")
	private String referenceUnit;

	@Override
	protected DataSetType getType() {
		return DataSetType.UNIT_GROUP;
	}

	// region getters

	public String getReferenceUnit() {
		return referenceUnit;
	}

	// endregion

	// region setters

	public UnitGroupDescriptor withReferenceUnit(String referenceUnit) {
		this.referenceUnit = referenceUnit;
		return this;
	}

	// endregion

	@Override
	public UnitGroupDescriptor copy() {
		var copy = new UnitGroupDescriptor();
		copyBase(copy);
		copy.withReferenceUnit(referenceUnit);
		return copy;
	}

}
