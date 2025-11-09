package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkDetails", propOrder = {"name", "properties"})
public class BulkDetails implements Copyable<BulkDetails> {

	@XmlElement(name = "Name", namespace = Vocab.MATML, required = true)
	private String name;

	@XmlElement(name = "PropertyData", namespace = Vocab.MATML)
	private List<PropertyData> properties;

	// region getters

	public String getName() {
		return name;
	}

	public List<PropertyData> getProperties() {
		return properties != null ? properties : List.of();
	}

	// endregion

	// region setters

	public BulkDetails withName(String name) {
		this.name = name;
		return this;
	}

	public BulkDetails withProperties(List<PropertyData> properties) {
		this.properties = properties;
		return this;
	}

	public List<PropertyData> withProperties() {
		if (properties == null) {
			properties = new ArrayList<>();
		}
		return properties;
	}

	// endregion

	@Override
	public BulkDetails copy() {
		var copy = new BulkDetails()
			.withName(name);
		Val.copy(properties, copy::withProperties);
		return copy;
	}

}
