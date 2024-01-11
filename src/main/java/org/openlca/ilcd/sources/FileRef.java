
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
	public String uri;

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

	@Override
	public FileRef copy() {
		FileRef clone = new FileRef();
		clone.uri = uri;
		return clone;
	}
}
