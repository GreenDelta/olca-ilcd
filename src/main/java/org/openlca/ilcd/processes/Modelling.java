package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModellingAndValidationType", propOrder = {
		"inventoryMethod",
		"representativeness",
		"completeness",
		"validation",
		"complianceDeclarations",
		"other"
})
public class Modelling implements Copyable<Modelling> {

	@XmlElement(name = "LCIMethodAndAllocation")
	public InventoryMethod inventoryMethod;

	@XmlElement(name = "dataSourcesTreatmentAndRepresentativeness")
	public Representativeness representativeness;

	public Completeness completeness;

	public Validation validation;

	@XmlElement(name = "complianceDeclarations")
	public ComplianceList complianceDeclarations;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Modelling copy() {
		var clone = new Modelling();
		if (inventoryMethod != null)
			clone.inventoryMethod = inventoryMethod.copy();
		if (representativeness != null)
			clone.representativeness = representativeness.copy();
		if (completeness != null)
			clone.completeness = completeness.copy();
		if (validation != null)
			clone.validation = validation.copy();
		if (complianceDeclarations != null) {
			clone.complianceDeclarations = complianceDeclarations.copy();
		}
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}

}
