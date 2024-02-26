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
public class EpdResultExtension
	implements Copyable<EpdResultExtension>, Extension {

	@XmlElement(name="amount", namespace = Vocab.EPD_2013)
	private List<EpdResult> results;

	@XmlElement(name="referenceToUnitGroupDataSet", namespace = Vocab.EPD_2013)
	private Ref unitGroup;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public List<EpdResult> getResults() {
		return results != null ? results : List.of();
	}

	public Ref getUnitGroup() {
		return unitGroup;
	}

	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdResultExtension withResults(List<EpdResult> results) {
		this.results = results;
		return this;
	}

	public EpdResultExtension withUnitGroup(Ref unitGroup) {
		this.unitGroup = unitGroup;
		return this;
	}

	public EpdResultExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	public List<EpdResult> withResults() {
		if (results == null) {
			results = new ArrayList<>();
		}
		return results;
	}

	public Ref withUnitGroup() {
		if (unitGroup == null) {
			unitGroup = new Ref();
		}
		return unitGroup;
	}

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdResultExtension copy() {
		var copy = new EpdResultExtension();
		Val.copy(results, copy::withResults);
		Val.copy(unitGroup, copy::withUnitGroup);
		Val.copyAny(any, copy::withAny);
		return copy;
	}
}
