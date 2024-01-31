package org.openlca.ilcd.descriptors;

import org.openlca.ilcd.commons.DataSetType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImpactMethodDescriptor extends Descriptor<ImpactMethodDescriptor> {

	@Override
	protected DataSetType getType() {
		return DataSetType.IMPACT_METHOD;
	}

	@Override
	public ImpactMethodDescriptor copy() {
		var copy = new ImpactMethodDescriptor();
		copyBase(copy);
		return copy;
	}
}
