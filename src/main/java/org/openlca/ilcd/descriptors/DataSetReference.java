
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlobalReferenceType", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI", propOrder = {
	"shortDescription"
})
public class DataSetReference implements Copyable<DataSetReference> {

	private List<LangString> shortDescription;

	@XmlAttribute(name = "type", required = true)
	private DataSetType type;

	@XmlAttribute(name = "refObjectId")
	private String refObjectId;

	@XmlAttribute(name = "version")
	private String version;

	@XmlAttribute(name = "uri")
	@XmlSchemaType(name = "anyURI")
	private String uri;

	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	private String href;

	// region getters

	public List<LangString> getShortDescription() {
		return shortDescription != null ? shortDescription : Collections.emptyList();
	}

	public DataSetType getType() {
		return type;
	}

	public String getRefObjectId() {
		return refObjectId;
	}

	public String getVersion() {
		return version;
	}

	public String getUri() {
		return uri;
	}

	public String getHref() {
		return href;
	}

	// endregion

	// region setters

	public DataSetReference withShortDescription(List<LangString> shortDescription) {
		this.shortDescription = shortDescription;
		return this;
	}

	public DataSetReference withType(DataSetType type) {
		this.type = type;
		return this;
	}

	public DataSetReference withRefObjectId(String refObjectId) {
		this.refObjectId = refObjectId;
		return this;
	}

	public DataSetReference withVersion(String version) {
		this.version = version;
		return this;
	}

	public DataSetReference withUri(String uri) {
		this.uri = uri;
		return this;
	}

	public DataSetReference withHref(String href) {
		this.href = href;
		return this;
	}

	public List<LangString> withShortDescription() {
		if (shortDescription == null) {
			shortDescription = new ArrayList<>();
		}
		return shortDescription;
	}

	// endregion

	@Override
	public DataSetReference copy() {
		var copy = new DataSetReference();
		Val.copy(shortDescription, copy::withShortDescription);
		copy.withType(type);
		copy.withRefObjectId(refObjectId);
		copy.withVersion(version);
		copy.withUri(uri);
		copy.withHref(href);
		return copy;
	}

}
