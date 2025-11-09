package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"version",
		"uri",
		"copyright"
})
public class Publication implements Copyable<Publication> {

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "dataSetVersion")
	private String version;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", name = "permanentDataSetURI")
	private String uri;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Boolean copyright;

	// region getters

	public String getVersion() {
		return version;
	}

	public String getUri() {
		return uri;
	}

	public Boolean getCopyright() {
		return copyright;
	}

	// endregion

	// region setters

	public Publication withVersion(String version) {
		this.version = version;
		return this;
	}

	public Publication withUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Publication withCopyright(Boolean copyright) {
		this.copyright = copyright;
		return this;
	}

	// endregion

	@Override
	public Publication copy() {
		var copy = new Publication();
		copy.withVersion(version);
		copy.withUri(uri);
		copy.withCopyright(copyright);
		return copy;
	}

}
