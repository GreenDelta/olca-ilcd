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
@XmlType(name = "MatML_Doc", propOrder = {"materials", "metadata"})
public class MaterialDoc implements Copyable<MaterialDoc> {

	@XmlElement(name = "Material", namespace = Vocab.MATML, required = true)
	private List<Material> materials;

	@XmlElement(name = "Metadata", namespace = Vocab.MATML)
	private Metadata metadata;

	// region getters

	public List<Material> getMaterials() {
		return materials != null ? materials : List.of();
	}

	public Metadata getMetadata() {
		return metadata;
	}

	// endregion

	// region setters

	public MaterialDoc withMaterials(List<Material> materials) {
		this.materials = materials;
		return this;
	}

	public MaterialDoc withMetadata(Metadata metadata) {
		this.metadata = metadata;
		return this;
	}

	public List<Material> withMaterials() {
		if (materials == null) {
			materials = new ArrayList<>();
		}
		return materials;
	}

	public Metadata withMetadata() {
		if (metadata == null) {
			metadata = new Metadata();
		}
		return metadata;
	}

	// endregion

	@Override
	public MaterialDoc copy() {
		var copy = new MaterialDoc();
		Val.copy(materials, copy::withMaterials);
		Val.copy(metadata, copy::withMetadata);
		return copy;
	}

}
