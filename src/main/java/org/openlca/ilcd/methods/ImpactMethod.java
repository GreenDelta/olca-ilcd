package org.openlca.ilcd.methods;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
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
@XmlType(name = "LCIAMethodDataSetType", propOrder = {
	"methodInfo",
	"modelling",
	"adminInfo",
	"characterisationFactors",
	"other"
})
public class ImpactMethod implements IDataSet {

	@XmlElement(name = "LCIAMethodInformation", required = true)
	public MethodInfo methodInfo;

	@XmlElement(name = "modellingAndValidation", required = true)
	public Modelling modelling;

	@XmlElement(name = "administrativeInformation")
	public AdminInfo adminInfo;

	public FactorList characterisationFactors;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAttribute(name = "version", required = true)
	public String version;

	@XmlAttribute(name = "locations")
	public String locations;

	@XmlAttribute(name = "LCIAMethodologies")
	public String methodologies;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public DataSetType getDataSetType() {
		return DataSetType.IMPACT_METHOD;
	}

	@Override
	public String getURI() {
		if (adminInfo == null || adminInfo.publication == null)
			return null;
		return adminInfo.publication.uri;
	}

	@Override
	public String getUUID() {
		if (methodInfo == null || methodInfo.dataSetInfo == null)
			return null;
		return methodInfo.dataSetInfo.uuid;
	}

	@Override
	public String getVersion() {
		if (adminInfo == null || adminInfo.publication == null)
			return null;
		return adminInfo.publication.version;
	}

	@Override
	public List<Classification> getClassifications() {
		if (methodInfo == null || methodInfo.dataSetInfo == null)
			return Collections.emptyList();
		return methodInfo.dataSetInfo.classifications;
	}

	@Override
	public List<LangString> getName() {
		if (methodInfo == null || methodInfo.dataSetInfo == null)
			return Collections.emptyList();
		return methodInfo.dataSetInfo.name;
	}

	public JAXBElement<ImpactMethod> toElement() {
		var qname = new QName(
			"http://lca.jrc.it/ILCD/LCIAMethod", "LCIAMethodDataSet");
		return new JAXBElement<>(qname, ImpactMethod.class, null, this);
	}

}
