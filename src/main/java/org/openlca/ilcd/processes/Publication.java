
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
import org.openlca.ilcd.util.Val;

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
	private XMLGregorianCalendar lastRevision;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", required = true, name = "dataSetVersion")
	private String version;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToPrecedingDataSetVersion")
	private List<Ref> precedingVersions;

	@XmlSchemaType(name = "anyURI")
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "permanentDataSetURI")
	private String uri;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "workflowAndPublicationStatus")
	private PublicationStatus status;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToUnchangedRepublication")
	private Ref republication;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToRegistrationAuthority")
	private Ref registrationAuthority;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private String registrationNumber;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToOwnershipOfDataSet")
	private Ref owner;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Boolean copyright;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToEntitiesWithExclusiveAccess")
	private List<Ref> entitiesWithExclusiveAccess;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "licenseType")
	private LicenseType license;

	@FreeText
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> accessRestrictions;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public XMLGregorianCalendar getLastRevision() {
		return lastRevision;
	}

	public String getVersion() {
		return version;
	}

	public List<Ref> getPrecedingVersions() {
		return precedingVersions != null ? precedingVersions : List.of();
	}

	public String getUri() {
		return uri;
	}

	public PublicationStatus getStatus() {
		return status;
	}

	public Ref getRepublication() {
		return republication;
	}

	public Ref getRegistrationAuthority() {
		return registrationAuthority;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public Ref getOwner() {
		return owner;
	}

	public Boolean getCopyright() {
		return copyright;
	}

	public List<Ref> getEntitiesWithExclusiveAccess() {
		return entitiesWithExclusiveAccess != null ? entitiesWithExclusiveAccess : List.of();
	}

	public LicenseType getLicense() {
		return license;
	}

	public List<LangString> getAccessRestrictions() {
		return accessRestrictions != null ? accessRestrictions : List.of();
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Map.of();
	}

	// endregion

	// region setters

	public Publication withLastRevision(XMLGregorianCalendar lastRevision) {
		this.lastRevision = lastRevision;
		return this;
	}

	public Publication withVersion(String version) {
		this.version = version;
		return this;
	}

	public Publication withPrecedingVersions(List<Ref> precedingVersions) {
		this.precedingVersions = precedingVersions;
		return this;
	}

	public Publication withUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Publication withStatus(PublicationStatus status) {
		this.status = status;
		return this;
	}

	public Publication withRepublication(Ref republication) {
		this.republication = republication;
		return this;
	}

	public Publication withRegistrationAuthority(Ref registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
		return this;
	}

	public Publication withRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
		return this;
	}

	public Publication withOwner(Ref owner) {
		this.owner = owner;
		return this;
	}

	public Publication withCopyright(Boolean copyright) {
		this.copyright = copyright;
		return this;
	}

	public Publication withEntitiesWithExclusiveAccess(List<Ref> entitiesWithExclusiveAccess) {
		this.entitiesWithExclusiveAccess = entitiesWithExclusiveAccess;
		return this;
	}

	public Publication withLicense(LicenseType license) {
		this.license = license;
		return this;
	}

	public Publication withAccessRestrictions(List<LangString> accessRestrictions) {
		this.accessRestrictions = accessRestrictions;
		return this;
	}

	public Publication withOther(Other other) {
		this.other = other;
		return this;
	}

	public Publication withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<Ref> withPrecedingVersions() {
		if (precedingVersions == null) {
			precedingVersions = new ArrayList<>();
		}
		return precedingVersions;
	}

	public Ref withRepublication() {
		if (republication == null) {
			republication = new Ref();
		}
		return republication;
	}

	public Ref withRegistrationAuthority() {
		if (registrationAuthority == null) {
			registrationAuthority = new Ref();
		}
		return registrationAuthority;
	}

	public Ref withOwner() {
		if (owner == null) {
			owner = new Ref();
		}
		return owner;
	}

	public List<Ref> withEntitiesWithExclusiveAccess() {
		if (entitiesWithExclusiveAccess == null) {
			entitiesWithExclusiveAccess = new ArrayList<>();
		}
		return entitiesWithExclusiveAccess;
	}

	public List<LangString> withAccessRestrictions() {
		if (accessRestrictions == null) {
			accessRestrictions = new ArrayList<>();
		}
		return accessRestrictions;
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
	public Publication copy() {
		var copy = new Publication();
		copy.withLastRevision(lastRevision);
		copy.withVersion(version);
		Val.copy(precedingVersions, copy::withPrecedingVersions);
		copy.withUri(uri);
		copy.withStatus(status);
		Val.copy(republication, copy::withRepublication);
		Val.copy(registrationAuthority, copy::withRegistrationAuthority);
		copy.withRegistrationNumber(registrationNumber);
		Val.copy(owner, copy::withOwner);
		copy.withCopyright(copyright);
		Val.copy(entitiesWithExclusiveAccess, copy::withEntitiesWithExclusiveAccess);
		copy.withLicense(license);
		Val.copy(accessRestrictions, copy::withAccessRestrictions);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

}
