package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;

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
		"originalDataSet",
		"documentor",
		"useApprovals",
		"other"
})
public class DataEntry implements Copyable<DataEntry> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public XMLGregorianCalendar timeStamp;

	/** Describes the format of the data set. */
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToDataSetFormat")
	public final List<Ref> formats = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToConvertedOriginalDataSetFrom")
	public Ref originalDataSet;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToPersonOrEntityEnteringTheData")
	public Ref documentor;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToDataSetUseApproval")
	public final List<Ref> useApprovals = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public DataEntry copy() {
		var clone = new DataEntry();
		clone.timeStamp = timeStamp;
		Ref.copy(formats, clone.formats);
		if (originalDataSet != null)
			clone.originalDataSet = originalDataSet.copy();
		if (documentor != null)
			clone.documentor = documentor.copy();
		Ref.copy(useApprovals, clone.useApprovals);
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}

}
