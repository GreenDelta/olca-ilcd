package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Classification;
import org.openlca.ilcd.commons.DataEntry;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.sources.AdminInfo;
import org.openlca.ilcd.sources.DataSetInfo;
import org.openlca.ilcd.sources.FileRef;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.sources.SourceInfo;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public final class Sources {

	private Sources() {
	}

	public static String getUUID(Source s) {
		var info = getDataSetInfo(s);
		return info != null
			? info.getUUID()
			: null;
	}

	public static void withUUID(Source s, String uuid) {
		withDataSetInfo(s).withUUID(uuid);
	}

	public static String getVersion(Source s) {
		var pub = getPublication(s);
		return pub != null ? pub.getVersion() : null;
	}

	public static void withVersion(Source s, String version) {
		withPublication(s).withVersion(version);
	}

	public static List<LangString> getName(Source s) {
		var info = getDataSetInfo(s);
		return info != null
			? info.getName()
			: Collections.emptyList();
	}

	public static List<LangString> withName(Source s) {
		return withDataSetInfo(s).withName();
	}

	public static void withName(Source s, LangString name) {
		var names = withName(s);
		names.clear();
		if (name != null) {
			names.add(name);
		}
	}

	public static String getUri(Source s) {
		var pub =	getPublication(s);
		return pub != null ? pub.getUri() : null;
	}

	public static void withUri(Source s, String uri) {
		withPublication(s).withUri(uri);
	}

	public static XMLGregorianCalendar getTimeStamp(Source s) {
		var entry = getDataEntry(s);
		return entry != null
			? entry.getTimeStamp()
			: null;
	}

	public static void withTimeStamp(Source s, XMLGregorianCalendar t) {
		withDataEntry(s).withTimeStamp(t);
	}

	public static List<Classification> getClassifications(Source s) {
		var info = getDataSetInfo(s);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
	}

	public static List<Classification> withClassifications(Source s) {
		return withDataSetInfo(s).withClassifications();
	}

	public static SourceInfo getSourceInfo(Source s) {
		return s != null
			? s.getSourceInfo()
			: null;
	}

	public static DataSetInfo getDataSetInfo(Source s) {
		var info = getSourceInfo(s);
		return info != null
			? info.getDataSetInfo()
			: null;
	}

	public static DataSetInfo withDataSetInfo(Source s) {
		return s.withSourceInfo().withDataSetInfo();
	}

	public static AdminInfo getAdminInfo(Source s) {
		return s != null
			? s.getAdminInfo()
			: null;
	}

	public static DataEntry getDataEntry(Source s) {
		var info = getAdminInfo(s);
		return info != null
			? info.getDataEntry()
			: null;
	}

	public static DataEntry withDataEntry(Source s) {
		return s.withAdminInfo().withDataEntry();
	}

	public static List<FileRef> getFileRefs(Source s) {
		var info = getDataSetInfo(s);
		return info != null
			? info.getFiles()
			: Collections.emptyList();
	}

	public static Publication getPublication(Source s) {
		var info = getAdminInfo(s);
		return info != null
			? info.getPublication()
			: null;
	}

	public static Publication withPublication(Source s) {
		return s.withAdminInfo().withPublication();
	}

	/**
	 * Returns the plain file name of the given file reference (the unescaped
	 * last part of the URI in the given file reference).
	 */
	public static String getFileName(FileRef ref) {
		if (ref == null || ref.getUri() == null)
			return null;
		try {
			String s = ref.getUri().trim().replace('\\', '/');
			if (s.isEmpty())
				return null;
			int pos = s.lastIndexOf('/');
			if (pos != -1) {
				s = s.substring(pos + 1);
			}
			return URLDecoder.decode(s, StandardCharsets.UTF_8);
		} catch (Exception e) {
			var log = LoggerFactory.getLogger(Sources.class);
			log.error("could not get file name from " + ref.getUri(), e);
			return null;
		}
	}

}
