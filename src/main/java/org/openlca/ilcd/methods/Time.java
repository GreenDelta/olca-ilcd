package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeType", propOrder = {
		"referenceYear",
		"duration",
		"description"
})
public class Time {

	public Integer referenceYear;

	@Label
	public final List<LangString> duration = new ArrayList<>();

	@FreeText
	@XmlElement(name = "timeRepresentativenessDescription")
	public final List<LangString> description = new ArrayList<>();

}
