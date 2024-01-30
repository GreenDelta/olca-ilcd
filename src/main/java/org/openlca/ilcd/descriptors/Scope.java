package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.processes.ReviewScope;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "method" })
@XmlRootElement(name = "scope")
public class Scope implements Copyable<Scope> {

	private List<Method> method;

	@XmlAttribute(name = "name", required = true)
	private ReviewScope name;

	// region getters

	public List<Method> getMethod() {
		return method != null ? method : List.of();
	}

	public ReviewScope getName() {
		return name;
	}

	// endregion

	// region setters

	public Scope withMethod(List<Method> method) {
		this.method = method;
		return this;
	}

	public Scope withName(ReviewScope name) {
		this.name = name;
		return this;
	}

	public List<Method> withMethod() {
		if (method == null) {
			method = new ArrayList<>();
		}
		return method;
	}

	// endregion

	@Override
	public Scope copy() {
		var copy = new Scope();
		Val.copy(method, copy::withMethod);
		copy.withName(name);
		return copy;
	}

}
