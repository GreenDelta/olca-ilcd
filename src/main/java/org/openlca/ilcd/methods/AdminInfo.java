package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.CommissionerAndGoal;
import org.openlca.ilcd.commons.Other;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdministrativeInformationType", propOrder = {
		"commissionerAndGoal",
		"dataGenerator",
		"dataEntry",
		"publication",
		"other"
})
public class AdminInfo {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public CommissionerAndGoal commissionerAndGoal;

	@XmlElement(required = true)
	public DataGenerator dataGenerator;

	@XmlElement(name = "dataEntryBy", required = true)
	public DataEntry dataEntry;

	@XmlElement(name = "publicationAndOwnership", required = true)
	public Publication publication;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
