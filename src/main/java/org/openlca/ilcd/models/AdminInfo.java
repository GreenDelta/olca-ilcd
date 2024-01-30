package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "dataEntry", "publication" })
public class AdminInfo implements Copyable<AdminInfo> {

	@XmlElement(name = "dataEntryBy")
	private DataEntry dataEntry;

	@XmlElement(name = "publicationAndOwnership")
	private Publication publication;
	// region getters

	public DataEntry getDataEntry() {
		return dataEntry;
	}

	public Publication getPublication() {
		return publication;
	}

	// endregion

	// region setters

	public AdminInfo withDataEntry(DataEntry dataEntry) {
		this.dataEntry = dataEntry;
		return this;
	}

	public AdminInfo withPublication(Publication publication) {
		this.publication = publication;
		return this;
	}

	public DataEntry withDataEntry() {
		if (dataEntry == null) {
			dataEntry = new DataEntry();
		}
		return dataEntry;
	}

	public Publication withPublication() {
		if (publication == null) {
			publication = new Publication();
		}
		return publication;
	}

	// endregion

	@Override
	public AdminInfo copy() {
		var copy = new AdminInfo();
		Val.copy(dataEntry, copy::withDataEntry);
		Val.copy(publication, copy::withPublication);
		return copy;
	}

}
