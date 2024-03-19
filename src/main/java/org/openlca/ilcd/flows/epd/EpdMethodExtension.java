package org.openlca.ilcd.flows.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdMethodExtension implements Copyable<EpdMethodExtension>, Extension {

	@XmlElement(name = "vendorSpecificProduct", namespace = Vocab.EPD_2013)
	private Boolean vendorSpecific;

	@XmlElement(name = "referenceToVendor", namespace = Vocab.EPD_2013)
	private Ref vendor;

	@XmlElement(name = "referenceToSource", namespace = Vocab.EPD_2013)
	private Ref documentation;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public Boolean getVendorSpecific() {
		return vendorSpecific;
	}

	public Ref getVendor() {
		return vendor;
	}

	public Ref getDocumentation() {
		return documentation;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdMethodExtension withVendorSpecific(Boolean vendorSpecific) {
		this.vendorSpecific = vendorSpecific;
		return this;
	}

	public EpdMethodExtension withVendor(Ref vendor) {
		this.vendor = vendor;
		return this;
	}

	public EpdMethodExtension withDocumentation(Ref documentation) {
		this.documentation = documentation;
		return this;
	}

	public EpdMethodExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public Ref withVendor() {
		if (vendor == null) {
			vendor = new Ref();
		}
		return vendor;
	}

	public Ref withDocumentation() {
		if (documentation == null) {
			documentation = new Ref();
		}
		return documentation;
	}

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdMethodExtension copy() {
		var copy = new EpdMethodExtension();
		copy.withVendorSpecific(vendorSpecific);
		Val.copy(vendor, copy::withVendor);
		Val.copy(documentation, copy::withDocumentation);
		return copy;
	}

}
