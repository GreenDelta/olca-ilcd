package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Ref;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"timeStamp",
		"formats",
})
public class DataEntry implements Copyable<DataEntry> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private XMLGregorianCalendar timeStamp;

	/** Describes the format of the data set. */
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToDataSetFormat")
	private List<Ref> formats;

	// region getters

	public XMLGregorianCalendar getTimeStamp() {
		return timeStamp;
	}

	public List<Ref> getFormats() {
		return formats != null ? formats : Collections.emptyList();
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

	public List<Ref> withFormats() {
		if (formats == null) {
			formats = new ArrayList<>();
		}
		return formats;
	}

	// endregion

	@Override
	public DataEntry copy() {
		var copy = new DataEntry();
		copy.withTimeStamp(timeStamp);
		Val.copy(formats, copy::withFormats);
		return copy;
	}


}
