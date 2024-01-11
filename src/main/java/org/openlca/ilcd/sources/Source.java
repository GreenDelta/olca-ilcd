package org.openlca.ilcd.sources;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SourceDataSetType", propOrder = {
		"sourceInfo",
		"adminInfo",
		"other"
})
public class Source implements IDataSet {

	@XmlElement(required = true, name = "sourceInformation")
	public SourceInfo sourceInfo;

	@XmlElement(name = "administrativeInformation")
	public AdminInfo adminInfo;

	@XmlAttribute(name = "version", required = true)
	public String version;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public DataSetType getDataSetType() {
		return DataSetType.SOURCE;
	}

	@Override
	public String getURI() {
		if (adminInfo == null || adminInfo.publication == null)
			return null;
		return adminInfo.publication.uri;
	}

	@Override
	public String getUUID() {
		if (sourceInfo == null || sourceInfo.dataSetInfo == null)
			return null;
		return sourceInfo.dataSetInfo.uuid;
	}

	@Override
	public String getVersion() {
		if (adminInfo == null || adminInfo.publication == null)
			return null;
		return adminInfo.publication.version;
	}

	@Override
	public List<Classification> getClassifications() {
		if (sourceInfo == null || sourceInfo.dataSetInfo == null)
			return Collections.emptyList();
		return sourceInfo.dataSetInfo.classifications;
	}

	@Override
	public List<LangString> getName() {
		if (sourceInfo == null || sourceInfo.dataSetInfo == null)
			return Collections.emptyList();
		return sourceInfo.dataSetInfo.name;
	}
}
