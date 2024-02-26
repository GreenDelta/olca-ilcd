
package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.PublicationStatus;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.processes.epd.EpdPublicationExtension;
import org.openlca.ilcd.util.Val;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
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
		"epdExtension"
})
public class Publication implements Copyable<Publication> {

	@XmlElement(namespace = Vocab.COMMON, name = "dateOfLastRevision")
	private XMLGregorianCalendar lastRevision;

	@XmlElement(namespace = Vocab.COMMON, required = true, name = "dataSetVersion")
	private String version;

	@XmlElement(namespace = Vocab.COMMON, name = "referenceToPrecedingDataSetVersion")
	private List<Ref> precedingVersions;

	@XmlSchemaType(name = "anyURI")
	@XmlElement(namespace = Vocab.COMMON, name = "permanentDataSetURI")
	private String uri;

	@XmlElement(namespace = Vocab.COMMON, name = "workflowAndPublicationStatus")
	private PublicationStatus status;

	@XmlElement(namespace = Vocab.COMMON, name = "referenceToUnchangedRepublication")
	private Ref republication;

	@XmlElement(namespace = Vocab.COMMON, name = "referenceToRegistrationAuthority")
	private Ref registrationAuthority;

	@XmlElement(namespace = Vocab.COMMON)
	private String registrationNumber;

	@XmlElement(namespace = Vocab.COMMON, name = "referenceToOwnershipOfDataSet")
	private Ref owner;

	@XmlElement(namespace = Vocab.COMMON)
	private Boolean copyright;

	@XmlElement(namespace = Vocab.COMMON, name = "referenceToEntitiesWithExclusiveAccess")
	private List<Ref> entitiesWithExclusiveAccess;

	@XmlElement(namespace = Vocab.COMMON, name = "licenseType")
	private LicenseType license;

	@FreeText
	@XmlElement(namespace = Vocab.COMMON)
	private List<LangString> accessRestrictions;

	@XmlElement(name= "other", namespace = Vocab.COMMON)
	private EpdPublicationExtension epdExtension;

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
		return precedingVersions != null ? precedingVersions : Collections.emptyList();
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
		return entitiesWithExclusiveAccess != null ? entitiesWithExclusiveAccess : Collections.emptyList();
	}

	public LicenseType getLicense() {
		return license;
	}

	public List<LangString> getAccessRestrictions() {
		return accessRestrictions != null ? accessRestrictions : Collections.emptyList();
	}

	public EpdPublicationExtension getEpdExtension() {
		return epdExtension;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
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

	public Publication withEpdExtension(EpdPublicationExtension ext) {
		this.epdExtension = ext;
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

	public EpdPublicationExtension withEpdExtension() {
		if (epdExtension == null) {
			epdExtension = new EpdPublicationExtension();
		}
		return epdExtension;
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
		Val.copy(epdExtension, copy::withEpdExtension);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
