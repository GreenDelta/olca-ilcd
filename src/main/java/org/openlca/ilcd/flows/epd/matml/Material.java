package org.openlca.ilcd.flows.epd.matml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Material", propOrder = {"bulkDetails"})
public class Material implements Copyable<Material> {

	@XmlElement(name = "BulkDetails", namespace = Vocab.MATML, required = true)
	private BulkDetails bulkDetails;

	// region getters

	public BulkDetails getBulkDetails() {
		return bulkDetails;
	}

	// endregion

	// region setters

	public Material withBulkDetails(BulkDetails bulkDetails) {
		this.bulkDetails = bulkDetails;
		return this;
	}

	public BulkDetails withBulkDetails() {
		if (bulkDetails == null) {
			bulkDetails = new BulkDetails();
		}
		return bulkDetails;
	}

	// endregion

	@Override
	public Material copy() {
		var copy = new Material();
		Val.copy(bulkDetails, copy::withBulkDetails);
		return copy;
	}

}
