
package org.openlca.ilcd.units;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceType", propOrder = {
		"referenceToComplianceSystem",
		"approvalOfOverallCompliance",
		"other"
})
public class ComplianceDeclaration {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	public Ref referenceToComplianceSystem;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Compliance approvalOfOverallCompliance;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
