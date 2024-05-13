package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.contacts.AdminInfo;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.contacts.ContactInfo;
import org.openlca.ilcd.contacts.DataSetInfo;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collections;
import java.util.List;

public final class Contacts {

	private Contacts() {
	}

	public static String getUUID(Contact c) {
		var info = getDataSetInfo(c);
		return info != null ? info.getUUID() : null;
	}

	public static void withUUID(Contact c, String uuid) {
		withDataSetInfo(c).withUUID(uuid);
	}

	public static String getVersion(Contact c) {
		var pub = getPublication(c);
		return pub != null ? pub.getVersion() : null;
	}

	public static void withVersion(Contact c, String version) {
		withPublication(c).withVersion(version);
	}

	public static List<LangString> getName(Contact c) {
		var info = getDataSetInfo(c);
		return info != null
			? info.getName()
			: Collections.emptyList();
	}

	public static List<LangString> withName(Contact c) {
		return withDataSetInfo(c).withName();
	}

	public static void withName(Contact c, LangString name) {
		var names = withName(c);
		names.clear();
		if (name != null) {
			names.add(name);
		}
	}

	public static String getUri(Contact c) {
		var pub =	getPublication(c);
		return pub != null ? pub.getUri() : null;
	}

	public static void withUri(Contact c, String uri) {
		withPublication(c).withUri(uri);
	}

	public static XMLGregorianCalendar getTimeStamp(Contact c) {
		var entry = getDataEntry(c);
		return entry != null
			? entry.getTimeStamp()
			: null;
	}

	public static void withTimeStamp(Contact c, XMLGregorianCalendar t) {
		withDataEntry(c).withTimeStamp(t);
	}

	public static List<Classification> getClassifications(Contact c) {
		var info = getDataSetInfo(c);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static List<Classification> withClassifications(Contact c) {
		return withDataSetInfo(c).withClassifications();
	}

	public static ContactInfo getContactInfo(Contact c) {
		return c != null ? c.getContactInfo() : null;
	}

	public static DataSetInfo getDataSetInfo(Contact c) {
		var info = getContactInfo(c);
		return info != null
			? info.getDataSetInfo()
			: null;
	}

	public static DataSetInfo withDataSetInfo(Contact c) {
		return c.withContactInfo().withDataSetInfo();
	}

	public static AdminInfo getAdminInfo(Contact c) {
		return c != null
			? c.getAdminInfo()
			: null;
	}

	public static DataEntry getDataEntry(Contact c) {
		var adminInfo = getAdminInfo(c);
		return adminInfo != null
			? adminInfo.getDataEntry()
			: null;
	}

	public static DataEntry withDataEntry(Contact c) {
		return c.withAdminInfo().withDataEntry();
	}

	public static Publication getPublication(Contact c) {
		var adminInfo = getAdminInfo(c);
		return adminInfo != null
			? adminInfo.getPublication()
			: null;
	}

	public static Publication withPublication(Contact c) {
		return c.withAdminInfo().withPublication();
	}

}
