package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

import java.util.ArrayList;
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
	public final List<Object> any = new ArrayList<>();

	@Override
	public Other copy() {
		var copy = new Other();
		for (var o : any) {
			if (o instanceof Element e) {
				copy.any.add(e.cloneNode(true));
			}
		}
		return copy;
	}

}
