package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.DataQualityIndicator;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "dataQualityIndicator" })
@XmlRootElement(name = "dataQualityIndicators")
public class DataQualityIndicators {

	@XmlElement(required = true)
	public final List<DataQualityIndicator> dataQualityIndicator = new ArrayList<>();

}
