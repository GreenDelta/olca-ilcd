package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
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
	private XMLGregorianCalendar timeStamp;

	/** Describes the format of the data set. */
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToDataSetFormat")
	private List<Ref> formats ;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToConvertedOriginalDataSetFrom")
	private Ref originalDataSet;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToPersonOrEntityEnteringTheData")
	private Ref documentor;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToDataSetUseApproval")
	private List<Ref> useApprovals;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public XMLGregorianCalendar getTimeStamp() {
		return timeStamp;
	}

	public List<Ref> getFormats() {
		return formats != null ? formats : Collections.emptyList();
	}

	public Ref getOriginalDataSet() {
		return originalDataSet;
	}

	public Ref getDocumentor() {
		return documentor;
	}

	public List<Ref> getUseApprovals() {
		return useApprovals != null ? useApprovals : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public DataEntry withTimeStamp(XMLGregorianCalendar timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	public DataEntry withFormats(List<Ref> formats) {
		this.formats = formats;
		return this;
	}

	public DataEntry withOriginalDataSet(Ref originalDataSet) {
		this.originalDataSet = originalDataSet;
		return this;
	}

	public DataEntry withDocumentor(Ref documentor) {
		this.documentor = documentor;
		return this;
	}

	public DataEntry withUseApprovals(List<Ref> useApprovals) {
		this.useApprovals = useApprovals;
		return this;
	}

	public DataEntry withOther(Other other) {
		this.other = other;
		return this;
	}

	public DataEntry withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Ref> withFormats() {
		if (formats == null) {
			formats = new ArrayList<>();
		}
		return formats;
	}

	public Ref withOriginalDataSet() {
		if (originalDataSet == null) {
			originalDataSet = new Ref();
		}
		return originalDataSet;
	}

	public Ref withDocumentor() {
		if (documentor == null) {
			documentor = new Ref();
		}
		return documentor;
	}

	public List<Ref> withUseApprovals() {
		if (useApprovals == null) {
			useApprovals = new ArrayList<>();
		}
		return useApprovals;
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
	public DataEntry copy() {
		var copy = new DataEntry();
		copy.withTimeStamp(timeStamp);
		Val.copy(formats, copy::withFormats);
		Val.copy(originalDataSet, copy::withOriginalDataSet);
		Val.copy(documentor, copy::withDocumentor);
		Val.copy(useApprovals, copy::withUseApprovals);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
