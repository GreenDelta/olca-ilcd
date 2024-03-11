package org.openlca.ilcd.epd.conversion;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.epd.model.EpdDataSet;
import org.openlca.ilcd.util.Processes;

import java.util.stream.Collectors;

@XmlRootElement(name = "referenceToOriginalEPD", namespace = Vocab.EPD_2019)
class OriginalEPDRef extends Ref {

	private static OriginalEPDRef wrap(Ref other) {
		var ref = new OriginalEPDRef();
		ref.withType(DataSetType.SOURCE);
		JaxbRefs.copyFields(other, ref);
		return ref;
	}

	static void write(EpdDataSet epd) {
		if (epd == null)
			return;

		// remove possible DOM elements
		if (epd.originalEPDs.isEmpty()) {
			var rep = Processes.getRepresentativeness(epd.process);
			if (rep == null)
				return;
			// currently nothing else is written to this extension
			// point; so we can just drop it
			rep.withEpdExtension(null);
			return;
		}

		var ext = epd.process
			.withModelling()
			.withRepresentativeness()
			.withEpdExtension();
		ext.withAny().clear();
		var refs = epd.originalEPDs.stream()
			.map(OriginalEPDRef::wrap)
			.collect(Collectors.toList());
		JaxbRefs.write(OriginalEPDRef.class, refs, ext);
	}

	static void read(EpdDataSet epd) {
		if (epd == null)
			return;
		var rep = Processes.getRepresentativeness(epd.process);
		if (rep == null || rep.getEpdExtension() == null)
			return;
		var refs = JaxbRefs.read(OriginalEPDRef.class, rep.getEpdExtension());
		if (refs.isEmpty())
			return;
		epd.originalEPDs.addAll(refs);
	}

}
