package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.CommissionerAndGoal;
import org.openlca.ilcd.commons.Copyable;
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
public class AdminInfo implements Copyable<AdminInfo> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public CommissionerAndGoal commissionerAndGoal;

	public DataGenerator dataGenerator;

	@XmlElement(name = "dataEntryBy")
	public DataEntry dataEntry;

	@XmlElement(name = "publicationAndOwnership")
	public Publication publication;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public AdminInfo copy() {
		var clone = new AdminInfo();
		if (commissionerAndGoal != null)
			clone.commissionerAndGoal = commissionerAndGoal.copy();
		if (dataGenerator != null)
			clone.dataGenerator = dataGenerator.copy();
		if (dataEntry != null)
			clone.dataEntry = dataEntry.copy();
		if (publication != null)
			clone.publication = publication.copy();
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
