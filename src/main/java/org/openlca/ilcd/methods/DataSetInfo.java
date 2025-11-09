package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Classification;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType", propOrder = {
		"uuid",
		"name",
		"methods",
		"classifications",
		"impactCategories",
		"areasOfProtection",
		"indicator",
		"comment",
		"externalDocs",
		"other" })
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private String uuid;

	@Label
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> name;

	@XmlElement(name = "methodology")
	private List<String> methods;

	@XmlElementWrapper(name = "classificationInformation")
	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Classification> classifications;

	@XmlElement(name = "impactCategory")
	private List<String> impactCategories;

	@XmlElement(name = "areaOfProtection")
	private List<AreaOfProtection> areasOfProtection;

	@XmlElement(name = "impactIndicator")
	private String indicator;

	@FreeText
	@XmlElement(name = "generalComment", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> comment;

	@XmlElement(name = "referenceToExternalDocumentation")
	private List<Ref> externalDocs;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getUUID() {
		return uuid;
	}

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
	}

	public List<String> getMethods() {
		return methods != null ? methods : Collections.emptyList();
	}

	public List<Classification> getClassifications() {
		return classifications != null ? classifications : Collections.emptyList();
	}

	public List<String> getImpactCategories() {
		return impactCategories != null ? impactCategories : Collections.emptyList();
	}

	public List<AreaOfProtection> getAreasOfProtection() {
		return areasOfProtection != null ? areasOfProtection : Collections.emptyList();
	}

	public String getIndicator() {
		return indicator;
	}

	public List<LangString> getComment() {
		return comment != null ? comment : Collections.emptyList();
	}

	public List<Ref> getExternalDocs() {
		return externalDocs != null ? externalDocs : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public DataSetInfo withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public DataSetInfo withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public DataSetInfo withMethods(List<String> methods) {
		this.methods = methods;
		return this;
	}

	public DataSetInfo withClassifications(List<Classification> classifications) {
		this.classifications = classifications;
		return this;
	}

	public DataSetInfo withImpactCategories(List<String> impactCategories) {
		this.impactCategories = impactCategories;
		return this;
	}

	public DataSetInfo withAreasOfProtection(List<AreaOfProtection> areasOfProtection) {
		this.areasOfProtection = areasOfProtection;
		return this;
	}

	public DataSetInfo withIndicator(String indicator) {
		this.indicator = indicator;
		return this;
	}

	public DataSetInfo withComment(List<LangString> comment) {
		this.comment = comment;
		return this;
	}

	public DataSetInfo withExternalDocs(List<Ref> externalDocs) {
		this.externalDocs = externalDocs;
		return this;
	}

	public DataSetInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public DataSetInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	public List<String> withMethods() {
		if (methods == null) {
			methods = new ArrayList<>();
		}
		return methods;
	}

	public List<Classification> withClassifications() {
		if (classifications == null) {
			classifications = new ArrayList<>();
		}
		return classifications;
	}

	public List<String> withImpactCategories() {
		if (impactCategories == null) {
			impactCategories = new ArrayList<>();
		}
		return impactCategories;
	}

	public List<AreaOfProtection> withAreasOfProtection() {
		if (areasOfProtection == null) {
			areasOfProtection = new ArrayList<>();
		}
		return areasOfProtection;
	}

	public List<LangString> withComment() {
		if (comment == null) {
			comment = new ArrayList<>();
		}
		return comment;
	}

	public List<Ref> withExternalDocs() {
		if (externalDocs == null) {
			externalDocs = new ArrayList<>();
		}
		return externalDocs;
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
	public DataSetInfo copy() {
		var copy = new DataSetInfo();
		copy.withUUID(uuid);
		Val.copy(name, copy::withName);
		if (methods != null) {
			copy.withMethods().addAll(methods);
		}
		Val.copy(classifications, copy::withClassifications);
		if (impactCategories != null) {
			copy.withImpactCategories().addAll(impactCategories);
		}
		if (areasOfProtection != null) {
			copy.withAreasOfProtection().addAll(areasOfProtection);
		}
		copy.withIndicator(indicator);
		Val.copy(comment, copy::withComment);
		Val.copy(externalDocs, copy::withExternalDocs);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
