
package org.openlca.ilcd.flowproperties;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceType", propOrder = {
	"system",
	"overallCompliance"
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

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public Ref getSystem() {
		return system;
	}

	public Compliance getOverallCompliance() {
		return overallCompliance;
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

	public ComplianceDeclaration withOverallCompliance(Compliance overallCompliance) {
		this.overallCompliance = overallCompliance;
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
		Val.copy(system, this::withSystem);
		copy.withOverallCompliance(overallCompliance);
		Val.copy(otherAttributes, this::withOtherAttributes);
		return copy;
	}

}
