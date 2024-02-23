package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.sources.SourceType;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"uuid",
		"uri",
		"version",
		"name",
		"shortName",
		"classification",
		"comment",
		"citation",
		"publicationType",
		"file",
		"belongsTo" })
public class SourceDescriptor extends Descriptor<SourceDescriptor> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String shortName;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	private List<LangString> citation;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	private SourceType publicationType;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	private List<DataSetReference> file;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	private DataSetReference belongsTo;

	@Override
	protected DataSetType getType() {
		return DataSetType.SOURCE;
	}

	// region getters

	public String getShortName() {
		return shortName;
	}

	public List<LangString> getCitation() {
		return citation != null ? citation : Collections.emptyList();
	}

	public SourceType getPublicationType() {
		return publicationType;
	}

	public List<DataSetReference> getFile() {
		return file != null ? file : Collections.emptyList();
	}

	public DataSetReference getBelongsTo() {
		return belongsTo;
	}

	// endregion

	// region setters

	public SourceDescriptor withShortName(String shortName) {
		this.shortName = shortName;
		return this;
	}

	public SourceDescriptor withCitation(List<LangString> citation) {
		this.citation = citation;
		return this;
	}

	public SourceDescriptor withPublicationType(SourceType publicationType) {
		this.publicationType = publicationType;
		return this;
	}

	public SourceDescriptor withFile(List<DataSetReference> file) {
		this.file = file;
		return this;
	}

	public SourceDescriptor withBelongsTo(DataSetReference belongsTo) {
		this.belongsTo = belongsTo;
		return this;
	}

	public List<LangString> withCitation() {
		if (citation == null) {
			citation = new ArrayList<>();
		}
		return citation;
	}

	public List<DataSetReference> withFile() {
		if (file == null) {
			file = new ArrayList<>();
		}
		return file;
	}

	public DataSetReference withBelongsTo() {
		if (belongsTo == null) {
			belongsTo = new DataSetReference();
		}
		return belongsTo;
	}

	// endregion

	@Override
	public SourceDescriptor copy() {
		var copy = new SourceDescriptor();
		copyBase(copy);
		copy.withShortName(shortName);
		Val.copy(citation, copy::withCitation);
		copy.withPublicationType(publicationType);
		Val.copy(file, copy::withFile);
		Val.copy(belongsTo, copy::withBelongsTo);
		return copy;
	}

}
