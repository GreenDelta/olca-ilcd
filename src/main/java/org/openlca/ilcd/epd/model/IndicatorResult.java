package org.openlca.ilcd.epd.model;

import org.openlca.ilcd.commons.Copyable;

import java.util.ArrayList;
import java.util.List;

public class IndicatorResult implements Copyable<IndicatorResult> {

	public Indicator indicator;
	public final List<Amount> amounts = new ArrayList<>();

	@Override
	public IndicatorResult copy() {
		IndicatorResult clone = new IndicatorResult();
		clone.indicator = indicator;
		for (Amount a : amounts) {
			clone.amounts.add(a.copy());
		}
		return clone;
	}
}
