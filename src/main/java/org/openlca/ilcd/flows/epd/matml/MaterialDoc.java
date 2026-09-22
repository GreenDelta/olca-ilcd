package org.openlca.ilcd.flows.epd.matml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatML_Doc", propOrder = {"materials", "properties"})
public class MaterialDoc implements Copyable<MaterialDoc> {

	@XmlElement(name = "Material", namespace = Vocab.MATML, required = true)
	private List<Material> materials;

	@XmlElementWrapper(name = "Metadata", namespace = Vocab.MATML)
	@XmlElement(name = "PropertyDetails", namespace = Vocab.MATML)
	private List<PropertyDetails> properties;

	// region getters

	public List<Material> getMaterials() {
		return materials != null ? materials : Collections.emptyList();
	}

	public List<PropertyDetails> getProperties() {
		return properties != null ? properties : Collections.emptyList();
	}

	// endregion

	// region setters

	public MaterialDoc withMaterials(List<Material> materials) {
		this.materials = materials;
		return this;
	}

	public MaterialDoc withProperties(List<PropertyDetails> properties) {
		this.properties = properties;
		return this;
	}

	public List<Material> withMaterials() {
		if (materials == null) {
			materials = new ArrayList<>();
		}
		return materials;
	}

	public List<PropertyDetails> withProperties() {
		if (properties == null) {
			properties = new ArrayList<>();
		}
		return properties;
	}

	// endregion

	@Override
	public MaterialDoc copy() {
		var copy = new MaterialDoc();
		Val.copy(materials, copy::withMaterials);
		Val.copy(properties, copy::withProperties);
		return copy;
	}

}
