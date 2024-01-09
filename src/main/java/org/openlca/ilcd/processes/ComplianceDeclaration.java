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
	public Ref system;

	@XmlElement(name = "approvalOfOverallCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance approval;

	@XmlElement(name = "nomenclatureCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance nomenclature;

	@XmlElement(name = "methodologicalCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance method;

	@XmlElement(name = "reviewCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance review;

	@XmlElement(name = "documentationCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance documentation;

	@XmlElement(name = "qualityCompliance", namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance quality;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public ComplianceDeclaration copy() {
		var clone = new ComplianceDeclaration();
		if (system != null)
			clone.system = system.copy();
		clone.approval = approval;
		clone.nomenclature = nomenclature;
		clone.method = method;
		clone.review = review;
		clone.documentation = documentation;
		clone.quality = quality;
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
