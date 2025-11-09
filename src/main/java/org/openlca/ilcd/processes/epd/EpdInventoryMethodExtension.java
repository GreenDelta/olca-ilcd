package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdInventoryMethodExtension
	implements Copyable<EpdInventoryMethodExtension>, Extension {

	@XmlElement(name = "subType", namespace = Vocab.EPD_2013)
	private EpdSubType subType;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public EpdSubType getSubType() {
		return subType;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdInventoryMethodExtension withSubType(EpdSubType subType) {
		this.subType = subType;
		return this;
	}

	public EpdInventoryMethodExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdInventoryMethodExtension copy() {
		var copy = new EpdInventoryMethodExtension()
			.withSubType(subType);
		Val.copyAny(any, copy::withAny);
		return copy;
	}
}
