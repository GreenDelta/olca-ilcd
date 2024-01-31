package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIAResultType", propOrder = {
	"method",
	"amount",
	"uncertaintyDistribution",
	"relativeStandardDeviation95In",
	"comment",
	"other"
})
public class ImpactResult implements Copyable<ImpactResult> {

	@XmlElement(name = "referenceToLCIAMethodDataSet", required = true)
	private Ref method;

	@XmlElement(name = "meanAmount")
	private double amount;

	@XmlElement(name = "uncertaintyDistributionType")
	private UncertaintyDistribution uncertaintyDistribution;

	private Double relativeStandardDeviation95In;

	@Label
	@XmlElement(name = "generalComment")
	private List<LangString> comment;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public Ref getMethod() {
		return method;
	}

	public double getAmount() {
		return amount;
	}

	public UncertaintyDistribution getUncertaintyDistribution() {
		return uncertaintyDistribution;
	}

	public Double getRelativeStandardDeviation95In() {
		return relativeStandardDeviation95In;
	}

	public List<LangString> getComment() {
		return comment != null ? comment : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public ImpactResult withMethod(Ref method) {
		this.method = method;
		return this;
	}

	public ImpactResult withAmount(double amount) {
		this.amount = amount;
		return this;
	}

	public ImpactResult withUncertaintyDistribution(UncertaintyDistribution uncertaintyDistribution) {
		this.uncertaintyDistribution = uncertaintyDistribution;
		return this;
	}

	public ImpactResult withRelativeStandardDeviation95In(Double relativeStandardDeviation95In) {
		this.relativeStandardDeviation95In = relativeStandardDeviation95In;
		return this;
	}

	public ImpactResult withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public ImpactResult withOther(Other other) {
		this.other = other;
		return this;
	}

	public ImpactResult withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public Ref withMethod() {
		if (method == null) {
			method = new Ref();
		}
		return method;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
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
	public ImpactResult copy() {
		var copy = new ImpactResult();
		Val.copy(method, copy::withMethod);
		copy.withAmount(amount);
		copy.withUncertaintyDistribution(uncertaintyDistribution);
		copy.withRelativeStandardDeviation95In(relativeStandardDeviation95In);
		Val.copy(comment, copy::withComment);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
