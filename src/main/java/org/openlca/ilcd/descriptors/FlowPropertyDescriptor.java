package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.DataSetType;
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
		"unitGroup"
})
public class FlowPropertyDescriptor extends Descriptor<FlowPropertyDescriptor> {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<LangString> synonyms;

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/FlowProperty")
	private UnitGroupReference unitGroup;

	@Override
	protected DataSetType getType() {
		return DataSetType.FLOW_PROPERTY;
	}

	// region getters

	public List<LangString> getSynonyms() {
		return synonyms != null ? synonyms : Collections.emptyList();
	}

	public UnitGroupReference getUnitGroup() {
		return unitGroup;
	}

	// endregion

	// region setters

	public FlowPropertyDescriptor withSynonyms(List<LangString> synonyms) {
		this.synonyms = synonyms;
		return this;
	}

	public FlowPropertyDescriptor withUnitGroup(UnitGroupReference unitGroup) {
		this.unitGroup = unitGroup;
		return this;
	}

	public List<LangString> withSynonyms() {
		if (synonyms == null) {
			synonyms = new ArrayList<>();
		}
		return synonyms;
	}

	public UnitGroupReference withUnitGroup() {
		if (unitGroup == null) {
			unitGroup = new UnitGroupReference();
		}
		return unitGroup;
	}

	// endregion

	@Override
	public FlowPropertyDescriptor copy() {
		var copy = new FlowPropertyDescriptor();
		copyBase(copy);
		Val.copy(synonyms, copy::withSynonyms);
		Val.copy(unitGroup, copy::withUnitGroup);
		return copy;
	}

}
