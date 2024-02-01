package org.openlca.ilcd;

import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.sources.AdminInfo;
import org.openlca.ilcd.sources.DataSetInfo;
import org.openlca.ilcd.sources.Source;

import java.util.UUID;

public final class SampleSource {

	private SampleSource() {
	}

	public static Source create() {
		Source source = new Source()
			.withAdminInfo(makeAdminInfo());
		source.withSourceInfo()
			.withDataSetInfo(makeDataInfo());
		return source;
	}

	private static DataSetInfo makeDataInfo() {
		var info = new DataSetInfo()
			.withUUID(UUID.randomUUID().toString());
		info.withName().add(LangString.of("test source", "en"));
		return info;
	}

	private static AdminInfo makeAdminInfo() {
		var info = new AdminInfo();
		info.withPublication()
			.withVersion("01.00.101")
			.withUri("http://openlca.org/ilcd/resource/mytestsource");
		return info;
	}

}
