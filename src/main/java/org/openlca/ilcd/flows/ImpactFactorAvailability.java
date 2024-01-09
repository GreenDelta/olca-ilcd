
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Availability;
import org.openlca.ilcd.commons.ImpactCategory;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletenessAvailabilityImpactFactorsType")
public class ImpactFactorAvailability {

	@XmlAttribute(name = "type", required = true)
	public ImpactCategory type;

	@XmlAttribute(name = "value", required = true)
	public Availability value;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
