package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.FlowType;
import org.openlca.ilcd.commons.LangString;
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
	"classification",
	"comment",
	"synonyms",
	"categories",
	"flowType",
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

	@Override
	protected DataSetType getType() {
		return DataSetType.FLOW;
	}

	// region getters

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : Collections.emptyList();
	}

	public List<Category> getCategories() {
		return categories != null ? categories : Collections.emptyList();
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
		return copy;
	}
}
