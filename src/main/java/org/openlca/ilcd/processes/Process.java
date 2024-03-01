package org.openlca.ilcd.processes;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.util.Val;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessDataSetType", propOrder = {
	"processInfo",
	"modelling",
	"adminInfo",
	"exchanges",
	"impactResults",
	"other"
})
public class Process implements IDataSet, Copyable<Process> {

	@XmlElement(required = true, name = "processInformation")
	private ProcessInfo processInfo;

	@XmlElement(name = "modellingAndValidation")
	private Modelling modelling;

	@XmlElement(name = "administrativeInformation")
	private AdminInfo adminInfo;

	@XmlElementWrapper(name = "exchanges")
	@XmlElement(name = "exchange")
	private List<Exchange> exchanges;

	@XmlElementWrapper(name = "LCIAResults")
	@XmlElement(name = "LCIAResult")
	private List<ImpactResult> impactResults;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAttribute(name = "version", required = true)
	private String schemaVersion = SCHEMA_VERSION;

	@XmlAttribute(name = "locations")
	private String locations;

	@XmlAttribute(name = "metaDataOnly")
	private Boolean metaDataOnly;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	// region getters

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}

	public Modelling getModelling() {
		return modelling;
	}

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public List<Exchange> getExchanges() {
		return exchanges != null ? exchanges : Collections.emptyList();
	}

	public List<ImpactResult> getImpactResults() {
		return impactResults != null ? impactResults : Collections.emptyList();
	}

	public Other getOther() {
		return other;
	}

	@Override
	public String getSchemaVersion() {
		return schemaVersion;
	}

	public String getLocations() {
		return locations;
	}

	public Boolean getMetaDataOnly() {
		return metaDataOnly;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Process withProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
		return this;
	}

	public Process withModelling(Modelling modelling) {
		this.modelling = modelling;
		return this;
	}

	public Process withAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
		return this;
	}

	public Process withExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
		return this;
	}

	public Process withImpactResults(List<ImpactResult> impactResults) {
		this.impactResults = impactResults;
		return this;
	}

	public Process withOther(Other other) {
		this.other = other;
		return this;
	}

	public Process withSchemaVersion(String version) {
		this.schemaVersion = version;
		return this;
	}

	public Process withLocations(String locations) {
		this.locations = locations;
		return this;
	}

	public Process withMetaDataOnly(Boolean metaDataOnly) {
		this.metaDataOnly = metaDataOnly;
		return this;
	}

	public Process withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public ProcessInfo withProcessInfo() {
		if (processInfo == null) {
			processInfo = new ProcessInfo();
		}
		return processInfo;
	}

	public Modelling withModelling() {
		if (modelling == null) {
			modelling = new Modelling();
		}
		return modelling;
	}

	public AdminInfo withAdminInfo() {
		if (adminInfo == null) {
			adminInfo = new AdminInfo();
		}
		return adminInfo;
	}

	public List<Exchange> withExchanges() {
		if (exchanges == null) {
			exchanges = new ArrayList<>();
		}
		return exchanges;
	}

	public List<ImpactResult> withImpactResults() {
		if (impactResults == null) {
			impactResults = new ArrayList<>();
		}
		return impactResults;
	}

	public Other withOther() {
		if (other == null) {
			other = new Other();
		}
		return other;
	}

	public Map<QName, String> withOtherAttributes() {
		if (otherAttributes == null) {
			otherAttributes = new HashMap<>();
		}
		return otherAttributes;
	}

	// endregion

	@Override
	public Process copy() {
		var copy = new Process();
		Val.copy(processInfo, copy::withProcessInfo);
		Val.copy(modelling, copy::withModelling);
		Val.copy(adminInfo, copy::withAdminInfo);
		Val.copy(exchanges, copy::withExchanges);
		Val.copy(impactResults, copy::withImpactResults);
		Val.copy(other, copy::withOther);
		copy.withSchemaVersion(schemaVersion);
		copy.withLocations(locations);
		copy.withMetaDataOnly(metaDataOnly);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	@Override
	public JAXBElement<Process> toElement() {
		var qname = new QName(
			"http://lca.jrc.it/ILCD/Process", "processDataSet");
		return new JAXBElement<>(qname, Process.class, null, this);
	}
}
