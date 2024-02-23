package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"copyright",
		"licenseType",
		"useRestrictions"
})
@XmlRootElement(name = "accessInformation")
public class AccessInfo implements Copyable<AccessInfo> {

	private Boolean copyright;
	private String licenseType;
	private List<LangString> useRestrictions;

	// region getters

	public Boolean getCopyright() {
		return copyright;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public List<LangString> getUseRestrictions() {
		return useRestrictions != null ? useRestrictions : Collections.emptyList();
	}

	// endregion

	// region setters

	public AccessInfo withCopyright(Boolean copyright) {
		this.copyright = copyright;
		return this;
	}

	public AccessInfo withLicenseType(String licenseType) {
		this.licenseType = licenseType;
		return this;
	}

	public AccessInfo withUseRestrictions(List<LangString> useRestrictions) {
		this.useRestrictions = useRestrictions;
		return this;
	}

	public List<LangString> withUseRestrictions() {
		if (useRestrictions == null) {
			useRestrictions = new ArrayList<>();
		}
		return useRestrictions;
	}

	// endregion

	@Override
	public AccessInfo copy() {
		var copy = new AccessInfo();
		copy.withCopyright(copyright);
		copy.withLicenseType(licenseType);
		Val.copy(useRestrictions, copy::withUseRestrictions);
		return copy;
	}

}
