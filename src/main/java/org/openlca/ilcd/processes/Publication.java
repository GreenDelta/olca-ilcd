
package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.PublicationStatus;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PublicationAndOwnershipType", propOrder = {
		"lastRevision",
		"version",
		"precedingVersions",
		"uri",
		"status",
		"republication",
		"registrationAuthority",
		"registrationNumber",
		"owner",
		"copyright",
		"entitiesWithExclusiveAccess",
		"license",
		"accessRestrictions",
		"other"
})
public class Publication implements Copyable<Publication> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "dateOfLastRevision")
	public XMLGregorianCalendar lastRevision;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", required = true, name = "dataSetVersion")
	public String version;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToPrecedingDataSetVersion")
	public final List<Ref> precedingVersions = new ArrayList<>();

	@XmlSchemaType(name = "anyURI")
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "permanentDataSetURI")
	public String uri;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "workflowAndPublicationStatus")
	public PublicationStatus status;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToUnchangedRepublication")
	public Ref republication;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToRegistrationAuthority")
	public Ref registrationAuthority;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public String registrationNumber;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToOwnershipOfDataSet")
	public Ref owner;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Boolean copyright;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToEntitiesWithExclusiveAccess")
	public final List<Ref> entitiesWithExclusiveAccess = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "licenseType")
	public LicenseType license;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public final List<LangString> accessRestrictions = new ArrayList<>();

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Publication copy() {
		var clone = new Publication();
		clone.lastRevision = lastRevision;
		clone.version = version;
		Ref.copy(precedingVersions, clone.precedingVersions);
		clone.uri = uri;
		clone.status = status;
		if (republication != null)
			clone.republication = republication.copy();
		if (registrationAuthority != null)
			clone.registrationAuthority = registrationAuthority.copy();
		clone.registrationNumber = registrationNumber;
		if (owner != null)
			clone.owner = owner.copy();
		clone.copyright = copyright;
		Ref.copy(entitiesWithExclusiveAccess, clone.entitiesWithExclusiveAccess);
		clone.license = license;
		LangString.copy(accessRestrictions, clone.accessRestrictions);
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}

}
