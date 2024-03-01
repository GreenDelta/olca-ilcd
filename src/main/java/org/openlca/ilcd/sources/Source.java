package org.openlca.ilcd.sources;

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
@XmlType(name = "SourceDataSetType", propOrder = {
		"sourceInfo",
		"adminInfo",
		"other"
})
public class Source implements IDataSet, Copyable<Source> {

	@XmlElement(required = true, name = "sourceInformation")
	private SourceInfo sourceInfo;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlAttribute(name = "version", required = true)
	private String schemaVersion = SCHEMA_VERSION;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public SourceInfo getSourceInfo() {
		return sourceInfo;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	@Override
	public String getSchemaVersion() {
		return schemaVersion;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Source withSourceInfo(SourceInfo sourceInfo) {
		this.sourceInfo = sourceInfo;
		return this;
	}

	public Source withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public Source withSchemaVersion(String version) {
		this.schemaVersion = version;
		return this;
	}

	public Source withOther(Other other) {
		this.other = other;
		return this;
	}

	public Source withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public SourceInfo withSourceInfo() {
		if (sourceInfo == null) {
			sourceInfo = new SourceInfo();
		}
		return sourceInfo;
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
	public Source copy() {
		var copy = new Source();
		Val.copy(sourceInfo, copy::withSourceInfo);
		Val.copy(adminInfo, copy::withAdminInfo);
		copy.withSchemaVersion(schemaVersion);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<Source> toElement() {
		var qname = new QName("http://lca.jrc.it/ILCD/Source", "sourceDataSet");
		return new JAXBElement<>(qname, Source.class, null, this);
	}
}
