package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class Modelling implements Copyable<Modelling> {

	@Override
	public Modelling copy() {
		return new Modelling();
	}
}
