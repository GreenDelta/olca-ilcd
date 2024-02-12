package org.openlca.ilcd.contacts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.Label;
import org.openlca.ilcd.commons.annotations.ShortText;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInformationType",
	propOrder = {
		"uuid",
		"shortName",
		"name",
		"classifications",
		"contactAddress",
		"telephone",
		"telefax",
		"email",
		"webSite",
		"centralContactPoint",
		"description",
		"belongsTo",
		"logo",
		"other"
	})
public class DataSetInfo implements Copyable<DataSetInfo> {

	@XmlElement(name = "UUID", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	private String uuid;

	@Label
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> shortName;

	@Label
	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private List<LangString> name;

	@XmlElementWrapper(name = "classificationInformation")
	@XmlElement(name = "classification", namespace = "http://lca.jrc.it/ILCD/Common")
	private List<Classification> classifications;

	@ShortText
	private List<LangString> contactAddress;

	private String telephone;

	private String telefax;

	private String email;

	@XmlElement(name = "WWWAddress")
	private String webSite;

	@ShortText
	private List<LangString> centralContactPoint;

	@ShortText
	@XmlElement(name = "contactDescriptionOrComment")
	private List<LangString> description;

	@XmlElement(name = "referenceToContact")
	private List<Ref> belongsTo;

	@XmlElement(name = "referenceToLogo")
	private Ref logo;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getUUID() {
		return uuid;
	}

	public List<LangString> getShortName() {
		return shortName != null ? shortName : Collections.emptyList();
	}

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
	}

	public List<Classification> getClassifications() {
		return classifications != null ? classifications : Collections.emptyList();
	}

	public List<LangString> getContactAddress() {
		return contactAddress != null ? contactAddress : Collections.emptyList();
	}

	public String getTelephone() {
		return telephone;
	}

	public String getTelefax() {
		return telefax;
	}

	public String getEmail() {
		return email;
	}

	public String getWebSite() {
		return webSite;
	}

	public List<LangString> getCentralContactPoint() {
		return centralContactPoint != null ? centralContactPoint : Collections.emptyList();
	}

	public List<LangString> getDescription() {
		return description != null ? description : Collections.emptyList();
	}

	public List<Ref> getBelongsTo() {
		return belongsTo != null ? belongsTo : Collections.emptyList();
	}

	public Ref getLogo() {
		return logo;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public DataSetInfo withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public DataSetInfo withShortName(List<LangString> shortName) {
		this.shortName = shortName;
		return this;
	}

	public DataSetInfo withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public DataSetInfo withClassifications(List<Classification> classifications) {
		this.classifications = classifications;
		return this;
	}

	public DataSetInfo withContactAddress(List<LangString> contactAddress) {
		this.contactAddress = contactAddress;
		return this;
	}

	public DataSetInfo withTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	public DataSetInfo withTelefax(String telefax) {
		this.telefax = telefax;
		return this;
	}

	public DataSetInfo withEmail(String email) {
		this.email = email;
		return this;
	}

	public DataSetInfo withWebSite(String webSite) {
		this.webSite = webSite;
		return this;
	}

	public DataSetInfo withCentralContactPoint(List<LangString> centralContactPoint) {
		this.centralContactPoint = centralContactPoint;
		return this;
	}

	public DataSetInfo withDescription(List<LangString> description) {
		this.description = description;
		return this;
	}

	public DataSetInfo withBelongsTo(List<Ref> belongsTo) {
		this.belongsTo = belongsTo;
		return this;
	}

	public DataSetInfo withLogo(Ref logo) {
		this.logo = logo;
		return this;
	}

	public DataSetInfo withOther(Other other) {
		this.other = other;
		return this;
	}

	public DataSetInfo withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withShortName() {
		if (shortName == null) {
			shortName = new ArrayList<>();
		}
		return shortName;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
	}

	public List<Classification> withClassifications() {
		if (classifications == null) {
			classifications = new ArrayList<>();
		}
		return classifications;
	}

	public List<LangString> withContactAddress() {
		if (contactAddress == null) {
			contactAddress = new ArrayList<>();
		}
		return contactAddress;
	}

	public List<LangString> withCentralContactPoint() {
		if (centralContactPoint == null) {
			centralContactPoint = new ArrayList<>();
		}
		return centralContactPoint;
	}

	public List<LangString> withDescription() {
		if (description == null) {
			description = new ArrayList<>();
		}
		return description;
	}

	public List<Ref> withBelongsTo() {
		if (belongsTo == null) {
			belongsTo = new ArrayList<>();
		}
		return belongsTo;
	}

	public Ref withLogo() {
		if (logo == null) {
			logo = new Ref();
		}
		return logo;
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
	public DataSetInfo copy() {
		var copy = new DataSetInfo();
		copy.withUUID(uuid);
		Val.copy(shortName, copy::withShortName);
		Val.copy(name, copy::withName);
		Val.copy(classifications, copy::withClassifications);
		Val.copy(contactAddress, copy::withContactAddress);
		copy.withTelephone(telephone);
		copy.withTelefax(telefax);
		copy.withEmail(email);
		copy.withWebSite(webSite);
		Val.copy(centralContactPoint, copy::withCentralContactPoint);
		Val.copy(description, copy::withDescription);
		Val.copy(belongsTo, copy::withBelongsTo);
		Val.copy(logo, copy::withLogo);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}
}
