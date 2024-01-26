package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeType", propOrder = {"referenceYear", "validUntil",
	"description", "other"})
public class Time implements Copyable<Time> {

	/**
	 * Start year of the time period for which the data set is valid (until year
	 * of "Data set valid until:"). For data sets that combine data from
	 * different years, the most representative year is given regarding the
	 * overall environmental impact. In that case, the reference year is derived
	 * by expert judgement.
	 */
	private Integer referenceYear;

	/**
	 * End year of the time period for which the data set is still valid /
	 * sufficiently representative. This date also determines when a data set
	 * revision / remodelling is required or recommended due to expected
	 * relevant changes in environmentally or technically relevant inventory
	 * values, including in the background system.
	 */
	@XmlElement(name = "dataSetValidUntil")
	private Integer validUntil;

	/**
	 * Description of the valid time span of the data set including information
	 * on limited usability within sub-time spans (e.g. summer/winter).
	 */
	@FreeText
	@XmlElement(name = "timeRepresentativenessDescription")
	private List<LangString> description;

	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public Integer getReferenceYear() {
		return referenceYear;
	}

	public Integer getValidUntil() {
		return validUntil;
	}

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Time withReferenceYear(Integer referenceYear) {
		this.referenceYear = referenceYear;
		return this;
	}

	public Time withValidUntil(Integer validUntil) {
		this.validUntil = validUntil;
		return this;
	}

	public Time withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public Time withOther(Other other) {
		this.other = other;
		return this;
	}

	public Time withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Time copy() {
		var copy = new Time()
			.withReferenceYear(referenceYear)
			.withValidUntil(validUntil);
		Val.copy(description, copy::withDescription);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
