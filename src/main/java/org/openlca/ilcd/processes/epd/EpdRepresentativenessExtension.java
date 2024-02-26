package org.openlca.ilcd.processes.epd;

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
public class EpdRepresentativenessExtension
	implements Copyable<EpdRepresentativenessExtension>, Extension {

	@XmlElement(name = "referenceToOriginalEPD", namespace = Vocab.EPD_2019)
	private Ref originalEpd;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public Ref getOriginalEpd() {
		return originalEpd;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdRepresentativenessExtension withOriginalEpd(Ref originalEpd) {
		this.originalEpd = originalEpd;
		return this;
	}

	public EpdRepresentativenessExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public Ref withOriginalEpd() {
		if (originalEpd == null) {
			originalEpd = new Ref();
		}
		return originalEpd;
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
		Val.copy(originalEpd, copy::withOriginalEpd);
		Val.copyAny(any, copy::withAny);
		return copy;
	}
}
