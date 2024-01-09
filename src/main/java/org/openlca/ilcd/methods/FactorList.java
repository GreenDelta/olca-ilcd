package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharacterisationFactorsType", propOrder = { "factors" })
public class FactorList {

	@XmlElement(name = "factor", required = true)
	public final List<Factor> factors = new ArrayList<>();

}
