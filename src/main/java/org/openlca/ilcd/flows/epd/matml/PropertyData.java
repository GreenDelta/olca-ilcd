package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyData", propOrder = {"data"})
public class PropertyData implements Copyable<PropertyData> {

	@XmlElement(name = "Data", namespace = Vocab.MATML, required = true)
	private Data data;

	@XmlIDREF
	@XmlAttribute(name = "property", required = true)
	private PropertyDetails property;

	// region getters

	public Data getData() {
		return data;
	}

	public PropertyDetails getProperty() {
		return property;
	}

	// endregion

	// region setters

	public PropertyData withData(Data data) {
		this.data = data;
		return this;
	}

	public PropertyData withProperty(PropertyDetails property) {
		this.property = property;
		return this;
	}

	public Data withData() {
		if (data == null) {
			data = new Data();
		}
		return data;
	}

	public PropertyDetails withProperty() {
		if (property == null) {
			property = new PropertyDetails();
		}
		return property;
	}

	// endregion

	@Override
	public PropertyData copy() {
		var copy = new PropertyData();
		Val.copy(data, copy::withData);
		Val.copy(property, copy::withProperty);
		return copy;
	}

}
