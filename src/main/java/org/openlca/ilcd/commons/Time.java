package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.annotations.FreeText;

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
	public Integer referenceYear;

	/**
	 * End year of the time period for which the data set is still valid /
	 * sufficiently representative. This date also determines when a data set
	 * revision / remodelling is required or recommended due to expected
	 * relevant changes in environmentally or technically relevant inventory
	 * values, including in the background system.
	 */
	@XmlElement(name = "dataSetValidUntil")
	public Integer validUntil;

	/**
	 * Description of the valid time span of the data set including information
	 * on limited usability within sub-time spans (e.g. summer/winter).
	 */
	@FreeText
	@XmlElement(name = "timeRepresentativenessDescription")
	public final List<LangString> description = new ArrayList<>();

	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Time copy() {
		var clone = new Time();
		clone.referenceYear = referenceYear;
		clone.validUntil = validUntil;
		LangString.copy(description, clone.description);
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
