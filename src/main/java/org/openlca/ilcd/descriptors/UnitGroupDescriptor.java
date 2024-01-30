
package org.openlca.ilcd.descriptors;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

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
public class UnitGroupDescriptor extends Descriptor
	implements Copyable<UnitGroupDescriptor> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/UnitGroup")
	private String referenceUnit;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	@XmlAttribute(name = "sourceId", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String sourceId;

	@Override
	protected DataSetType getType() {
		return DataSetType.UNIT_GROUP;
	}

	// region getters

	public String getReferenceUnit() {
		return referenceUnit;
	}

	public String getHref() {
		return href;
	}

	public String getSourceId() {
		return sourceId;
	}

	// endregion

	// region setters

	public UnitGroupDescriptor withReferenceUnit(String referenceUnit) {
		this.referenceUnit = referenceUnit;
		return this;
	}

	public UnitGroupDescriptor withHref(String href) {
		this.href = href;
		return this;
	}

	public UnitGroupDescriptor withSourceId(String sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	// endregion

	@Override
	public UnitGroupDescriptor copy() {
		var copy = new UnitGroupDescriptor();
		copy.withReferenceUnit(referenceUnit);
		copy.withHref(href);
		copy.withSourceId(sourceId);
		return copy;
	}

}
