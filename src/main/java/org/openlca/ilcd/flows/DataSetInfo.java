
package org.openlca.ilcd.flows;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.annotations.FreeText;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType", propOrder = {
		"uuid",
		"name",
		"synonyms",
		"classificationInformation",
		"casNumber",
		"sumFormula",
		"generalComment",
		"other"
})
public class DataSetInfo {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	public String uuid;

	public FlowName name;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public final List<LangString> synonyms = new ArrayList<>();

	@XmlElement(name = "classificationInformation")
	public FlowCategoryInfo classificationInformation;

	@XmlElement(name = "CASNumber")
	public String casNumber;

	public String sumFormula;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public final List<LangString> generalComment = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

}
