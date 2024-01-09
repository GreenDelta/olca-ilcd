package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Ref;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataEntryByType", propOrder = {
		"timeStamp",
		"formats",
		"originalDataSet",
		"documentor",
		"recommendationBy"
})
public class DataEntry {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public XMLGregorianCalendar timeStamp;

	@XmlElement(name = "referenceToDataSetFormat", namespace = "http://lca.jrc.it/ILCD/Common")
	public final List<Ref> formats = new ArrayList<>();

	@XmlElement(name = "referenceToConvertedOriginalDataSetFrom", namespace = "http://lca.jrc.it/ILCD/Common")
	public Ref originalDataSet;

	@XmlElement(name = "referenceToPersonOrEntityEnteringTheData", namespace = "http://lca.jrc.it/ILCD/Common")
	public Ref documentor;

	public Recommendation recommendationBy;

}
