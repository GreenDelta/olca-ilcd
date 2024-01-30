package org.openlca.ilcd.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class GroupRef implements Copyable<GroupRef> {

	@XmlAttribute(name = "groupId")
	private int groupID;

	// region getters

	public int getGroupID() {
		return groupID;
	}

	// endregion

	// region setters

	public GroupRef withGroupID(int groupID) {
		this.groupID = groupID;
		return this;
	}

	// endregion

	@Override
	public GroupRef copy() {
		var copy = new GroupRef();
		copy.withGroupID(groupID);
		return copy;
	}

}
