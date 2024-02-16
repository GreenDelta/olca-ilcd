package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "downstreamLinks" })
public class Connection implements Copyable<Connection> {

	@XmlAttribute(name = "flowUUID")
	private String outputFlow;

	@XmlAttribute(name = "dominant")
	private Boolean dominant;

	@XmlElement(name = "downstreamProcess")
	private List<DownstreamLink> downstreamLinks;

	// region getters

	public String getOutputFlow() {
		return outputFlow;
	}

	public Boolean getDominant() {
		return dominant;
	}

	public List<DownstreamLink> getDownstreamLinks() {
		return downstreamLinks != null ? downstreamLinks : Collections.emptyList();
	}

	// endregion

	// region setters

	public Connection withOutputFlow(String outputFlow) {
		this.outputFlow = outputFlow;
		return this;
	}

	public Connection withDominant(Boolean dominant) {
		this.dominant = dominant;
		return this;
	}

	public Connection withDownstreamLinks(List<DownstreamLink> downstreamLinks) {
		this.downstreamLinks = downstreamLinks;
		return this;
	}

	public List<DownstreamLink> withDownstreamLinks() {
		if (downstreamLinks == null) {
			downstreamLinks = new ArrayList<>();
		}
		return downstreamLinks;
	}

	// endregion

	@Override
	public Connection copy() {
		var copy = new Connection();
		copy.withOutputFlow(outputFlow);
		copy.withDominant(dominant);
		Val.copy(downstreamLinks, copy::withDownstreamLinks);
		return copy;
	}
}
