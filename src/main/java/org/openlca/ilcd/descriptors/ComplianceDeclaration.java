
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"reference",
		"overallCompliance",
		"nomenclatureCompliance",
		"methodologicalCompliance",
		"reviewCompliance",
		"documentationCompliance",
		"qualityCompliance"
})
@XmlRootElement(name = "complianceSystem")
public class ComplianceDeclaration implements Copyable<ComplianceDeclaration> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private DataSetReference reference;

	private Compliance overallCompliance;

	private Compliance nomenclatureCompliance;

	private Compliance methodologicalCompliance;

	private Compliance reviewCompliance;

	private Compliance documentationCompliance;

	private Compliance qualityCompliance;

	@XmlAttribute(name = "name")
	private String name;

	// region getters

	public DataSetReference getReference() {
		return reference;
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

	public String getName() {
		return name;
	}

	// endregion

	// region setters

	public ComplianceDeclaration withReference(DataSetReference reference) {
		this.reference = reference;
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

	public ComplianceDeclaration withName(String name) {
		this.name = name;
		return this;
	}

	public DataSetReference withReference() {
		if (reference == null) {
			reference = new DataSetReference();
		}
		return reference;
	}

	// endregion

	@Override
	public ComplianceDeclaration copy() {
		var copy = new ComplianceDeclaration();
		Val.copy(reference, copy::withReference);
		copy.withOverallCompliance(overallCompliance);
		copy.withNomenclatureCompliance(nomenclatureCompliance);
		copy.withMethodologicalCompliance(methodologicalCompliance);
		copy.withReviewCompliance(reviewCompliance);
		copy.withDocumentationCompliance(documentationCompliance);
		copy.withQualityCompliance(qualityCompliance);
		copy.withName(name);
		return copy;
	}
}
