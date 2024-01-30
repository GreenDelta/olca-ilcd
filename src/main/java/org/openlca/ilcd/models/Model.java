package org.openlca.ilcd.models;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"info", "modelling", "adminInfo"})
@XmlRootElement(name = "lifeCycleModelDataSet", namespace = "http://eplca.jrc.ec.europa.eu/ILCD/LifeCycleModel/2017")
public class Model implements IDataSet, Copyable<Model> {

	@XmlElement(name = "lifeCycleModelInformation")
	private ModelInfo info;

	@XmlElement(name = "modellingAndValidation")
	private Modelling modelling;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlAttribute(name = "version")
	private String version;

	@XmlAttribute(name = "locations")
	private String locations;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public ModelInfo getInfo() {
		return info;
	}

	public Modelling getModelling() {
		return modelling;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public String getVersion() {
		return version;
	}

	public String getLocations() {
		return locations;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Model withInfo(ModelInfo info) {
		this.info = info;
		return this;
	}

	public Model withModelling(Modelling modelling) {
		this.modelling = modelling;
		return this;
	}

	public Model withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public Model withVersion(String version) {
		this.version = version;
		return this;
	}

	public Model withLocations(String locations) {
		this.locations = locations;
		return this;
	}

	public Model withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public ModelInfo withInfo() {
		if (info == null) {
			info = new ModelInfo();
		}
		return info;
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

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Model copy() {
		var copy = new Model();
		Val.copy(info, copy::withInfo);
		Val.copy(modelling, copy::withModelling);
		Val.copy(adminInfo, copy::withAdminInfo);
		copy.withVersion(version);
		copy.withLocations(locations);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<Model> toElement() {
		var qname = new QName(
			"http://eplca.jrc.ec.europa.eu/ILCD/LifeCycleModel/2017",
			"lifeCycleModelDataSet");
		return new JAXBElement<>(qname, Model.class, null, this);
	}
}
