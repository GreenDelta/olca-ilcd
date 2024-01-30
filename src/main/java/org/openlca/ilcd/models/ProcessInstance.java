package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.List;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Ref;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
	"process",
	"scalingFactor",
	"groupRefs",
	"parameters",
	"connections" })
public class ProcessInstance implements Copyable<ProcessInstance> {

	@XmlAttribute(name = "dataSetInternalID")
	private int id;

	@XmlAttribute(name = "multiplicationFactor")
	private double multiplicationFactor;

	@XmlElement(name = "referenceToProcess")
	private Ref process;

	@XmlElement(name = "scalingFactor")
	private Double scalingFactor;

	@XmlElementWrapper(name = "groups")
	@XmlElement(name = "memberOf")
	private List<GroupRef> groupRefs;

	@XmlElementWrapper(name = "parameters")
	@XmlElement(name = "parameter")
	private List<Parameter> parameters;

	@XmlElementWrapper(name = "connections")
	@XmlElement(name = "outputExchange")
	private List<Connection> connections;

	// region getters

	public int getId() {
		return id;
	}

	public double getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public Ref getProcess() {
		return process;
	}

	public Double getScalingFactor() {
		return scalingFactor;
	}

	public List<GroupRef> getGroupRefs() {
		return groupRefs != null ? groupRefs : List.of();
	}

	public List<Parameter> getParameters() {
		return parameters != null ? parameters : List.of();
	}

	public List<Connection> getConnections() {
		return connections != null ? connections : List.of();
	}

	// endregion

	// region setters

	public ProcessInstance withId(int id) {
		this.id = id;
		return this;
	}

	public ProcessInstance withMultiplicationFactor(double multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
		return this;
	}

	public ProcessInstance withProcess(Ref process) {
		this.process = process;
		return this;
	}

	public ProcessInstance withScalingFactor(Double scalingFactor) {
		this.scalingFactor = scalingFactor;
		return this;
	}

	public ProcessInstance withGroupRefs(List<GroupRef> groupRefs) {
		this.groupRefs = groupRefs;
		return this;
	}

	public ProcessInstance withParameters(List<Parameter> parameters) {
		this.parameters = parameters;
		return this;
	}

	public ProcessInstance withConnections(List<Connection> connections) {
		this.connections = connections;
		return this;
	}

	public Ref withProcess() {
		if (process == null) {
			process = new Ref();
		}
		return process;
	}

	public List<GroupRef> withGroupRefs() {
		if (groupRefs == null) {
			groupRefs = new ArrayList<>();
		}
		return groupRefs;
	}

	public List<Parameter> withParameters() {
		if (parameters == null) {
			parameters = new ArrayList<>();
		}
		return parameters;
	}

	public List<Connection> withConnections() {
		if (connections == null) {
			connections = new ArrayList<>();
		}
		return connections;
	}

	// endregion

	@Override
	public ProcessInstance copy() {
		var copy = new ProcessInstance();
		copy.withId(id);
		copy.withMultiplicationFactor(multiplicationFactor);
		Val.copy(process, copy::withProcess);
		copy.withScalingFactor(scalingFactor);
		Val.copy(groupRefs, copy::withGroupRefs);
		Val.copy(parameters, copy::withParameters);
		Val.copy(connections, copy::withConnections);
		return copy;
	}

}
