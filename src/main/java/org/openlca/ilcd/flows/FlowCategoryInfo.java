package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlowCategoryInformationType", propOrder = {
	"compartmentLists", "classifications"})
public class FlowCategoryInfo implements Copyable<FlowCategoryInfo> {

	@XmlElement(name = "elementaryFlowCategorization", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<CompartmentList> compartmentLists;

	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Classification> classifications;

	// region getters

	public List<CompartmentList> getCompartmentLists() {
		return compartmentLists != null ? compartmentLists : List.of();
	}

	public List<Classification> getClassifications() {
		return classifications != null ? classifications : List.of();
	}

	// endregion

	// region setters

	public FlowCategoryInfo withCompartmentLists(List<CompartmentList> compartmentLists) {
		this.compartmentLists = compartmentLists;
		return this;
	}

	public FlowCategoryInfo withClassifications(List<Classification> classifications) {
		this.classifications = classifications;
		return this;
	}

	public List<CompartmentList> withCompartmentLists() {
		if (compartmentLists == null) {
			compartmentLists = new ArrayList<>();
		}
		return compartmentLists;
	}

	public List<Classification> withClassifications() {
		if (classifications == null) {
			classifications = new ArrayList<>();
		}
		return classifications;
	}

	// endregion

	@Override
	public FlowCategoryInfo copy() {
		var copy = new FlowCategoryInfo();
		Val.copy(compartmentLists, this::withCompartmentLists);
		Val.copy(classifications, this::withClassifications);
		return copy;
	}

}
