package org.openlca.ilcd.epd.conversion;

import org.openlca.ilcd.commons.ExchangeDirection;
import org.openlca.ilcd.commons.ExchangeFunction;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.epd.model.EpdDataSet;
import org.openlca.ilcd.epd.model.EpdProfile;
import org.openlca.ilcd.epd.model.Indicator;
import org.openlca.ilcd.epd.model.IndicatorResult;
import org.openlca.ilcd.processes.Exchange;
import org.openlca.ilcd.processes.ImpactResult;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.epd.EpdResultExtension;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Results {

	static List<IndicatorResult> readResults(
		Process process, EpdProfile profile) {
		if (process == null || profile == null)
			return Collections.emptyList();

		List<IndicatorResult> results = new ArrayList<>();

		// LCI indicator results
		for (var e : process.getExchanges()) {
			if (e.getExchangeFunction() != ExchangeFunction.GENERAL_REMINDER_FLOW)
				continue;
			var indicator = profile.indicatorOf(e);
			if (indicator == null)
				continue;
			var result = new IndicatorResult();
			result.indicator = indicator;
			result.amounts.addAll(Amounts.readFrom(e.getEpdExtension(), profile));
			results.add(result);
		}

		// LCIA indicator results
		for (var impact : process.getImpactResults()) {
			var indicator = profile.indicatorOf(impact);
			if (indicator == null)
				continue;
			var result = new IndicatorResult();
			result.indicator = indicator;
			result.amounts.addAll(Amounts.readFrom(impact.getEpdExtension(), profile));
			results.add(result);
		}

		return results;
	}

	static void writeResults(EpdDataSet epd) {
		if (epd == null || epd.process == null)
			return;
		var doc = Dom.createDocument();
		for (var result : epd.results) {
			var indicator = result.indicator;
			if (indicator == null)
				continue;
			var other = indicator.type == Indicator.Type.LCI
				? initFlow(epd.process, indicator)
				: initImpact(epd.process, indicator);
			Amounts.writeAmounts(result.amounts, other, doc);
			addUnitRef(other, indicator, doc);
		}
	}

	private static EpdResultExtension initFlow(Process p, Indicator indicator) {
		int nextId = 1;
		for (var e : p.getExchanges()) {
			if (e.getId() >= nextId) {
				nextId = e.getId() + 1;
			}
		}
		var e = new Exchange()
			.withId(nextId)
			.withFlow(refOf(indicator))
			.withExchangeFunction(ExchangeFunction.GENERAL_REMINDER_FLOW)
			.withDirection(
				indicator.isInput != null && indicator.isInput
					? ExchangeDirection.INPUT
					: ExchangeDirection.OUTPUT);
		p.withExchanges().add(e);
		return e.withEpdExtension();
	}

	private static Extension initImpact(Process process, Indicator indicator) {
		var r = new ImpactResult()
			.withMethod(refOf(indicator));
		process.withImpactResults().add(r);
		return r.withEpdExtension();
	}

	private static Ref refOf(Indicator indicator) {
		if (indicator == null)
			return null;
		return indicator.getRef("en");
	}

	private static void addUnitRef(
		Extension other, Indicator indicator, Document doc) {
		if (other == null || indicator == null)
			return;
		Element root = doc.createElementNS(Vocab.NS_EPD,
			"epd:referenceToUnitGroupDataSet");
		root.setAttribute("type", "unit group data set");
		root.setAttribute("refObjectId", indicator.unitGroupUUID);
		String uri = "../unitgroups/" + indicator.unitGroupUUID;
		root.setAttribute("uri", uri);
		Element description = doc.createElementNS(
			"http://lca.jrc.it/ILCD/Common", "common:shortDescription");
		description.setTextContent(indicator.unit);
		root.appendChild(description);
		other.withAny().add(root);
	}
}
