
package org.openlca.ilcd.flows;

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
@XmlType(name = "FlowDataSetType", propOrder = {
	"flowInfo",
	"modelling",
	"adminInfo",
	"flowProperties",
	"other"
})
public class Flow implements IDataSet, Copyable<Flow> {

	@XmlElement(required = true, name = "flowInformation")
	private FlowInfo flowInfo;

	@XmlElement(name = "modellingAndValidation")
	private Modelling modelling;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlElementWrapper(name = "flowProperties")
	@XmlElement(required = true, name = "flowProperty")
	private List<FlowPropertyRef> flowProperties;

	@XmlAttribute(name = "version", required = true)
	private String version;

	@XmlAttribute(name = "locations")
	private String locations;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public FlowInfo getFlowInfo() {
		return flowInfo;
	}

	public Modelling getModelling() {
		return modelling;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public List<FlowPropertyRef> getFlowProperties() {
		return flowProperties != null ? flowProperties : List.of();
	}

	public String getVersion() {
		return version;
	}

	public String getLocations() {
		return locations;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Flow withFlowInfo(FlowInfo flowInfo) {
		this.flowInfo = flowInfo;
		return this;
	}

	public Flow withModelling(Modelling modelling) {
		this.modelling = modelling;
		return this;
	}

	public Flow withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public Flow withFlowProperties(List<FlowPropertyRef> flowProperties) {
		this.flowProperties = flowProperties;
		return this;
	}

	public Flow withVersion(String version) {
		this.version = version;
		return this;
	}

	public Flow withLocations(String locations) {
		this.locations = locations;
		return this;
	}

	public Flow withOther(Other other) {
		this.other = other;
		return this;
	}

	public Flow withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public FlowInfo withFlowInfo() {
		if (flowInfo == null) {
			flowInfo = new FlowInfo();
		}
		return flowInfo;
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

	public List<FlowPropertyRef> withFlowProperties() {
		if (flowProperties == null) {
			flowProperties = new ArrayList<>();
		}
		return flowProperties;
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
	public Flow copy() {
		var copy = new Flow();
		Val.copy(flowInfo, copy::withFlowInfo);
		Val.copy(modelling, copy::withModelling);
		Val.copy(adminInfo, copy::withAdminInfo);
		Val.copy(flowProperties, copy::withFlowProperties);
		copy.withVersion(version);
		copy.withLocations(locations);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<Flow> toElement() {
		var qname = new QName("http://lca.jrc.it/ILCD/Flow", "flowDataSet");
		return new JAXBElement<>(qname, Flow.class, null, this);
	}
}
