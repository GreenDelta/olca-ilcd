
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataQualityIndicatorType")
public class DataQualityIndicator implements Copyable<DataQualityIndicator> {

	@XmlAttribute(name = "name", required = true)
	public QualityIndicator name;

	@XmlAttribute(name = "value", required = true)
	public Quality value;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public DataQualityIndicator copy() {
		var clone = new DataQualityIndicator();
		clone.name = name;
		clone.value = value;
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
