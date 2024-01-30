package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class DownstreamLink implements Copyable<DownstreamLink> {

	@XmlAttribute(name = "id")
	private int process;

	@XmlAttribute(name = "flowUUID")
	private String inputFlow;

	@XmlAttribute(name = "location")
	private String location;

	@XmlAttribute(name = "dominant")
	private Boolean dominant;

	/**
	 * This is an extension attribute to support linking of exchanges that have
	 * the same flow. In the eILCD format this is only possible when these
	 * exchanges have different locations. However, in openLCA you can for
	 * example have multiple inputs of the same product that come from different
	 * providers (without location attributes). To solve this case we add the
	 * internal exchange ID of the linked exchange to the connection.
	 */
	@XmlAttribute(name = "linkedExchange", namespace = "http://openlca.org/ilcd-extensions")
	private Integer linkedExchange;

	// region getters

	public int getProcess() {
		return process;
	}

	public String getInputFlow() {
		return inputFlow;
	}

	public String getLocation() {
		return location;
	}

	public Boolean getDominant() {
		return dominant;
	}

	public Integer getLinkedExchange() {
		return linkedExchange;
	}

	// endregion

	// region setters

	public DownstreamLink withProcess(int process) {
		this.process = process;
		return this;
	}

	public DownstreamLink withInputFlow(String inputFlow) {
		this.inputFlow = inputFlow;
		return this;
	}

	public DownstreamLink withLocation(String location) {
		this.location = location;
		return this;
	}

	public DownstreamLink withDominant(Boolean dominant) {
		this.dominant = dominant;
		return this;
	}

	public DownstreamLink withLinkedExchange(Integer linkedExchange) {
		this.linkedExchange = linkedExchange;
		return this;
	}

	// endregion

	@Override
	public DownstreamLink copy() {
		var copy = new DownstreamLink();
		copy.withProcess(process);
		copy.withInputFlow(inputFlow);
		copy.withLocation(location);
		copy.withDominant(dominant);
		copy.withLinkedExchange(linkedExchange);
		return copy;
	}
}
