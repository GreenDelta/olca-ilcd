package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.ShortText;
import org.openlca.ilcd.flowproperties.ComplianceDeclaration;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModellingAndValidationType", propOrder = {
		"useAdvice",
		"normalisationAndWeighting",
		"dataSources",
		"completeness",
		"validation",
		"complianceDeclarations",
		"other"
})
public class Modelling {

	@ShortText
	@XmlElement(name = "useAdviceForDataSet")
	public final List<LangString> useAdvice = new ArrayList<>();

	@XmlElement(name = "LCIAMethodNormalisationAndWeighting")
	public NormalisationAndWeighting normalisationAndWeighting;

	@XmlElementWrapper(name = "dataSources")
	@XmlElement(name = "referenceToDataSource")
	public final List<Ref> dataSources = new ArrayList<>();

	public Completeness completeness;

	public Validation validation;

	@XmlElementWrapper(name = "complianceDeclarations")
	@XmlElement(name = "compliance")
	public ComplianceDeclaration[] complianceDeclarations;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
