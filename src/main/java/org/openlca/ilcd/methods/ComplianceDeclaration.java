package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceType", propOrder = {
		"system",
		"overallCompliance",
		"nomenclatureCompliance",
		"methodologicalCompliance",
		"reviewCompliance",
		"documentationCompliance",
		"qualityCompliance"
})
public class ComplianceDeclaration implements Copyable<ComplianceDeclaration> {

	@XmlElement(
		name = "referenceToComplianceSystem",
		namespace = "http://lca.jrc.it/ILCD/Common",
		required = true)
	private Ref system;

	@XmlElement(
		name = "approvalOfOverallCompliance",
		namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance overallCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance nomenclatureCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance methodologicalCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance reviewCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance documentationCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Compliance qualityCompliance;

	// region getters

	public Ref getSystem() {
		return system;
	}

	public Compliance getOverallCompliance() {
		return overallCompliance;
	}

	public Compliance getNomenclatureCompliance() {
		return nomenclatureCompliance;
	}

	public Compliance getMethodologicalCompliance() {
		return methodologicalCompliance;
	}

	public Compliance getReviewCompliance() {
		return reviewCompliance;
	}

	public Compliance getDocumentationCompliance() {
		return documentationCompliance;
	}

	public Compliance getQualityCompliance() {
		return qualityCompliance;
	}

	// endregion

	// region setters

	public ComplianceDeclaration withSystem(Ref system) {
		this.system = system;
		return this;
	}

	public ComplianceDeclaration withOverallCompliance(Compliance overallCompliance) {
		this.overallCompliance = overallCompliance;
		return this;
	}

	public ComplianceDeclaration withNomenclatureCompliance(Compliance nomenclatureCompliance) {
		this.nomenclatureCompliance = nomenclatureCompliance;
		return this;
	}

	public ComplianceDeclaration withMethodologicalCompliance(Compliance methodologicalCompliance) {
		this.methodologicalCompliance = methodologicalCompliance;
		return this;
	}

	public ComplianceDeclaration withReviewCompliance(Compliance reviewCompliance) {
		this.reviewCompliance = reviewCompliance;
		return this;
	}

	public ComplianceDeclaration withDocumentationCompliance(Compliance documentationCompliance) {
		this.documentationCompliance = documentationCompliance;
		return this;
	}

	public ComplianceDeclaration withQualityCompliance(Compliance qualityCompliance) {
		this.qualityCompliance = qualityCompliance;
		return this;
	}

	public Ref withSystem() {
		if (system == null) {
			system = new Ref();
		}
		return system;
	}

	// endregion

	@Override
	public ComplianceDeclaration copy() {
		var copy = new ComplianceDeclaration();
		Val.copy(system, copy::withSystem);
		copy.withOverallCompliance(overallCompliance);
		copy.withNomenclatureCompliance(nomenclatureCompliance);
		copy.withMethodologicalCompliance(methodologicalCompliance);
		copy.withReviewCompliance(reviewCompliance);
		copy.withDocumentationCompliance(documentationCompliance);
		copy.withQualityCompliance(qualityCompliance);
		return copy;
	}
}
