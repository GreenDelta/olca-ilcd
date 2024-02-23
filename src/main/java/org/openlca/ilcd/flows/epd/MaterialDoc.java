package org.openlca.ilcd.flows.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class MaterialDoc implements Copyable<MaterialDoc> {

	@Override
	public MaterialDoc copy() {
		var copy = new MaterialDoc();
		return copy;
	}
}
