package org.openlca.ilcd.methods;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LCIAMethodDataSetType", propOrder = {
	"methodInfo",
	"modelling",
	"adminInfo",
	"factors",
	"other"
})
public class ImpactMethod implements IDataSet, Copyable<ImpactMethod> {

	@XmlElement(name = "LCIAMethodInformation", required = true)
	private MethodInfo methodInfo;

	@XmlElement(name = "modellingAndValidation", required = true)
	private Modelling modelling;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlElementWrapper(name="characterisationFactors")
	@XmlElement(name = "factor", required = true)
	private List<Factor> factors;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "version", required = true)
	private String version;

	@XmlAttribute(name = "locations")
	private String locations;

	@XmlAttribute(name = "LCIAMethodologies")
	private String methodologies;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public MethodInfo getMethodInfo() {
		return methodInfo;
	}

	public Modelling getModelling() {
		return modelling;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public List<Factor> getFactors() {
		return factors != null ? factors : List.of();
	}

	public Other getOther() {
		return other;
	}

	public String getVersion() {
		return version;
	}

	public String getLocations() {
		return locations;
	}

	public String getMethodologies() {
		return methodologies;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public ImpactMethod withMethodInfo(MethodInfo methodInfo) {
		this.methodInfo = methodInfo;
		return this;
	}

	public ImpactMethod withModelling(Modelling modelling) {
		this.modelling = modelling;
		return this;
	}

	public ImpactMethod withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public ImpactMethod withFactors(List<Factor> factors) {
		this.factors = factors;
		return this;
	}

	public ImpactMethod withOther(Other other) {
		this.other = other;
		return this;
	}

	public ImpactMethod withVersion(String version) {
		this.version = version;
		return this;
	}

	public ImpactMethod withLocations(String locations) {
		this.locations = locations;
		return this;
	}

	public ImpactMethod withMethodologies(String methodologies) {
		this.methodologies = methodologies;
		return this;
	}

	public ImpactMethod withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public MethodInfo withMethodInfo() {
		if (methodInfo == null) {
			methodInfo = new MethodInfo();
		}
		return methodInfo;
	}

	public Modelling withModelling() {
		if (modelling == null) {
			modelling = new Modelling();
		}
		return modelling;
	}

	public AdminInfo withAdminInfo() {
		if (adminInfo == null) {
			adminInfo = new AdminInfo();
		}
		return adminInfo;
	}

	public List<Factor> withFactors() {
		if (factors == null) {
			factors = new ArrayList<>();
		}
		return factors;
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
	public ImpactMethod copy() {
		var copy = new ImpactMethod();
		Val.copy(methodInfo, copy::withMethodInfo);
		Val.copy(modelling, copy::withModelling);
		Val.copy(adminInfo, copy::withAdminInfo);
		Val.copy(factors, copy::withFactors);
		Val.copy(other, copy::withOther);
		copy.withVersion(version);
		copy.withLocations(locations);
		copy.withMethodologies(methodologies);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<ImpactMethod> toElement() {
		var qname = new QName(
			"http://lca.jrc.it/ILCD/LCIAMethod", "LCIAMethodDataSet");
		return new JAXBElement<>(qname, ImpactMethod.class, null, this);
	}

}
