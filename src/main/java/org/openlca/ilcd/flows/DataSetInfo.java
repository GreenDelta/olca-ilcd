
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
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
		"synonyms",
		"classificationInformation",
		"casNumber",
		"sumFormula",
		"generalComment",
		"other"
})
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private String uuid;

	private FlowName name;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> synonyms;

	@XmlElement(name = "classificationInformation")
	private FlowCategoryInfo classificationInformation;

	@XmlElement(name = "CASNumber")
	private String casNumber;

	private String sumFormula;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> generalComment;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getUuid() {
		return uuid;
	}

	public FlowName getName() {
		return name;
	}

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : List.of();
	}

	public FlowCategoryInfo getClassificationInformation() {
		return classificationInformation;
	}

	public String getCasNumber() {
		return casNumber;
	}

	public String getSumFormula() {
		return sumFormula;
	}

	public List<LangString> getGeneralComment() {
		return generalComment != null ? generalComment : List.of();
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

	public DataSetInfo withName(FlowName name) {
		this.name = name;
		return this;
	}

	public DataSetInfo withSynonyms(List<LangString> synonyms) {
		this.synonyms = synonyms;
		return this;
	}

	public DataSetInfo withClassificationInformation(FlowCategoryInfo classificationInformation) {
		this.classificationInformation = classificationInformation;
		return this;
	}

	public DataSetInfo withCasNumber(String casNumber) {
		this.casNumber = casNumber;
		return this;
	}

	public DataSetInfo withSumFormula(String sumFormula) {
		this.sumFormula = sumFormula;
		return this;
	}

	public DataSetInfo withGeneralComment(List<LangString> generalComment) {
		this.generalComment = generalComment;
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

	public FlowName withName() {
		if (name == null) {
			name = new FlowName();
		}
		return name;
	}

	public List<LangString> withSynonyms() {
		if (synonyms == null) {
			synonyms = new ArrayList<>();
		}
		return synonyms;
	}

	public FlowCategoryInfo withClassificationInformation() {
		if (classificationInformation == null) {
			classificationInformation = new FlowCategoryInfo();
		}
		return classificationInformation;
	}

	public List<LangString> withGeneralComment() {
		if (generalComment == null) {
			generalComment = new ArrayList<>();
		}
		return generalComment;
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
		Val.copy(synonyms, copy::withSynonyms);
		Val.copy(classificationInformation, copy::withClassificationInformation);
		copy.withCasNumber(casNumber);
		copy.withSumFormula(sumFormula);
		Val.copy(generalComment, copy::withGeneralComment);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
