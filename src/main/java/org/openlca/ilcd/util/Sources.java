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

	public static String getVersion(Source s) {
		var pub = getPublication(s);
		return pub != null ? pub.getVersion() : null;
	}

	public static List<LangString> getName(Source s) {
		var info = getDataSetInfo(s);
		return info != null
			? info.getName()
			: Collections.emptyList();
	}

	public static String getUri(Source s) {
		var pub =	getPublication(s);
		return pub != null ? pub.getUri() : null;
	}

	public static List<Classification> getClassifications(Source s) {
		var info = getDataSetInfo(s);
		return info != null
			? info.getClassifications()
			: Collections.emptyList();
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
