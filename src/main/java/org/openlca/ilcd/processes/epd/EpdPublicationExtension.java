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
public class EpdPublicationExtension
	implements Copyable<EpdPublicationExtension>, Extension {

	@XmlElement(name = "referenceToPublisher", namespace = Vocab.EPD_2019)
	private List<Ref> publishers;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public List<Ref> getPublishers() {
		return publishers != null ? publishers : Collections.emptyList();
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdPublicationExtension withPublishers(List<Ref> publishers) {
		this.publishers = publishers;
		return this;
	}

	public EpdPublicationExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public List<Ref> withPublishers() {
		if (publishers == null) {
			publishers = new ArrayList<>();
		}
		return publishers;
	}

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdPublicationExtension copy() {
		var copy = new EpdPublicationExtension();
		Val.copy(publishers, copy::withPublishers);
		Val.copyAny(any, copy::withAny);
		return copy;
	}
}
