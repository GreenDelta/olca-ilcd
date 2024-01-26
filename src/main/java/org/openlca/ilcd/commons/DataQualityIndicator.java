
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataQualityIndicatorType")
public class DataQualityIndicator implements Copyable<DataQualityIndicator> {

	@XmlAttribute(name = "name", required = true)
	private QualityIndicator name;

	@XmlAttribute(name = "value", required = true)
	private Quality value;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public QualityIndicator getName() {
		return name;
	}

	public Quality getValue() {
		return value;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public DataQualityIndicator withName(QualityIndicator name) {
		this.name = name;
		return this;
	}

	public DataQualityIndicator withValue(Quality value) {
		this.value = value;
		return this;
	}

	public DataQualityIndicator withOtherAttributes(Map<QName, String> otherAttributes) {
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
	public DataQualityIndicator copy() {
		var copy = new DataQualityIndicator()
			.withName(name)
			.withValue(value);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
