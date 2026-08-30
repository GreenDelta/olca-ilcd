package org.openlca.ilcd.contacts;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.Extension;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdContactExtension implements Copyable<EpdContactExtension>, Extension {

	@XmlElementWrapper(name = "entityIds", namespace = Vocab.ENTITY_IDS_2024)
	@XmlElement(name = "entityId", namespace = Vocab.ENTITY_IDS_2024)
	private List<EpdEntityId> entityIds;

	@XmlAnyElement(lax = true)
	private List<Object> any;

	// region getters

	public List<EpdEntityId> getEntityIds() {
		return entityIds != null ? entityIds : List.of();
	}

	@Override
	public List<Object> getAny() {
		return any != null ? any : List.of();
	}

	// endregion

	// region setters

	public EpdContactExtension withEntityIds(List<EpdEntityId> entityIds) {
		this.entityIds = entityIds;
		return this;
	}

	public List<EpdEntityId> withEntityIds() {
		if (entityIds == null) {
			entityIds = new ArrayList<>();
		}
		return entityIds;
	}

	@Override
	public EpdContactExtension withAny(List<Object> any) {
		this.any = any;
		return this;
	}

	@Override
	public List<Object> withAny() {
		if (any == null) {
			any = new ArrayList<>();
		}
		return any;
	}

	// endregion

	@Override
	public EpdContactExtension copy() {
		var copy = new EpdContactExtension();
		Val.copy(entityIds, copy::withEntityIds);
		Val.copyAny(any, copy::withAny);
		return copy;
	}
}
