package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.annotations.ShortText;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ref describes an ILCD data set reference (GlobalReferenceType).
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlobalReferenceType", propOrder = { "name" })
public class Ref implements Copyable<Ref> {

	@ShortText
	@XmlElement(name = "shortDescription")
	public final List<LangString> name = new ArrayList<>();

	@XmlAttribute(name = "type", required = true)
	public DataSetType type;

	@XmlAttribute(name = "refObjectId")
	public String uuid;

	@XmlAttribute(name = "version")
	public String version;

	@XmlAttribute(name = "uri")
	@XmlSchemaType(name = "anyURI")
	public String uri;

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
		if (!(obj instanceof Ref other))
			return false;
		return Objects.equals(this.type, other.type)
				&& Objects.equals(this.uuid, other.uuid);
	}

	@Override
	public Ref copy() {
		Ref clone = new Ref();
		LangString.copy(name, clone.name);
		clone.type = type;
		clone.uuid = uuid;
		clone.version = version;
		clone.uri = uri;
		return clone;
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
			default -> null;
		};
	}

	public static Ref of(IDataSet dataSet) {
		if (dataSet == null)
			return new Ref();
		Ref ref = new Ref();
		ref.uri = dataSet.getURI();
		ref.uuid = dataSet.getUUID();
		ref.type = dataSet.getDataSetType();
		ref.version = dataSet.getVersion();
		LangString.copy(dataSet.getName(), ref.name);
		return ref;
	}
}
