package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletenessType", propOrder = {
		"impactCoverage",
		"inventoryItems"
})
public class Completeness implements Copyable<Completeness> {

	@XmlElement(name = "completenessImpactCoverage")
	private Double impactCoverage;

	private Integer inventoryItems;

	// region getters

	public Double getImpactCoverage() {
		return impactCoverage;
	}

	public Integer getInventoryItems() {
		return inventoryItems;
	}

	// endregion

	// region setters

	public Completeness withImpactCoverage(Double impactCoverage) {
		this.impactCoverage = impactCoverage;
		return this;
	}

	public Completeness withInventoryItems(Integer inventoryItems) {
		this.inventoryItems = inventoryItems;
		return this;
	}

	// endregion

	@Override
	public Completeness copy() {
		var copy = new Completeness();
		copy.withImpactCoverage(impactCoverage);
		copy.withInventoryItems(inventoryItems);
		return copy;
	}

}
