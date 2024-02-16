package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataEntryByType", propOrder = {
		"timeStamp",
		"formats",
		"originalDataSet",
		"documentor",
		"recommendation"
})
public class DataEntry implements Copyable<DataEntry> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private XMLGregorianCalendar timeStamp;

	@XmlElement(name = "referenceToDataSetFormat", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Ref> formats;

	@XmlElement(name = "referenceToConvertedOriginalDataSetFrom", namespace = "http://lca.jrc.it/ILCD/Common")
	private Ref originalDataSet;

	@XmlElement(name = "referenceToPersonOrEntityEnteringTheData", namespace = "http://lca.jrc.it/ILCD/Common")
	private Ref documentor;

	@XmlElement(name = "recommendationBy")
	private Recommendation recommendation;

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

	public Recommendation getRecommendation() {
		return recommendation;
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

	public DataEntry withRecommendation(Recommendation recommendation) {
		this.recommendation = recommendation;
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

	public Recommendation withRecommendation() {
		if (recommendation == null) {
			recommendation = new Recommendation();
		}
		return recommendation;
	}

	// endregion

	@Override
	public DataEntry copy() {
		var copy = new DataEntry();
		copy.withTimeStamp(timeStamp);
		Val.copy(formats, copy::withFormats);
		Val.copy(originalDataSet, copy::withOriginalDataSet);
		Val.copy(documentor, copy::withDocumentor);
		Val.copy(recommendation, copy::withRecommendation);
		return copy;
	}

}
