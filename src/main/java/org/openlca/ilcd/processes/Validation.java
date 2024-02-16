package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationType", propOrder = { "reviews", "other" })
public class Validation implements Copyable<Validation> {

	@XmlElement(name = "review")
	private List<Review> reviews;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public List<Review> getReviews() {
		return reviews != null ? reviews : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Validation withReviews(List<Review> reviews) {
		this.reviews = reviews;
		return this;
	}

	public Validation withOther(Other other) {
		this.other = other;
		return this;
	}

	public Validation withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Review> withReviews() {
		if (reviews == null) {
			reviews = new ArrayList<>();
		}
		return reviews;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Validation copy() {
		var copy = new Validation();
		Val.copy(reviews, copy::withReviews);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
