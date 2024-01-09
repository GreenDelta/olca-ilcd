package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.ReviewType;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "scope", "dataQualityIndicators",
		"reviewDetails", "otherReviewDetails", "reviewer" })
@XmlRootElement(name = "review")
public class Review {

	public final List<Scope> scope = new ArrayList<>();

	public DataQualityIndicators dataQualityIndicators;

	public LangString reviewDetails;

	public LangString otherReviewDetails;

	public DataSetReference reviewer;

	@XmlAttribute(name = "type", required = true)
	public ReviewType type;

}
