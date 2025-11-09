package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdContentDeclaration implements Copyable<EpdContentDeclaration> {

	@XmlElements({
		@XmlElement(
			name = "component",
			type = EpdContentComponent.class,
			namespace = Vocab.EPD_2019),
		@XmlElement(
			name = "material",
			type = EpdContentMaterial.class,
			namespace = Vocab.EPD_2019),
		@XmlElement(
			name = "substance",
			type = EpdContentSubstance.class,
			namespace = Vocab.EPD_2019),
	})
	private List<EpdContentElement<?>> elements;

	// region getters

	public List<? extends EpdContentElement<?>> getElements() {
		return elements != null ? elements : List.of();
	}

	// endregion

	// region setters

	public EpdContentDeclaration withElements(List<EpdContentElement<?>> elements) {
		this.elements = elements;
		return this;
	}

	public List<EpdContentElement<?>> withElements() {
		if (elements == null) {
			elements = new ArrayList<>();
		}
		return elements;
	}

	// endregion

	@Override
	public EpdContentDeclaration copy() {
		var copy = new EpdContentDeclaration();
		if (elements != null && !elements.isEmpty()) {
			var target = copy.withElements();
			for (var e : elements) {
				target.add(e.copy());
			}
		}
		return copy;
	}
}
