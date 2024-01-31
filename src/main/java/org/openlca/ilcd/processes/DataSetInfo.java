package org.openlca.ilcd.processes;

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
		"subIdentifier",
		"synonyms",
		"complementingProcesses",
		"classifications",
		"comment",
		"externalDocs",
		"other"
})
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private String uuid;

	/**
	 * General descriptive and specifying name of the process.
	 */
	private ProcessName name;

	/**
	 * Identifier of a sub-set of a complete process data set. This can be the
	 * life cycle stage that a data set covers (such as used in EPDs for modular
	 * LCI reporting, with the inventory split up into "resource extraction
	 * stage", "production stage", "use stage" and "end-of-life stage"). Or it
	 * can be e.g. the type of emission source from which the elementary flows
	 * of the Inputs and Outputs stems (e.g. "incineration-related",
	 * "transport-related", etc.). Together with the field "Complementing
	 * processes" this allows to split up a process data set into a number of
	 * clearly identified data sets, each carrying only a part of the inventory
	 * and that together represent the complete inventory. Care has to be taken
	 * when naming the reference flow, to avoid misinterpretation.
	 */
	@XmlElement(name = "identifierOfSubDataSet")
	private String subIdentifier;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> synonyms;

	@XmlElementWrapper(name = "complementingProcesses")
	@XmlElement(name = "referenceToComplementingProcess", required = true)
	private List<Ref> complementingProcesses;

	@XmlElementWrapper(name = "classificationInformation")
	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Classification> classifications;

	@FreeText
	@XmlElement(name = "generalComment", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> comment;

	@XmlElement(name = "referenceToExternalDocumentation")
	private List<Ref> externalDocs;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getUuid() {
		return uuid;
	}

	public ProcessName getName() {
		return name;
	}

	public String getSubIdentifier() {
		return subIdentifier;
	}

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : List.of();
	}

	public List<Ref> getComplementingProcesses() {
		return complementingProcesses != null ? complementingProcesses : List.of();
	}

	public List<Classification> getClassifications() {
		return classifications != null ? classifications : List.of();
	}

	public List<LangString> getComment() {
		return comment != null ? comment : List.of();
	}

	public List<Ref> getExternalDocs() {
		return externalDocs != null ? externalDocs : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public DataSetInfo withUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public DataSetInfo withName(ProcessName name) {
		this.name = name;
		return this;
	}

	public DataSetInfo withSubIdentifier(String subIdentifier) {
		this.subIdentifier = subIdentifier;
		return this;
	}

	public DataSetInfo withSynonyms(List<LangString> synonyms) {
		this.synonyms = synonyms;
		return this;
	}

	public DataSetInfo withComplementingProcesses(List<Ref> complementingProcesses) {
		this.complementingProcesses = complementingProcesses;
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

	public DataSetInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public DataSetInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public ProcessName withName() {
		if (name == null) {
			name = new ProcessName();
		}
		return name;
	}

	public List<LangString> withSynonyms() {
		if (synonyms == null) {
			synonyms = new ArrayList<>();
		}
		return synonyms;
	}

	public List<Ref> withComplementingProcesses() {
		if (complementingProcesses == null) {
			complementingProcesses = new ArrayList<>();
		}
		return complementingProcesses;
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
		copy.withUuid(uuid);
		Val.copy(name, copy::withName);
		copy.withSubIdentifier(subIdentifier);
		Val.copy(synonyms, copy::withSynonyms);
		Val.copy(complementingProcesses, copy::withComplementingProcesses);
		Val.copy(classifications, copy::withClassifications);
		Val.copy(comment, copy::withComment);
		Val.copy(externalDocs, copy::withExternalDocs);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;	}

}
