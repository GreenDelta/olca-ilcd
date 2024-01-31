package org.openlca.ilcd.descriptors;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.LangString;

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
	"classification",
	"comment",
	"synonyms",
	"flowCategorization",
	"type",
	"casNumber",
	"sumFormula",
	"referenceFlowProperty"
})
public class FlowDescriptor extends Descriptor<FlowDescriptor>
	implements Copyable<FlowDescriptor> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<LangString> synonyms;

	@XmlElementWrapper(
		name = "flowCategorization",
		namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow")
	@XmlElement(
		name = "category",
		namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI",
		required = true)
	private List<Category> categories;

	@XmlElement(
		name="type",
		namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow")
	private FlowType flowType;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow")
	private String casNumber;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow")
	private String sumFormula;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow")
	private ReferenceFlowProperty referenceFlowProperty;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	@XmlAttribute(name = "sourceId", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String sourceId;

	@Override
	protected DataSetType getType() {
		return DataSetType.FLOW;
	}

	// region getters

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : List.of();
	}

	public List<Category> getCategories() {
		return categories != null ? categories : List.of();
	}

	public FlowType getFlowType() {
		return flowType;
	}

	public String getCasNumber() {
		return casNumber;
	}

	public String getSumFormula() {
		return sumFormula;
	}

	public ReferenceFlowProperty getReferenceFlowProperty() {
		return referenceFlowProperty;
	}

	public String getHref() {
		return href;
	}

	public String getSourceId() {
		return sourceId;
	}

	// endregion

	// region setters

	public FlowDescriptor withSynonyms(List<LangString> synonyms) {
		this.synonyms = synonyms;
		return this;
	}

	public FlowDescriptor withCategories(List<Category> categories) {
		this.categories = categories;
		return this;
	}

	public FlowDescriptor withFlowType(FlowType type) {
		this.flowType = type;
		return this;
	}

	public FlowDescriptor withCasNumber(String casNumber) {
		this.casNumber = casNumber;
		return this;
	}

	public FlowDescriptor withSumFormula(String sumFormula) {
		this.sumFormula = sumFormula;
		return this;
	}

	public FlowDescriptor withReferenceFlowProperty(ReferenceFlowProperty referenceFlowProperty) {
		this.referenceFlowProperty = referenceFlowProperty;
		return this;
	}

	public FlowDescriptor withHref(String href) {
		this.href = href;
		return this;
	}

	public FlowDescriptor withSourceId(String sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	public List<LangString> withSynonyms() {
		if (synonyms == null) {
			synonyms = new ArrayList<>();
		}
		return synonyms;
	}

	public List<Category> withCategories() {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		return categories;
	}

	public ReferenceFlowProperty withReferenceFlowProperty() {
		if (referenceFlowProperty == null) {
			referenceFlowProperty = new ReferenceFlowProperty();
		}
		return referenceFlowProperty;
	}

	// endregion

	@Override
	public FlowDescriptor copy() {
		var copy = new FlowDescriptor();
		copyBase(copy);
		Val.copy(synonyms, copy::withSynonyms);
		Val.copy(categories, copy::withCategories);
		copy.withFlowType(flowType);
		copy.withCasNumber(casNumber);
		copy.withSumFormula(sumFormula);
		Val.copy(referenceFlowProperty, copy::withReferenceFlowProperty);
		copy.withHref(href);
		copy.withSourceId(sourceId);
		return copy;
	}
}
