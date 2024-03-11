package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.ExchangeDirection;
import org.openlca.ilcd.commons.ExchangeFunction;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.processes.Exchange;
import org.openlca.ilcd.processes.ImpactResult;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.epd.EpdResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is not an XML entity but a result view of an indicator in an EPD
 * dataset. This class also provides some utility functions for reading
 * and writing results from and to EPD extensions.
 */
public record EpdIndicatorResult(
	Ref indicator,
	Ref unitGroup,
	List<EpdResult> values,
	boolean isInput) implements Copyable<EpdIndicatorResult> {

	public static EpdIndicatorResult of(Ref indicator, Ref unitGroup) {
		return new EpdIndicatorResult(
			indicator, unitGroup, new ArrayList<>(), false);
	}

	public static EpdIndicatorResult ofInputIndicator(
		Ref indicator, Ref unitGroup) {
		return new EpdIndicatorResult(
			indicator, unitGroup, new ArrayList<>(), true);
	}

	public static EpdIndicatorResult ofOutputIndicator(
		Ref indicator, Ref unitGroup) {
		return of(indicator, unitGroup);
	}

	public static Optional<EpdIndicatorResult> of(Exchange e) {
		if (e == null || e.getFlow() == null)
			return Optional.empty();
		var ext = e.getEpdExtension();
		var unitGroup = ext != null
			? ext.getUnitGroup()
			: null;
		var r = e.getDirection() == ExchangeDirection.INPUT
			? ofInputIndicator(e.getFlow(), unitGroup)
			: ofOutputIndicator(e.getFlow(), unitGroup);
		if (ext != null) {
			r.values.addAll(ext.getResults());
		}
		return Optional.of(r);
	}

	public static Optional<EpdIndicatorResult> of(ImpactResult i) {
		if (i == null || i.getMethod() == null)
			return Optional.empty();
		var ext = i.getEpdExtension();
		var unitGroup = ext != null
			? ext.getUnitGroup()
			: null;
		var r = EpdIndicatorResult.of(i.getMethod(), unitGroup);
		if (ext != null) {
			r.values.addAll(ext.getResults());
		}
		return Optional.of(r);
	}

	/**
	 * Collect all non-empty results from the EPD extensions of the given
	 * process dataset.
	 */
	public static List<EpdIndicatorResult> allOf(Process epd) {
		var rs = new ArrayList<EpdIndicatorResult>();
		for (var e : epd.getExchanges()) {
			of(e).ifPresent(r -> {
				if (!r.values.isEmpty()) {
					rs.add(r);
				}
			});
		}
		for (var i : epd.getImpactResults()) {
			of(i).ifPresent(r -> {
				if (!r.values.isEmpty()) {
					rs.add(r);
				}
			});
		}
		return rs;
	}

	public static void clear(Process epd) {
		if (epd == null)
			return;
		if (!epd.getExchanges().isEmpty()) {
			var qrefs = Processes.getReferenceFlows(epd);
			epd.getExchanges()
				.removeIf(e -> !qrefs.contains(e.getId()));
		}
		if (!epd.getImpactResults().isEmpty()) {
			epd.getImpactResults().clear();
		}
	}

	public static void writeClean(Process epd, List<EpdIndicatorResult> rs) {
		if (epd == null || rs == null)
			return;

		clear(epd);
		var exchanges = epd.withExchanges();
		int nextId = 1;
		for (var e : exchanges) {
			if (e.getId() >= nextId) {
				nextId = e.getId() + 1;
			}
		}
		var impacts = epd.withImpactResults();

		for (var r : rs) {
			if (r.hasInventoryIndicator()) {
				var e = r.toExchange().withId(nextId);
				exchanges.add(e);
				nextId++;
			} else {
				impacts.add(r.toImpactResult());
			}
		}
	}

	public Exchange toExchange() {
		var e = new Exchange()
			.withFlow(indicator)
			.withExchangeFunction(
				ExchangeFunction.GENERAL_REMINDER_FLOW)
			.withDirection(isInput
				? ExchangeDirection.INPUT
				: ExchangeDirection.OUTPUT);
		var vs = e.withEpdExtension()
			.withUnitGroup(unitGroup)
			.withResults();
		vs.addAll(values);
		return e;
	}

	public ImpactResult toImpactResult() {
		var i = new ImpactResult()
			.withMethod(indicator);
		var vs = i.withEpdExtension()
			.withUnitGroup(unitGroup)
			.withResults();
		vs.addAll(values);
		return i;
	}

	public boolean hasImpactIndicator() {
		return indicator != null
			&& indicator.getType() == DataSetType.IMPACT_METHOD;
	}

	public boolean hasInventoryIndicator() {
		return indicator != null
			&& indicator.getType() == DataSetType.FLOW;
	}

	@Override
	public EpdIndicatorResult copy() {
		var copyValues = new ArrayList<EpdResult>();
		Val.copy(values, () -> copyValues);
		return new EpdIndicatorResult(
			indicator != null ? indicator.copy() : null,
			unitGroup != null ? unitGroup.copy() : null,
			copyValues,
			isInput);
	}
}
