package org.openlca.ilcd.epd;


import java.util.Objects;

import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdProfileIndicator implements Copyable<EpdProfileIndicator> {

	@XmlAttribute(name = "isInputIndicator")
	private Boolean inputIndicator;

	@XmlAttribute(name = "code")
	private String code;

	@XmlAttribute(name = "group")
	private String group;

	@XmlElement(name = "ref", namespace = Vocab.OLCA_EPD)
	private Ref ref;

	@XmlElement(name = "unitGroup", namespace = Vocab.OLCA_EPD)
	private Ref unit;

	public boolean isInputIndicator() {
		return inputIndicator != null && inputIndicator;
	}

	public boolean isInventoryIndicator() {
		return ref != null && ref.getType() == DataSetType.FLOW;
	}

	public String getGroup() {
		return group;
	}

	public String getCode() {
		return code;
	}

	public Ref getRef() {
		return ref;
	}

	public Ref getUnit() {
		return unit;
	}

	public String getUUID() {
		return ref != null ? ref.getUUID() : null;
	}

	public EpdProfileIndicator withGroup(String group) {
		this.group = group;
		return this;
	}

	public EpdProfileIndicator withCode(String code) {
		this.code = code;
		return this;
	}

	public EpdProfileIndicator withInputIndicator(Boolean inputIndicator) {
		this.inputIndicator = inputIndicator;
		return this;
	}

	public EpdProfileIndicator withRef(Ref ref) {
		this.ref = ref;
		return this;
	}

	public EpdProfileIndicator withUnit(Ref unit) {
		this.unit = unit;
		return this;
	}

	@Override
	public int hashCode() {
		var id = getUUID();
		return id != null ? id.hashCode() : super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return obj instanceof EpdProfileIndicator other
			&& Objects.equals(this.getUUID(), other.getUUID());
	}

	@Override
	public String toString() {
		var name = ref != null
			? LangString.getDefault(ref.getName())
			: null;
		return "EpdProfileIndicator [ name=\"" + name + "\" uuid =\"" + getUUID() + "\"]";
	}

	public EpdIndicatorResult createResult() {
		if (isInventoryIndicator())
			return isInputIndicator()
				? EpdIndicatorResult.ofInputIndicator(ref, unit)
				: EpdIndicatorResult.ofOutputIndicator(ref, unit);
		return EpdIndicatorResult.of(ref, unit);
	}

	@Override
	public EpdProfileIndicator copy() {
		var copy = new EpdProfileIndicator()
			.withCode(code)
			.withGroup(group)
			.withInputIndicator(inputIndicator);
		Val.copy(ref, copy::withRef);
		Val.copy(unit, copy::withUnit);
		return copy;
	}
}

