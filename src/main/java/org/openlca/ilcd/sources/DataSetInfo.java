package org.openlca.ilcd.sources;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType", propOrder = {
	"uuid",
	"name",
	"classifications",
	"citation",
	"type",
	"description",
	"files",
	"contacts",
	"logo",
	"other"})
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private String uuid;

	@Label
	@XmlElement(name = "shortName", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> name;

	@XmlElementWrapper(name = "classificationInformation")
	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Classification> classifications;

	@XmlElement(name = "sourceCitation")
	private String citation;

	@XmlElement(name = "publicationType")
	private SourceType type;

	@FreeText
	@XmlElement(name = "sourceDescriptionOrComment")
	private List<LangString> description;

	@XmlElement(name = "referenceToDigitalFile")
	private List<FileRef> files;

	@XmlElement(name = "referenceToContact")
	private List<Ref> contacts;

	@XmlElement(name = "referenceToLogo")
	private Ref logo;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getUUID() {
		return uuid;
	}

	public List<LangString> getName() {
		return name != null ? name : List.of();
	}

	public List<Classification> getClassifications() {
		return classifications != null ? classifications : List.of();
	}

	public String getCitation() {
		return citation;
	}

	public SourceType getType() {
		return type;
	}

	public List<LangString> getDescription() {
		return description != null ? description : List.of();
	}

	public List<FileRef> getFiles() {
		return files != null ? files : List.of();
	}

	public List<Ref> getContacts() {
		return contacts != null ? contacts : List.of();
	}

	public Ref getLogo() {
		return logo;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public DataSetInfo withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public DataSetInfo withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public DataSetInfo withClassifications(List<Classification> classifications) {
		this.classifications = classifications;
		return this;
	}

	public DataSetInfo withCitation(String citation) {
		this.citation = citation;
		return this;
	}

	public DataSetInfo withType(SourceType type) {
		this.type = type;
		return this;
	}

	public DataSetInfo withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public DataSetInfo withFiles(List<FileRef> files) {
		this.files = files;
		return this;
	}

	public DataSetInfo withContacts(List<Ref> contacts) {
		this.contacts = contacts;
		return this;
	}

	public DataSetInfo withLogo(Ref logo) {
		this.logo = logo;
		return this;
	}

	public DataSetInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public DataSetInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	public List<Classification> withClassifications() {
		if (classifications == null) {
			classifications = new ArrayList<>();
		}
		return classifications;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	public List<FileRef> withFiles() {
		if (files == null) {
			files = new ArrayList<>();
		}
		return files;
	}

	public List<Ref> withContacts() {
		if (contacts == null) {
			contacts = new ArrayList<>();
		}
		return contacts;
	}

	public Ref withLogo() {
		if (logo == null) {
			logo = new Ref();
		}
		return logo;
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
	public DataSetInfo copy() {
		var copy = new DataSetInfo();
		copy.withUUID(uuid);
		Val.copy(name, copy::withName);
		Val.copy(classifications, copy::withClassifications);
		copy.withCitation(citation);
		copy.withType(type);
		Val.copy(description, copy::withDescription);
		Val.copy(files, copy::withFiles);
		Val.copy(contacts, copy::withContacts);
		Val.copy(logo, copy::withLogo);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
