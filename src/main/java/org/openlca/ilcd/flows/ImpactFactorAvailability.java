
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Availability;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.ImpactCategory;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletenessAvailabilityImpactFactorsType")
public class ImpactFactorAvailability implements Copyable<ImpactFactorAvailability> {

	@XmlAttribute(name = "type", required = true)
	private ImpactCategory type;

	@XmlAttribute(name = "value", required = true)
	private Availability value;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public ImpactCategory getType() {
		return type;
	}

	public Availability getValue() {
		return value;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public ImpactFactorAvailability withType(ImpactCategory type) {
		this.type = type;
		return this;
	}

	public ImpactFactorAvailability withValue(Availability value) {
		this.value = value;
		return this;
	}

	public ImpactFactorAvailability withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public ImpactFactorAvailability copy() {
		var copy = new ImpactFactorAvailability();
		copy.withType(type);
		copy.withValue(value);
		Val.copy(otherAttributes, this::withOtherAttributes);
		return copy;
	}

}
