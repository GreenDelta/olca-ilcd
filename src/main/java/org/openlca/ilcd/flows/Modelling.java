
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModellingAndValidationType", propOrder = {
		"inventoryMethod",
		"complianceDeclarations",
		"other"
})
public class Modelling implements Copyable<Modelling> {

	@XmlElement(name = "LCIMethod")
	private InventoryMethod inventoryMethod;

	@XmlElementWrapper(name="complianceDeclarations")
	@XmlElement(name = "compliance", required = true)
	private List<ComplianceDeclaration> complianceDeclarations;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public InventoryMethod getInventoryMethod() {
		return inventoryMethod;
	}

	public List<ComplianceDeclaration> getComplianceDeclarations() {
		return complianceDeclarations != null ? complianceDeclarations : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Modelling withInventoryMethod(InventoryMethod inventoryMethod) {
		this.inventoryMethod = inventoryMethod;
		return this;
	}

	public Modelling withComplianceDeclarations(List<ComplianceDeclaration> complianceDeclarations) {
		this.complianceDeclarations = complianceDeclarations;
		return this;
	}

	public Modelling withOther(Other other) {
		this.other = other;
		return this;
	}

	public Modelling withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public InventoryMethod withInventoryMethod() {
		if (inventoryMethod == null) {
			inventoryMethod = new InventoryMethod();
		}
		return inventoryMethod;
	}

	public List<ComplianceDeclaration> withComplianceDeclarations() {
		if (complianceDeclarations == null) {
			complianceDeclarations = new ArrayList<>();
		}
		return complianceDeclarations;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Modelling copy() {
		var copy = new Modelling();
		Val.copy(inventoryMethod, copy::withInventoryMethod);
		Val.copy(complianceDeclarations, copy::withComplianceDeclarations);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
