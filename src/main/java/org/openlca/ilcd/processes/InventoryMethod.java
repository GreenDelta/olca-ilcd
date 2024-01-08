package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ModellingApproach;
import org.openlca.ilcd.commons.ModellingPrinciple;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.ProcessType;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;

import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIMethodAndAllocationType", propOrder = {
	"processType",
	"principle",
	"principleDeviations",
	"approaches",
	"approachDeviations",
	"constants",
	"constantsDeviations",
	"sources",
	"other"})
public class InventoryMethod implements Serializable {

	private final static long serialVersionUID = 1L;

	@XmlElement(name = "typeOfDataSet")
	public ProcessType processType;

	@XmlElement(name = "LCIMethodPrinciple")
	public ModellingPrinciple principle;

	@FreeText
	@XmlElement(name = "deviationsFromLCIMethodPrinciple")
	public final List<LangString> principleDeviations = new ArrayList<>();

	@XmlElement(name = "LCIMethodApproaches")
	public final List<ModellingApproach> approaches = new ArrayList<>();

	@FreeText
	@XmlElement(name = "deviationsFromLCIMethodApproaches")
	public final List<LangString> approachDeviations = new ArrayList<>();

	@FreeText
	@XmlElement(name = "modellingConstants")
	public final List<LangString> constants = new ArrayList<>();

	@FreeText
	@XmlElement(name = "deviationsFromModellingConstants")
	public final List<LangString> constantsDeviations = new ArrayList<>();

	@XmlElement(name = "referenceToLCAMethodDetails")
	public final List<Ref> sources = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public InventoryMethod clone() {
		var clone = new InventoryMethod();
		clone.processType = processType;
		clone.principle = principle;
		LangString.copy(principleDeviations, clone.principleDeviations);
		clone.approaches.addAll(approaches);
		LangString.copy(approachDeviations, clone.approachDeviations);
		LangString.copy(constants, clone.constants);
		LangString.copy(constantsDeviations, clone.constantsDeviations);
		Ref.copy(sources, clone.sources);
		if (other != null) {
			clone.other = other.clone();
		}
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
