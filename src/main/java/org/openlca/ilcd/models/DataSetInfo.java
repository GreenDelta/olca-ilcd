package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType", propOrder = {
		"uuid",
		"modelName",
		"classifications",
		"comment",
		"externalDocs"
})
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common")
	private String uuid;

	@XmlElement(name = "name")
	private ModelName modelName;

	@XmlElementWrapper(name = "classificationInformation")
	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Classification> classifications;

	@FreeText
	@XmlElement(name = "generalComment", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> comment;

	@XmlElement(name = "referenceToExternalDocumentation")
	private List<Ref> externalDocs;

	// region getters

	public String getUUID() {
		return uuid;
	}

	public ModelName getModelName() {
		return modelName;
	}

	public List<Classification> getClassifications() {
		return classifications != null ? classifications : Collections.emptyList();
	}

	public List<LangString> getComment() {
		return comment != null ? comment : Collections.emptyList();
	}

	public List<Ref> getExternalDocs() {
		return externalDocs != null ? externalDocs : Collections.emptyList();
	}

	// endregion

	// region setters

	public DataSetInfo withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public DataSetInfo withModelName(ModelName name) {
		this.modelName = name;
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

	public DataSetInfo withExternalDocs(List<Ref> externalDocs) {
		this.externalDocs = externalDocs;
		return this;
	}

	public ModelName withModelName() {
		if (modelName == null) {
			modelName = new ModelName();
		}
		return modelName;
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

	public List<Ref> withExternalDocs() {
		if (externalDocs == null) {
			externalDocs = new ArrayList<>();
		}
		return externalDocs;
	}

	// endregion

	@Override
	public DataSetInfo copy() {
		var copy = new DataSetInfo();
		copy.withUUID(uuid);
		Val.copy(modelName, copy::withModelName);
		Val.copy(classifications, copy::withClassifications);
		Val.copy(comment, copy::withComment);
		Val.copy(externalDocs, copy::withExternalDocs);
		return copy;
	}

}
