package org.openlca.ilcd.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "groups", "processes" })
public class Technology implements Copyable<Technology> {

	@XmlElementWrapper(name = "groupDeclarations")
	@XmlElement(name = "group")
	private List<Group> groups;

	@XmlElementWrapper(name = "processes")
	@XmlElement(name = "processInstance")
	private List<ProcessInstance> processes;

	// region getters

	public List<Group> getGroups() {
		return groups != null ? groups : List.of();
	}

	public List<ProcessInstance> getProcesses() {
		return processes != null ? processes : List.of();
	}

	// endregion

	// region setters

	public Technology withGroups(List<Group> groups) {
		this.groups = groups;
		return this;
	}

	public Technology withProcesses(List<ProcessInstance> processes) {
		this.processes = processes;
		return this;
	}

	public List<Group> withGroups() {
		if (groups == null) {
			groups = new ArrayList<>();
		}
		return groups;
	}

	public List<ProcessInstance> withProcesses() {
		if (processes == null) {
			processes = new ArrayList<>();
		}
		return processes;
	}

	// endregion

	@Override
	public Technology copy() {
		var copy = new Technology();
		Val.copy(groups, copy::withGroups);
		Val.copy(processes, copy::withProcesses);
		return copy;
	}

}
