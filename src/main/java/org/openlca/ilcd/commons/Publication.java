
package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PublicationAndOwnershipType", propOrder = {
	"version",
	"precedingVersions",
	"uri",
	"owner",
	"other"
})
public class Publication implements Copyable<Publication> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", required = true, name = "dataSetVersion")
	private String version;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToPrecedingDataSetVersion")
	private List<Ref> precedingVersions;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "permanentDataSetURI")
	@XmlSchemaType(name = "anyURI")
	private String uri;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "referenceToOwnershipOfDataSet")
	private Ref owner;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public String getVersion() {
		return version;
	}

	public List<Ref> getPrecedingVersions() {
		return precedingVersions != null
			? precedingVersions
			: Collections.emptyList();
	}

	public String getUri() {
		return uri;
	}

	public Ref getOwner() {
		return owner;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

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

	public Publication withOwner(Ref owner) {
		this.owner = owner;
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

	public Ref withOwner() {
		if (owner == null) {
			owner = new Ref();
		}
		return owner;
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
		var copy = new Publication()
			.withUri(uri)
			.withVersion(version);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		Val.copy(precedingVersions, copy::withPrecedingVersions);
		Val.copy(owner, copy::withOwner);
		return null;
	}
}
