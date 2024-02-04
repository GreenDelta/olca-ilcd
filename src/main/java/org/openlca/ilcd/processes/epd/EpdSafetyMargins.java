package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdSafetyMargins implements Copyable<EpdSafetyMargins> {

	@XmlElement(name="margins", namespace = Vocab.EPD_2013)
	private Double value;

	@XmlElement(name="description", namespace = Vocab.EPD_2013)
	private List<LangString> description;

	// region getters

	public Double getValue() {
		return value;
	}

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	// endregion

	// region setters

	public EpdSafetyMargins withValue(Double margins) {
		this.value = margins;
		return this;
	}

	public EpdSafetyMargins withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	// endregion

	@Override
	public EpdSafetyMargins copy() {
		var copy = new EpdSafetyMargins();
		copy.withValue(value);
		Val.copy(description, copy::withDescription);
		return copy;
	}

}
