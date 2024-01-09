
package org.openlca.ilcd.processes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Time;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessInformationType", propOrder = {
		"dataSetInfo",
		"quantitativeReference",
		"time",
		"geography",
		"technology",
		"parameters",
		"other"
})
public class ProcessInfo implements Copyable<ProcessInfo> {

	@XmlElement(required = true, name = "dataSetInformation")
	public DataSetInfo dataSetInfo;

	public QuantitativeReference quantitativeReference;

	public Time time;

	public Geography geography;

	public Technology technology;

	@XmlElement(name = "mathematicalRelations")
	public ParameterSection parameters;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public ProcessInfo copy() {
		var clone = new ProcessInfo();
		if (dataSetInfo != null)
			clone.dataSetInfo = dataSetInfo.copy();
		if (quantitativeReference != null)
			clone.quantitativeReference = quantitativeReference.copy();
		if (time != null)
			clone.time = time.copy();
		if (geography != null)
			clone.geography = geography.copy();
		if (technology != null)
			clone.technology = technology.copy();
		if (parameters != null)
			clone.parameters = parameters.copy();
		if (other != null)
			clone.other = other.copy();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}
}
