package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.CommissionerAndGoal;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdministrativeInformationType", propOrder = {
	"commissionerAndGoal",
	"dataGenerators",
	"dataEntry",
	"publication",
	"other"
})
public class AdminInfo implements Copyable<AdminInfo> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private CommissionerAndGoal commissionerAndGoal;

	@XmlElementWrapper(name = "dataGenerator")
	@XmlElement(
		name = "referenceToPersonOrEntityGeneratingTheDataSet",
		namespace = "http://lca.jrc.it/ILCD/Common",
		required = true)
	public final List<Ref> dataGenerators = new ArrayList<>();

	@XmlElement(name = "dataEntryBy", required = true)
	private DataEntry dataEntry;

	@XmlElement(name = "publicationAndOwnership", required = true)
	private Publication publication;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public CommissionerAndGoal getCommissionerAndGoal() {
		return commissionerAndGoal;
	}

	public DataEntry getDataEntry() {
		return dataEntry;
	}

	public Publication getPublication() {
		return publication;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public AdminInfo withCommissionerAndGoal(CommissionerAndGoal commissionerAndGoal) {
		this.commissionerAndGoal = commissionerAndGoal;
		return this;
	}

	public AdminInfo withDataEntry(DataEntry dataEntry) {
		this.dataEntry = dataEntry;
		return this;
	}

	public AdminInfo withPublication(Publication publication) {
		this.publication = publication;
		return this;
	}

	public AdminInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public AdminInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public CommissionerAndGoal withCommissionerAndGoal() {
		if (commissionerAndGoal == null) {
			commissionerAndGoal = new CommissionerAndGoal();
		}
		return commissionerAndGoal;
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
	public AdminInfo copy() {
		var copy = new AdminInfo();
		Val.copy(commissionerAndGoal, copy::withCommissionerAndGoal);
		Val.copy(dataEntry, copy::withDataEntry);
		Val.copy(publication, copy::withPublication);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
