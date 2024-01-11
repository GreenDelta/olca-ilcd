
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataEntryByType", propOrder = {
		"timeStamp",
		"formats",
		"other"
})
public class DataEntry {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public XMLGregorianCalendar timeStamp;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToDataSetFormat")
	public final List<Ref> formats = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
