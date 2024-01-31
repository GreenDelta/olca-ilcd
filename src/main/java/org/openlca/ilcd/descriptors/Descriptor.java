package org.openlca.ilcd.descriptors;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import org.openlca.ilcd.util.Val;

/**
 * A descriptor contains only some meta-information of a data set.
 */
@XmlTransient
public abstract class Descriptor<T extends Descriptor<T>> implements Copyable<T> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String uuid;

	@XmlElement(name = "permanentUri", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	@XmlSchemaType(name = "anyURI")
	private String uri;

	@XmlElement(name = "dataSetVersion", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String version;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<LangString> name;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<Classification> classification;

	@XmlElement(name = "generalComment", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<LangString> comment;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	@XmlAttribute(name = "sourceId", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String sourceId;

	protected abstract DataSetType getType();

	public final Ref toRef() {
		Ref ref = new Ref()
			.withType(getType())
			.withUri(uri)
			.withUUID(uuid)
			.withVersion(version);
		Val.copy(name, ref::withName);
		return ref;
	}

	// region getters

	public String getUuid() {
		return uuid;
	}

	public String getUri() {
		return uri;
	}

	public String getVersion() {
		return version;
	}

	public List<LangString> getName() {
		return name != null ? name : List.of();
	}

	public List<Classification> getClassification() {
		return classification != null ? classification : List.of();
	}

	public List<LangString> getComment() {
		return comment != null ? comment : List.of();
	}

	public String getHref() {
		return href;
	}

	public String getSourceId() {
		return sourceId;
	}

	// endregion

	// region setters

	@SuppressWarnings("unchecked")
	public T withUuid(String uuid) {
		this.uuid = uuid;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T withUri(String uri) {
		this.uri = uri;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T withVersion(String version) {
		this.version = version;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T withName(List<LangString> name) {
		this.name = name;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T withHref(String href) {
		this.href = href;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T withSourceId(String sourceId) {
		this.sourceId = sourceId;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T withClassification(List<Classification> classification) {
		this.classification = classification;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T withComment(List<LangString> comment) {
		this.comment = comment;
		return (T) this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	public List<Classification> withClassification() {
		if (classification == null) {
			classification = new ArrayList<>();
		}
		return classification;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
	}

	// endregion

	protected void copyBase(T copy) {
		copy.withUuid(uuid);
		copy.withUri(uri);
		copy.withVersion(version);
		copy.withHref(href);
		copy.withSourceId(sourceId);
		Val.copy(name, copy::withName);
		Val.copy(classification, copy::withClassification);
		Val.copy(comment, copy::withComment);
	}

}
