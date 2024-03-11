package org.openlca.ilcd.epd.conversion;

import org.openlca.ilcd.epd.model.EpdDataSet;
import org.openlca.ilcd.epd.model.EpdProfile;
import org.openlca.ilcd.epd.model.ModuleEntry;
import org.openlca.ilcd.epd.model.SubType;
import org.openlca.ilcd.epd.model.content.ContentDeclaration;
import org.openlca.ilcd.epd.model.qmeta.QMetaData;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Processes;
import org.openlca.ilcd.util.Strings;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Converts an ILCD process data set to an EPD data set.
 */
record EpdExtensionReader(Process process, EpdProfile profile) {

	static EpdDataSet read(Process process, EpdProfile profile) {
		return new EpdExtensionReader(process, profile).read();
	}

	private EpdDataSet read() {
		var epd = new EpdDataSet(process);
		readExtensions(epd);
		return epd;
	}

	private void readExtensions(EpdDataSet epd) {
		epd.profile = process.getOtherAttributes().get(Vocab.PROFILE_ATTR);
		readSubType(epd);
		readPublicationDate(epd);
		PublisherRef.read(epd);
		OriginalEPDRef.read(epd);
		epd.qMetaData = QMetaData.read(process);

		// read the extensions that are stored under `dataSetInformation`
		var info = Processes.getDataSetInfo(process);

		if (info == null || info.getEpdExtension() == null)
			return;
		var other = info.getEpdExtension();
		List<ModuleEntry> modules = ModuleConverter.readModules(other, profile);
		epd.moduleEntries.addAll(modules);
		epd.contentDeclaration = ContentDeclaration.read(other);

	}

	private void readSubType(EpdDataSet dataSet) {
		var method = Processes.getInventoryMethod(process);
		if (method == null || method.getEpdExtension() == null)
			return;
		var elem = Dom.getElement(method.getEpdExtension(), "subType");
		if (elem != null) {
			dataSet.subType = SubType.fromLabel(elem.getTextContent());
		}
	}

	private void readPublicationDate(EpdDataSet epd) {
		var time = Processes.getTime(epd.process);
		if (time == null || time.getEpdExtension() == null)
			return;
		var elem = Dom.getElement(time.getEpdExtension(), "publicationDateOfEPD");
		if (elem == null)
			return;
		var text = elem.getTextContent();
		if (Strings.nullOrEmpty(text))
			return;
		try {
			epd.publicationDate = LocalDate.parse(
				text, DateTimeFormatter.ISO_DATE);
		} catch (Exception e) {
			var log = LoggerFactory.getLogger(getClass());
			log.error("Invalid format for publication date: " + text, e);
		}
	}

}
