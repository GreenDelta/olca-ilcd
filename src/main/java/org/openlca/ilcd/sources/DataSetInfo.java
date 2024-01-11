package org.openlca.ilcd.sources;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.commons.annotations.Label;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType", propOrder = {
		"uuid",
		"name",
		"classifications",
		"citation",
		"type",
		"description",
		"files",
		"contacts",
		"logo",
		"other" })
public class DataSetInfo {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	public String uuid;

	@Label
	@XmlElement(name = "shortName", namespace = "http://lca.jrc.it/ILCD/Common")
	public final List<LangString> name = new ArrayList<>();

	@XmlElementWrapper(name = "classificationInformation")
	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	public final List<Classification> classifications = new ArrayList<>();

	@XmlElement(name = "sourceCitation")
	public String citation;

	@XmlElement(name = "publicationType")
	public SourceType type;

	@FreeText
	@XmlElement(name = "sourceDescriptionOrComment")
	public final List<LangString> description = new ArrayList<>();

	@XmlElement(name = "referenceToDigitalFile")
	public final List<FileRef> files = new ArrayList<>();

	@XmlElement(name = "referenceToContact")
	public final List<Ref> contacts = new ArrayList<>();

	@XmlElement(name = "referenceToLogo")
	public Ref logo;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public Map<QName, String> otherAttributes = new HashMap<>();

}
