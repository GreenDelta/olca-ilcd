package org.openlca.ilcd.epd.conversion;

import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.epd.model.EpdDataSet;
import org.openlca.ilcd.util.Processes;

@XmlRootElement(name = "referenceToPublisher", namespace = Vocab.NS_EPDv2)
final class PublisherRef extends Ref {

	private static PublisherRef wrap(Ref other) {
		var ref = new PublisherRef();
		JaxbRefs.copyFields(other, ref);
		ref.withType(DataSetType.CONTACT);
		return ref;
	}

	/**
	 * Write the publisher references to the underlying process data set of the
	 * given EPD.
	 */
	static void write(EpdDataSet epd) {
		if (epd == null)
			return;

		if (epd.publishers.isEmpty()) {
			// remove possible DOM elements
			var pub = Processes.getPublication(epd.process);
			if (pub != null) {
				// currently nothing else is written to this extension
				// point; so we can just drop it
				pub.withOther(null);
			}
			return;
		}

		var other = new Other();
		var pubRefs = epd.publishers.stream()
			.map(PublisherRef::wrap)
			.collect(Collectors.toList());
		JaxbRefs.write(PublisherRef.class, pubRefs, other);
		epd.process
			.withAdminInfo()
			.withPublication()
			.withOther(other);
	}

	/**
	 * Write the publisher references from the underlying process data set to
	 * the given EPD.
	 */
	static void read(EpdDataSet epd) {
		if (epd == null)
			return;
		var pub = Processes.getPublication(epd.process);
		if (pub == null || pub.getOther() == null)
			return;
		var refs = JaxbRefs.read(PublisherRef.class, pub.getOther());
		if (refs.isEmpty())
			return;
		epd.publishers.addAll(refs);
	}
}
