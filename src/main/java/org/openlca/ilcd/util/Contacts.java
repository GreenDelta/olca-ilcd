package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.contacts.AdminInfo;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.contacts.ContactInfo;
import org.openlca.ilcd.contacts.DataSetInfo;

public final class Contacts {

	private Contacts() {
	}

	public static ContactInfo getContactInfo(Contact c) {
		return c != null
			? c.contactInfo
			: null;
	}

	public static ContactInfo forceContactInfo(Contact c) {
		if (c.contactInfo == null) {
			c.contactInfo = new ContactInfo();
		}
		return c.contactInfo;
	}

	public static DataSetInfo getDataSetInfo(Contact c) {
		var info = getContactInfo(c);
		return info != null
			? info.dataSetInfo
			: null;
	}

	public static DataSetInfo forceDataSetInfo(Contact c) {
		var info = forceContactInfo(c);
		if (info.dataSetInfo == null) {
			info.dataSetInfo = new DataSetInfo();
		}
		return info.dataSetInfo;
	}

	public static AdminInfo getAdminInfo(Contact c) {
		return c != null
			? c.adminInfo
			: null;
	}

	public static AdminInfo forceAdminInfo(Contact c) {
		if (c.adminInfo == null) {
			c.adminInfo = new AdminInfo();
		}
		return c.adminInfo;
	}

	public static DataEntry getDataEntry(Contact c) {
		var adminInfo = getAdminInfo(c);
		return adminInfo != null
			? adminInfo.dataEntry
			: null;
	}

	public static DataEntry forceDataEntry(Contact c) {
		var adminInfo = forceAdminInfo(c);
		if (adminInfo.dataEntry == null) {
			adminInfo.dataEntry = new DataEntry();
		}
		return adminInfo.dataEntry;
	}

	public static Publication getPublication(Contact c) {
		var adminInfo = getAdminInfo(c);
		return adminInfo != null
			? adminInfo.publication
			: null;
	}

	public static Publication forcePublication(Contact c) {
		var adminInfo = forceAdminInfo(c);
		if (adminInfo.publication == null) {
			adminInfo.publication = new Publication();
		}
		return adminInfo.publication;
	}

}
