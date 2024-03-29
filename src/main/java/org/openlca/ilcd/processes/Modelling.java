package org.openlca.ilcd.processes;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
	private InventoryMethod inventoryMethod;

	@XmlElement(name = "dataSourcesTreatmentAndRepresentativeness")
	private Representativeness representativeness;

	private Completeness completeness;

	private Validation validation;

	@XmlElementWrapper(name = "complianceDeclarations")
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

	public Representativeness getRepresentativeness() {
		return representativeness;
	}

	public Completeness getCompleteness() {
		return completeness;
	}

	public Validation getValidation() {
		return validation;
	}

	public List<ComplianceDeclaration> getComplianceDeclarations() {
		return complianceDeclarations != null ? complianceDeclarations : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Modelling withInventoryMethod(InventoryMethod inventoryMethod) {
		this.inventoryMethod = inventoryMethod;
		return this;
	}

	public Modelling withRepresentativeness(Representativeness representativeness) {
		this.representativeness = representativeness;
		return this;
	}

	public Modelling withCompleteness(Completeness completeness) {
		this.completeness = completeness;
		return this;
	}

	public Modelling withValidation(Validation validation) {
		this.validation = validation;
		return this;
	}

	public Modelling withComplianceDeclarations(List<ComplianceDeclaration> entries) {
		this.complianceDeclarations = entries;
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

	public Representativeness withRepresentativeness() {
		if (representativeness == null) {
			representativeness = new Representativeness();
		}
		return representativeness;
	}

	public Completeness withCompleteness() {
		if (completeness == null) {
			completeness = new Completeness();
		}
		return completeness;
	}

	public Validation withValidation() {
		if (validation == null) {
			validation = new Validation();
		}
		return validation;
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
		Val.copy(representativeness, copy::withRepresentativeness);
		Val.copy(completeness, copy::withCompleteness);
		Val.copy(validation, copy::withValidation);
		Val.copy(complianceDeclarations, copy::withComplianceDeclarations);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
