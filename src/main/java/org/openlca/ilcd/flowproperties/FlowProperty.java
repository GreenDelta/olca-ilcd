
package org.openlca.ilcd.flowproperties;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlowPropertyDataSetType", propOrder = {
	"flowPropertyInfo",
	"modelling",
	"adminInfo",
	"other"
})
public class FlowProperty implements IDataSet, Copyable<FlowProperty> {

	@XmlElement(name = "flowPropertiesInformation", required = true)
	private FlowPropertyInfo flowPropertyInfo;

	@XmlElement(name = "modellingAndValidation")
	private Modelling modelling;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "version", required = true)
	private String schemaVersion = SCHEMA_VERSION;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public FlowPropertyInfo getFlowPropertyInfo() {
		return flowPropertyInfo;
	}

	public Modelling getModelling() {
		return modelling;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public Other getOther() {
		return other;
	}

	@Override
	public String getSchemaVersion() {
		return schemaVersion;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public FlowProperty withFlowPropertyInfo(FlowPropertyInfo flowPropertyInfo) {
		this.flowPropertyInfo = flowPropertyInfo;
		return this;
	}

	public FlowProperty withModelling(Modelling modelling) {
		this.modelling = modelling;
		return this;
	}

	public FlowProperty withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public FlowProperty withOther(Other other) {
		this.other = other;
		return this;
	}

	public FlowProperty withSchemaVersion(String version) {
		this.schemaVersion = version;
		return this;
	}

	public FlowProperty withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public FlowPropertyInfo withFlowPropertyInfo() {
		if (flowPropertyInfo == null) {
			flowPropertyInfo = new FlowPropertyInfo();
		}
		return flowPropertyInfo;
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
	public FlowProperty copy() {
		var copy = new FlowProperty();
		Val.copy(flowPropertyInfo, copy::withFlowPropertyInfo);
		Val.copy(modelling, copy::withModelling);
		Val.copy(adminInfo, copy::withAdminInfo);
		Val.copy(other, copy::withOther);
		copy.withSchemaVersion(schemaVersion);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<FlowProperty> toElement() {
		var qname = new QName(
			"http://lca.jrc.it/ILCD/FlowProperty", "flowPropertyDataSet");
		return new JAXBElement<>(qname, FlowProperty.class, null, this);
	}

}
