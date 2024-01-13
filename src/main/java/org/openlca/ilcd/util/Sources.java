package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.DataEntry;
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

	public static SourceInfo getSourceInfo(Source s) {
		return s != null
			? s.sourceInfo
			: null;
	}

	public static SourceInfo forceSourceInfo(Source s) {
		if (s.sourceInfo == null) {
			s.sourceInfo = new SourceInfo();
		}
		return s.sourceInfo;
	}

	public static DataSetInfo getDataSetInfo(Source s) {
		var info = getSourceInfo(s);
		return info != null
			? info.dataSetInfo
			: null;
	}

	public static DataSetInfo forceDataSetInfo(Source s) {
		var info = forceSourceInfo(s);
		if (info.dataSetInfo == null) {
			info.dataSetInfo = new DataSetInfo();
		}
		return info.dataSetInfo;
	}

	public static AdminInfo getAdminInfo(Source s) {
		return s != null
			? s.adminInfo
			: null;
	}

	public static AdminInfo forceAdminInfo(Source s) {
		if (s.adminInfo == null) {
			s.adminInfo = new AdminInfo();
		}
		return s.adminInfo;
	}

	public static DataEntry getDataEntry(Source s) {
		var info = getAdminInfo(s);
		return info != null
			? info.dataEntry
			: null;
	}

	public static DataEntry forceDataEntry(Source s) {
		var info = forceAdminInfo(s);
		if (info.dataEntry == null) {
			info.dataEntry = new DataEntry();
		}
		return info.dataEntry;
	}

	public static List<FileRef> getFileRefs(Source s) {
		if (s == null || s.sourceInfo == null)
			return Collections.emptyList();
		return s.sourceInfo.dataSetInfo == null
			? Collections.emptyList()
			: s.sourceInfo.dataSetInfo.files;
	}

	public static Publication getPublication(Source s) {
		var info = getAdminInfo(s);
		return info != null
			? info.publication
			: null;
	}

	public static Publication forcePublication(Source s) {
		var info = forceAdminInfo(s);
		if (info.publication == null) {
			info.publication = new Publication();
		}
		return info.publication;
	}

	/**
	 * Returns the plain file name of the given file reference (the unescaped
	 * last part of the URI in the given file reference).
	 */
	public static String getFileName(FileRef ref) {
		if (ref == null || ref.uri == null)
			return null;
		try {
			String s = ref.uri.trim().replace('\\', '/');
			if (s.isEmpty())
				return null;
			int pos = s.lastIndexOf('/');
			if (pos != -1) {
				s = s.substring(pos + 1);
			}
			return URLDecoder.decode(s, StandardCharsets.UTF_8);
		} catch (Exception e) {
			var log = LoggerFactory.getLogger(Sources.class);
			log.error("could not get file name from " + ref.uri, e);
			return null;
		}
	}

}
