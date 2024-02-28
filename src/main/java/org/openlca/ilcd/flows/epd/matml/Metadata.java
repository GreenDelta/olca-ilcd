package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Metadata", propOrder = {"properties"})
public class Metadata implements Copyable<Metadata> {

	@XmlElement(name = "PropertyDetails", namespace = Vocab.MATML)
	private List<PropertyDetails> properties;

	// region getters

	public List<PropertyDetails> getProperties() {
		return properties != null ? properties : List.of();
	}

	// endregion

	// region setters

	public Metadata withProperties(List<PropertyDetails> properties) {
		this.properties = properties;
		return this;
	}

	public List<PropertyDetails> withProperties() {
		if (properties == null) {
			properties = new ArrayList<>();
		}
		return properties;
	}

	// endregion

	@Override
	public Metadata copy() {
		var copy = new Metadata();
		Val.copy(properties, copy::withProperties);
		return copy;
	}

}
