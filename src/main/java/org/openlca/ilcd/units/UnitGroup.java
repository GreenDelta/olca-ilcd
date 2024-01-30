package org.openlca.ilcd.units;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitGroupDataSetType", propOrder = {
		"unitGroupInfo",
		"modelling",
		"adminInfo",
		"unitList",
		"other"
})
public class UnitGroup implements IDataSet, Copyable<UnitGroup> {

	@XmlElement(required = true, name = "unitGroupInformation")
	private UnitGroupInfo unitGroupInfo;

	@XmlElement(name = "modellingAndValidation")
	private Modelling modelling;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlElementWrapper(name="units")
	@XmlElement(name = "unit")
	private List<Unit> units;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "version", required = true)
	private String version;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public UnitGroupInfo getUnitGroupInfo() {
		return unitGroupInfo;
	}

	public Modelling getModelling() {
		return modelling;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public List<Unit> getUnits() {
		return units != null ? units : List.of();
	}

	public Other getOther() {
		return other;
	}

	public String getVersion() {
		return version;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public UnitGroup withUnitGroupInfo(UnitGroupInfo unitGroupInfo) {
		this.unitGroupInfo = unitGroupInfo;
		return this;
	}

	public UnitGroup withModelling(Modelling modelling) {
		this.modelling = modelling;
		return this;
	}

	public UnitGroup withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public UnitGroup withUnits(List<Unit> units) {
		this.units = units;
		return this;
	}

	public UnitGroup withOther(Other other) {
		this.other = other;
		return this;
	}

	public UnitGroup withVersion(String version) {
		this.version = version;
		return this;
	}

	public UnitGroup withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public UnitGroupInfo withUnitGroupInfo() {
		if (unitGroupInfo == null) {
			unitGroupInfo = new UnitGroupInfo();
		}
		return unitGroupInfo;
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

	public List<Unit> withUnits() {
		if (units == null) {
			units = new ArrayList<>();
		}
		return units;
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
	public UnitGroup copy() {
		var copy = new UnitGroup();
		Val.copy(unitGroupInfo, copy::withUnitGroupInfo);
		Val.copy(modelling, copy::withModelling);
		Val.copy(adminInfo, copy::withAdminInfo);
		Val.copy(units, copy::withUnits);
		Val.copy(other, copy::withOther);
		copy.withVersion(version);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<UnitGroup> toElement() {
		var qname = new QName(
			"http://lca.jrc.it/ILCD/UnitGroup", "unitGroupDataSet");
		return new JAXBElement<>(qname, UnitGroup.class, null, this);
	}
}
