package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.UncertaintyDistribution;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VariableParameterType", propOrder = {
	"formula",
	"mean",
	"min",
	"max",
	"distribution",
	"dispersion",
	"comment",
	"other"
})
public class Parameter implements Copyable<Parameter> {

	/**
	 * Mathematical expression that determines the value of a variable. [Note: A
	 * parameter is defined by entering the value manually into the field "Mean
	 * value" and this field can be left empty.]
	 */
	@XmlElement(name = "formula")
	private String formula;

	/**
	 * Parameter value entered by user OR in case a formula is given in the
	 * "Formula" field, the result of the formula for the variable is displayed
	 * here.
	 */
	@XmlElement(name = "meanValue")
	private Double mean;

	/**
	 * Minimum value permissible for this parameter. For variables this field is
	 * empty.
	 */
	@XmlElement(name = "minimumValue")
	private Double min;

	/**
	 * Maximum value permissible for this parameter. For variables this field is
	 * empty.
	 */
	@XmlElement(name = "maximumValue")
	private Double max;

	/**
	 * Defines the kind of uncertainty distribution that is valid for this
	 * particular object or parameter.
	 */
	@XmlElement(name = "uncertaintyDistributionType")
	private UncertaintyDistribution distribution;

	/**
	 * The resulting overall uncertainty of the calculated variable value
	 * considering uncertainty of measurements, modelling, appropriateness etc.
	 * [Notes: For log-normal distribution the square of the geometric standard
	 * deviation (SDg^2) is stated. Mean value times SDg^2 equals the 97.5%
	 * value (= Maximum value), Mean value divided by SDg^2 equals the 2.5%
	 * value (= Minimum value). For normal distribution the doubled standard
	 * deviation value (2*SD) is entered. Mean value plus 2*SD equals 97.5%
	 * value (= Maximum value), Mean value minus 2*SD equals 2.5% value (=
	 * Minimum value). This data field remains empty when uniform or triangular
	 * uncertainty distribution is applied.]
	 */
	@XmlElement(name = "relativeStandardDeviation95In")
	private Double dispersion;

	/**
	 * Comment or description of variable or parameter. Typically including its
	 * unit and default values, e.g. in the pattern &lt;[unit] description;
	 * defaults; comments&gt;.
	 */
	@Label
	@XmlElement(name = "comment")
	private List<LangString> comment;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	/**
	 * Name of variable or parameter used as scaling factors for the "Mean
	 * amount" of individual inputs or outputs of the data set.
	 */
	@XmlAttribute(name = "name", required = true)
	private String name;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getFormula() {
		return formula;
	}

	public Double getMean() {
		return mean;
	}

	public Double getMin() {
		return min;
	}

	public Double getMax() {
		return max;
	}

	public UncertaintyDistribution getDistribution() {
		return distribution;
	}

	public Double getDispersion() {
		return dispersion;
	}

	public List<LangString> getComment() {
		return comment != null ? comment : List.of();
	}

	public Other getOther() {
		return other;
	}

	public String getName() {
		return name;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Parameter withFormula(String formula) {
		this.formula = formula;
		return this;
	}

	public Parameter withMean(Double mean) {
		this.mean = mean;
		return this;
	}

	public Parameter withMin(Double min) {
		this.min = min;
		return this;
	}

	public Parameter withMax(Double max) {
		this.max = max;
		return this;
	}

	public Parameter withDistribution(UncertaintyDistribution distribution) {
		this.distribution = distribution;
		return this;
	}

	public Parameter withDispersion(Double dispersion) {
		this.dispersion = dispersion;
		return this;
	}

	public Parameter withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public Parameter withOther(Other other) {
		this.other = other;
		return this;
	}

	public Parameter withName(String name) {
		this.name = name;
		return this;
	}

	public Parameter withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
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
	public Parameter copy() {
		var copy = new Parameter();
		copy.withFormula(formula);
		copy.withMean(mean);
		copy.withMin(min);
		copy.withMax(max);
		copy.withDistribution(distribution);
		copy.withDispersion(dispersion);
		Val.copy(comment, copy::withComment);
		Val.copy(other, copy::withOther);
		copy.withName(name);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
