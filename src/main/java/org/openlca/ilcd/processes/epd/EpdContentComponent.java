package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import org.openlca.ilcd.Vocab;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdContentComponent extends
	EpdContentElement<EpdContentComponent> {

	@XmlElements({
		@XmlElement(
			name = "material",
			type = EpdContentMaterial.class,
			namespace = Vocab.EPD_2019),
		@XmlElement(
			name = "substance",
			type = EpdContentSubstance.class,
			namespace = Vocab.EPD_2019),
	})
	private List<EpdInnerContentElement<?>> elements;


	// region getters

	public List<EpdInnerContentElement<?>> getElements() {
		return elements != null ? elements : List.of();
	}

	// endregion

	// region setters

	public EpdContentComponent withElements(List<EpdInnerContentElement<?>> elements) {
		this.elements = elements;
		return this;
	}

	public List<EpdInnerContentElement<?>> withElements() {
		if (elements == null) {
			elements = new ArrayList<>();
		}
		return elements;
	}

	// endregion

	@Override
	public EpdContentComponent copy() {
		var copy = new EpdContentComponent();
		copyBase(copy);
		if (elements != null && !elements.isEmpty()) {
			var target = copy.withElements();
			for (var e : elements) {
				target.add(e.copy());
			}
		}
		return copy;
	}

}
