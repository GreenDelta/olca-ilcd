
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.flows.epd.EpdInfoExtension;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType", propOrder = {
		"uuid",
		"flowName",
		"synonyms",
		"classificationInformation",
		"casNumber",
		"sumFormula",
		"comment",
		"epdExtension"
})
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private String uuid;

	@XmlElement(name = "name")
	private FlowName flowName;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> synonyms;

	@XmlElement(name = "classificationInformation")
	private FlowCategoryInfo classificationInformation;

	@XmlElement(name = "CASNumber")
	private String casNumber;

	private String sumFormula;

	@FreeText
	@XmlElement(name = "generalComment", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> comment;

	@XmlElement(name= "other", namespace = "http://lca.jrc.it/ILCD/Common")
	private EpdInfoExtension epdExtension;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getUUID() {
		return uuid;
	}

	public FlowName getFlowName() {
		return flowName;
	}

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : Collections.emptyList();
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

	public List<LangString> getComment() {
		return comment != null ? comment : Collections.emptyList();
	}

	public EpdInfoExtension getEpdExtension() {
		return epdExtension;
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

	public DataSetInfo withFlowName(FlowName name) {
		this.flowName = name;
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

	public DataSetInfo withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public DataSetInfo withEpdExtension(EpdInfoExtension extension) {
		this.epdExtension = extension;
		return this;
	}

	public DataSetInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public FlowName withFlowName() {
		if (flowName == null) {
			flowName = new FlowName();
		}
		return flowName;
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

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
	}

	public EpdInfoExtension withEpdExtension() {
		if (epdExtension == null) {
			epdExtension = new EpdInfoExtension();
		}
		return epdExtension;
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
		Val.copy(flowName, copy::withFlowName);
		Val.copy(synonyms, copy::withSynonyms);
		Val.copy(classificationInformation, copy::withClassificationInformation);
		copy.withCasNumber(casNumber);
		copy.withSumFormula(sumFormula);
		Val.copy(comment, copy::withComment);
		Val.copy(epdExtension, copy::withEpdExtension);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
