package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "any" })
@XmlRootElement(name = "other")
public class Other implements Copyable<Other> {

	/**
	 * Objects of the following type(s) are allowed in the list {@link Element }
	 * {@link Object }
	 */
	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public List<Object> getAny() {
		return any != null ? any : Collections.emptyList();
	}

	// endregion

	// region setters

	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	public Other withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	// endregion

	@Override
	public Other copy() {
		var copy = new Other();
		if (any == null)
			return copy;
		var target = copy.withAny();
		for (var o : any) {
			if (o instanceof Element e) {
				target.add(e.cloneNode(true));
			}
		}
		return copy;
	}

}
