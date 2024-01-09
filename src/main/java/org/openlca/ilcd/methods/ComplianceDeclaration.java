package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.ilcd.commons.Ref;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceType", propOrder = {
		"complianceSystem",
		"approvalOfOverallCompliance",
		"nomenclatureCompliance",
		"methodologicalCompliance",
		"reviewCompliance",
		"documentationCompliance",
		"qualityCompliance"
})
public class ComplianceDeclaration {

	@XmlElement(name = "referenceToComplianceSystem", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	public Ref complianceSystem;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance approvalOfOverallCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance nomenclatureCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance methodologicalCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance reviewCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance documentationCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance qualityCompliance;

}
