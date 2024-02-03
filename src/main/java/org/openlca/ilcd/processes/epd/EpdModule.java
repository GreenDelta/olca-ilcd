package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdModule implements Copyable<EpdModule> {

	@XmlAttribute(name = "name", namespace = Vocab.EPD_2013)
	private String name;

	@XmlAttribute(name = "productsystem-id", namespace = Vocab.EPD_2013)
	private String productSystemId;

	// region getters

	public String getName() {
		return name;
	}

	public String getProductSystemId() {
		return productSystemId;
	}

	// endregion

	// region setters

	public EpdModule withName(String name) {
		this.name = name;
		return this;
	}

	public EpdModule withProductSystemId(String productSystemId) {
		this.productSystemId = productSystemId;
		return this;
	}

	// endregion

	@Override
	public EpdModule copy() {
		var copy = new EpdModule();
		copy.withName(name);
		copy.withProductSystemId(productSystemId);
		return copy;
	}

}
