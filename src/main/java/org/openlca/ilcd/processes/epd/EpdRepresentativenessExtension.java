package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdRepresentativenessExtension
	implements Copyable<EpdRepresentativenessExtension>, Extension {

	@XmlElement(name = "referenceToOriginalEPD", namespace = Vocab.EPD_2019)
	private List<Ref> originalEpds;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public List<Ref> getOriginalEpds() {
		return originalEpds != null ? originalEpds : Collections.emptyList();
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdRepresentativenessExtension withOriginalEpd(List<Ref> originalEpds) {
		this.originalEpds = originalEpds;
		return this;
	}

	public EpdRepresentativenessExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public List<Ref> withOriginalEpds() {
		if (originalEpds == null) {
			originalEpds = new ArrayList<>();
		}
		return originalEpds;
	}

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdRepresentativenessExtension copy() {
		var copy = new EpdRepresentativenessExtension();
		Val.copy(originalEpds, copy::withOriginalEpds);
		Val.copyAny(any, copy::withAny);
		return copy;
	}
}
