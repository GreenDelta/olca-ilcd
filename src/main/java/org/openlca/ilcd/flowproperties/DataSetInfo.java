package org.openlca.ilcd.flowproperties;

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
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType", propOrder = { "uuid", "name",
		"synonyms", "classifications", "comment", "other" })
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private String uuid;

	@Label
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> name;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> synonyms;

	@XmlElementWrapper(name = "classificationInformation")
	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Classification> classifications;

	@FreeText
	@XmlElement(name = "generalComment", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> comment;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getUUID() {
		return uuid;
	}

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
	}

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : Collections.emptyList();
	}

	public List<Classification> getClassifications() {
		return classifications != null ? classifications : Collections.emptyList();
	}

	public List<LangString> getComment() {
		return comment != null ? comment : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
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

	public DataSetInfo withSynonyms(List<LangString> synonyms) {
		this.synonyms = synonyms;
		return this;
	}

	public DataSetInfo withClassifications(List<Classification> classifications) {
		this.classifications = classifications;
		return this;
	}

	public DataSetInfo withComment(List<LangString> comment) {
		this.comment = comment;
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

	public List<LangString> withSynonyms() {
		if (synonyms == null) {
			synonyms = new ArrayList<>();
		}
		return synonyms;
	}

	public List<Classification> withClassifications() {
		if (classifications == null) {
			classifications = new ArrayList<>();
		}
		return classifications;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
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
		Val.copy(synonyms, copy::withSynonyms);
		Val.copy(classifications, copy::withClassifications);
		Val.copy(comment, copy::withComment);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
