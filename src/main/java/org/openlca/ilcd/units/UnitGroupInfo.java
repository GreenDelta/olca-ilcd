package org.openlca.ilcd.units;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Other;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitGroupInformationType", propOrder = {
		"dataSetInfo",
		"quantitativeReference",
		"other"
})
public class UnitGroupInfo {

	@XmlElement(required = true, name = "dataSetInformation")
	public DataSetInfo dataSetInfo;

	public QuantitativeReference quantitativeReference;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
