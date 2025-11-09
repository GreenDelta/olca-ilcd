package org.openlca.ilcd.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.namespace.QName;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.annotations.ShortText;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.DataSets;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Ref describes an ILCD data set reference (GlobalReferenceType).
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlobalReferenceType", propOrder = {"name", "other"})
public class Ref implements Copyable<Ref> {

	@ShortText
	@XmlElement(name = "shortDescription")
	private List<LangString> name;

	@XmlAttribute(name = "type", required = true)
	private DataSetType type;

	@XmlAttribute(name = "refObjectId")
	private String uuid;

	@XmlAttribute(name = "version")
	private String version;

	@XmlAttribute(name = "uri")
	@XmlSchemaType(name = "anyURI")
	private String uri;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	private Other other;

	@XmlAnyAttribute
	private Map<QName, String> otherAttributes;

	public static Ref of(IDataSet ds) {
		var ref = new Ref()
			.withType(DataSets.getType(ds))
			.withUUID(DataSets.getUUID(ds))
			.withVersion(DataSets.getVersion(ds))
			.withUri(DataSets.getUri(ds));
		var names = DataSets.getBaseName(ds);
		if (!names.isEmpty()) {
			ref.withName().addAll(names);
		}
		return ref;
	}

	// region getters

	public List<LangString> getName() {
		return name != null ? name : Collections.emptyList();
	}

	public DataSetType getType() {
		return type;
	}

	public String getUUID() {
		return uuid;
	}

	public String getVersion() {
		return version;
	}

	public String getUri() {
		return uri;
	}

	public Other getOther() {
		return other;
	}

	public Map<QName, String> getOtherAttributes() {
		return otherAttributes != null ? otherAttributes : Collections.emptyMap();
	}

	// endregion

	// region setters

	public Ref withName(List<LangString> name) {
		this.name = name;
		return this;
	}

	public Ref withType(DataSetType type) {
		this.type = type;
		return this;
	}

	public Ref withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public Ref withVersion(String version) {
		this.version = version;
		return this;
	}

	public Ref withUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Ref withOther(Other other) {
		this.other = other;
		return this;
	}

	public Ref withOtherAttributes(Map<QName, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
		return this;
	}

	public List<LangString> withName() {
		if (name == null) {
			name = new ArrayList<>();
		}
		return name;
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
	public String toString() {
		return "Ref [type=" + type + ", uuid=" + uuid + "]";
	}

	public boolean isValid() {
		return uuid != null && type != null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Ref otherRef))
			return false;
		return Objects.equals(this.type, otherRef.type)
			&& Objects.equals(this.uuid, otherRef.uuid);
	}

	@Override
	public Ref copy() {
		var copy = new Ref()
			.withType(type)
			.withUUID(uuid)
			.withVersion(version)
			.withUri(uri);
		Val.copy(name, copy::withName);
		Val.copy(other, copy::withOther);
		Val.copy(otherAttributes, copy::withOtherAttributes);
		return copy;
	}

	/**
	 * Copies all data set references from the given source list to the given
	 * target list.
	 */
	public static void copy(List<Ref> source, List<Ref> target) {
		if (source == null || target == null)
			return;
		for (Ref ref : source) {
			if (ref == null)
				continue;
			target.add(ref.copy());
		}
	}

	public static Ref[] copy(Ref[] refs) {
		if (refs == null)
			return null;
		Ref[] copy = new Ref[refs.length];
		for (int i = 0; i < refs.length; i++) {
			if (refs[i] == null)
				continue;
			copy[i] = refs[i].copy();
		}
		return copy;
	}

	public Class<? extends IDataSet> getDataSetClass() {
		if (type == null)
			return null;
		return switch (type) {
			case CONTACT -> Contact.class;
			case SOURCE -> Source.class;
			case UNIT_GROUP -> UnitGroup.class;
			case FLOW_PROPERTY -> FlowProperty.class;
			case FLOW -> Flow.class;
			case PROCESS -> Process.class;
			case IMPACT_METHOD -> ImpactMethod.class;
			case MODEL -> Model.class;
			case EXTERNAL_FILE -> null;
		};
	}
}
