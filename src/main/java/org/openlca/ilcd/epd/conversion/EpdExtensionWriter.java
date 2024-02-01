package org.openlca.ilcd.epd.conversion;

import javax.xml.namespace.QName;

import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.epd.model.EpdDataSet;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.util.Processes;

class EpdExtensionWriter {

	private final EpdDataSet epd;

	private EpdExtensionWriter(EpdDataSet dataSet) {
		this.epd = dataSet;
	}

	/**
	 * Writes the EPD extensions into the wrapped ILCD process of the given EPD
	 * data set.
	 */
	static void write(EpdDataSet epd) {
		new EpdExtensionWriter(epd).write();
	}

	private void write() {
		if (epd == null)
			return;
		var process = epd.process;
		clearResults(process);
		Results.writeResults(epd);
		writeExtensions();
		// set the format version
		process.withOtherAttributes().put(
			new QName(Vocab.NS_EPDv2, "epd-version", "epd2"), "1.2");
		process.withVersion("1.1");
		Cleanup.on(epd);
	}

	/**
	 * Remove all result exchanges.
	 */
	private void clearResults(Process p) {
		if (p == null)
			return;
		var refFlows = Processes.getReferenceFlows(p);
		var exchanges = p.getExchanges();
		if (!exchanges.isEmpty()) {
			exchanges.removeIf(e -> !refFlows.contains(e.getId()));
		}
		p.withImpactResults(null);
	}

	private void writeExtensions() {
		var doc = Dom.createDocument();

		// write the Q-Meta data
		var qMeta = epd.qMetaData;
		if (qMeta == null) {
			var mod = Processes.getModelling(epd.process);
			if (mod != null) {
				// remove possible old data
				mod.withOther(null);
			}
		} else {
			var other = epd.process
				.withModelling()
				.withOther();
			epd.qMetaData.write(other, doc);
		}

		// write info extensions
		var info = epd.process
			.withProcessInfo()
			.withDataSetInfo();
		var infoOther = info.getOther() != null
			? info.getOther()
			: info.withOther();

		ModuleConverter.writeModules(epd, infoOther, doc);
		ScenarioConverter.writeScenarios(epd, infoOther, doc);
		SafetyMarginsConverter.write(epd, infoOther, doc);
		if (epd.contentDeclaration != null) {
			epd.contentDeclaration.write(infoOther, doc);
		}
		if (Dom.isEmpty(infoOther)) {
			info.withOther(null);
		}

		writeProfile();
		writeSubType();
		writePublicationDate();
		PublisherRef.write(epd);
		OriginalEPDRef.write(epd);
	}

	private void writeSubType() {
		if (epd.subType == null) {
			var method = Processes.getInventoryMethod(epd.process);
			if (method == null)
				return;
			method.withOther(null);
			return;
		}

		var elem = Dom.createElement(Vocab.NS_EPD, "subType");
		if (elem != null) {
			epd.process.withModelling()
				.withInventoryMethod()
				.withOther()
				.withAny()
				.add(elem);
		}
	}

	private void writeProfile() {
		if (epd.profile != null) {
			epd.process.withOtherAttributes()
				.put(Vocab.PROFILE_ATTR, epd.profile);
		} else {
			var atts = epd.process.getOtherAttributes();
			if (!atts.isEmpty()) {
				atts.remove(Vocab.PROFILE_ATTR);
			}
		}
	}

	private void writePublicationDate() {



		var t = Processes.getTime(epd.process);
		var pubDate = epd.publicationDate;
		if (pubDate == null && t == null)
			return;
		var time = t == null
			? epd.process.withProcessInfo().withTime()
			: t;
		if (pubDate == null && time.other == null)
			return;

		var tag = "publicationDateOfEPD";

		// delete it if publication date is null
		if (pubDate == null) {
			Dom.clear(time.other, tag);
			if (Dom.isEmpty(time.other)) {
				time.other = null;
			}
			return;
		}

		// create or update the element
		var elem = Dom.getElement(time.other, tag);
		if (elem != null) {
			elem.setTextContent(pubDate.toString());
			return;
		}
		var newElem = Dom.createElement(Vocab.NS_EPDv2, tag);
		if (newElem == null)
			return;
		newElem.setTextContent(pubDate.toString());
		if (time.other == null) {
			time.other = new Other();
		}
		time.other.any.add(newElem);
	}
}
