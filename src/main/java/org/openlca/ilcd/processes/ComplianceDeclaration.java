package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceType", propOrder = {
		"system",
		"approval",
		"nomenclature",
		"method",
		"review",
		"documentation",
		"quality",
		"other"
})
public class ComplianceDeclaration implements Copyable<ComplianceDeclaration> {

	@XmlElement(name = "referenceToComplianceSystem", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private Ref system;

	@XmlElement(name = "approvalOfOverallCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance approval;

	@XmlElement(name = "nomenclatureCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance nomenclature;

	@XmlElement(name = "methodologicalCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance method;

	@XmlElement(name = "reviewCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance review;

	@XmlElement(name = "documentationCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance documentation;

	@XmlElement(name = "qualityCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance quality;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public Ref getSystem() {
		return system;
	}

	public Compliance getApproval() {
		return approval;
	}

	public Compliance getNomenclature() {
		return nomenclature;
	}

	public Compliance getMethod() {
		return method;
	}

	public Compliance getReview() {
		return review;
	}

	public Compliance getDocumentation() {
		return documentation;
	}

	public Compliance getQuality() {
		return quality;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public ComplianceDeclaration withSystem(Ref system) {
		this.system = system;
		return this;
	}

	public ComplianceDeclaration withApproval(Compliance approval) {
		this.approval = approval;
		return this;
	}

	public ComplianceDeclaration withNomenclature(Compliance nomenclature) {
		this.nomenclature = nomenclature;
		return this;
	}

	public ComplianceDeclaration withMethod(Compliance method) {
		this.method = method;
		return this;
	}

	public ComplianceDeclaration withReview(Compliance review) {
		this.review = review;
		return this;
	}

	public ComplianceDeclaration withDocumentation(Compliance documentation) {
		this.documentation = documentation;
		return this;
	}

	public ComplianceDeclaration withQuality(Compliance quality) {
		this.quality = quality;
		return this;
	}

	public ComplianceDeclaration withOther(Other other) {
		this.other = other;
		return this;
	}

	public ComplianceDeclaration withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Ref withSystem() {
		if (system == null) {
			system = new Ref();
		}
		return system;
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
	public ComplianceDeclaration copy() {
		var copy = new ComplianceDeclaration();
		Val.copy(system, copy::withSystem);
		copy.withApproval(approval);
		copy.withNomenclature(nomenclature);
		copy.withMethod(method);
		copy.withReview(review);
		copy.withDocumentation(documentation);
		copy.withQuality(quality);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
