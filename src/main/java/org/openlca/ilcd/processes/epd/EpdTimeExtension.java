package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.util.Val;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdTimeExtension implements Copyable<EpdTimeExtension>, Extension {

	@XmlElement(name = "publicationDateOfEPD", namespace = Vocab.EPD_2019)
	private XMLGregorianCalendar publicationDate;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public XMLGregorianCalendar getPublicationDate() {
		return publicationDate;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdTimeExtension withPublicationDate(XMLGregorianCalendar publicationDate) {
		this.publicationDate = publicationDate;
		return this;
	}

	public EpdTimeExtension withAny(List<Object> any) {
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
	public EpdTimeExtension copy() {
		var copy = new EpdTimeExtension();
		copy.withPublicationDate(publicationDate);
		Val.copyAny(any, copy::withAny);
		return copy;
	}
}
