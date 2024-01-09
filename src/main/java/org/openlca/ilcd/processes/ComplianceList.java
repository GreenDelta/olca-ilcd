package org.openlca.ilcd.processes;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplianceList implements Copyable<ComplianceList> {

	@XmlElement(name = "compliance", required = true)
	public final List<ComplianceDeclaration> entries = new ArrayList<>();

	@Override
	public ComplianceList copy() {
		var clone = new ComplianceList();
		for (var e : entries) {
			if (e != null)
				clone.entries.add(e.copy());
		}
		return clone;
	}

}
