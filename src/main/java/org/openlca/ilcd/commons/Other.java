package org.openlca.ilcd.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.util.Val;
import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "any" })
@XmlRootElement(name = "other")
public class Other implements Copyable<Other>, Extension {

	/**
	 * Objects of the following type(s) are allowed in the list {@link Element }
	 * {@link Object }
	 */
	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	@Override
	public List<Object> getAny() {
		return any != null ? any : Collections.emptyList();
	}

	// endregion

	// region setters

	@Override
	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	@Override
	public Other withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	// endregion

	@Override
	public Other copy() {
		var copy = new Other();
		Val.copyAny(any, copy::withAny);
		return copy;
	}

}
