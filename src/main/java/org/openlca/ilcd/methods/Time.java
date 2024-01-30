package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeType", propOrder = {
	"referenceYear",
	"duration",
	"description"
})
public class Time implements Copyable<Time> {

	private Integer referenceYear;

	@Label
	private List<LangString> duration;

	@FreeText
	@XmlElement(name = "timeRepresentativenessDescription")
	private List<LangString> description;

	// region getters

	public Integer getReferenceYear() {
		return referenceYear;
	}

	public List<LangString> getDuration() {
		return duration != null ? duration : List.of();
	}

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	// endregion

	// region setters

	public Time withReferenceYear(Integer referenceYear) {
		this.referenceYear = referenceYear;
		return this;
	}

	public Time withDuration(List<LangString> duration) {
		this.duration = duration;
		return this;
	}

	public Time withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public List<LangString> withDuration() {
		if (duration == null) {
			duration = new ArrayList<>();
		}
		return duration;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	// endregion

	@Override
	public Time copy() {
		var copy = new Time();
		copy.withReferenceYear(referenceYear);
		Val.copy(duration, copy::withDuration);
		Val.copy(description, copy::withDescription);
		return copy;
	}

}
