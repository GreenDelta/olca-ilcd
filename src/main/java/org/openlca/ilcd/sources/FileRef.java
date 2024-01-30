
package org.openlca.ilcd.sources;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceToDigitalFileType")
public class FileRef implements Copyable<FileRef> {

	@XmlAttribute(name = "uri")
	@XmlSchemaType(name = "anyURI")
	private String uri;

	// region getters

	public String getUri() {
		return uri;
	}

	// endregion

	// region setters

	public FileRef withUri(String uri) {
		this.uri = uri;
		return this;
	}

	// endregion

	@Override
	public FileRef copy() {
		var copy = new FileRef();
		copy.withUri(uri);
		return copy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof FileRef other))
			return false;
		if (this.uri == null && other.uri == null)
			return true;
		if (this.uri == null || other.uri == null)
			return false;
		return this.uri.trim().equalsIgnoreCase(other.uri.trim());
	}

	@Override
	public int hashCode() {
		return uri == null ? super.hashCode() : uri.hashCode();
	}

}
