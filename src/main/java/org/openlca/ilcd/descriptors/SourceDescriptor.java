package org.openlca.ilcd.descriptors;

import java.util.ArrayList;
import java.util.List;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.sources.SourceType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

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
public class SourceDescriptor extends Descriptor
	implements Copyable<SourceDescriptor> {

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

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	@XmlAttribute(name = "sourceId", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String sourceId;

	@Override
	protected DataSetType getType() {
		return DataSetType.SOURCE;
	}

	// region getters

	public String getShortName() {
		return shortName;
	}

	public List<LangString> getCitation() {
		return citation != null ? citation : List.of();
	}

	public SourceType getPublicationType() {
		return publicationType;
	}

	public List<DataSetReference> getFile() {
		return file != null ? file : List.of();
	}

	public DataSetReference getBelongsTo() {
		return belongsTo;
	}

	public String getHref() {
		return href;
	}

	public String getSourceId() {
		return sourceId;
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

	public SourceDescriptor withHref(String href) {
		this.href = href;
		return this;
	}

	public SourceDescriptor withSourceId(String sourceId) {
		this.sourceId = sourceId;
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
		copy.withShortName(shortName);
		Val.copy(citation, copy::withCitation);
		copy.withPublicationType(publicationType);
		Val.copy(file, copy::withFile);
		Val.copy(belongsTo, copy::withBelongsTo);
		copy.withHref(href);
		copy.withSourceId(sourceId);
		return copy;
	}

}
