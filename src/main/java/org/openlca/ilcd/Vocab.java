package org.openlca.ilcd;

import java.util.Optional;

public interface Vocab {

	String COMMON = "http://lca.jrc.it/ILCD/Common";
	String EPD_2013 = "http://www.iai.kit.edu/EPD/2013";
	String EPD_2019 = "http://www.indata.network/EPD/2019";
	String MATML = "http://www.matml.org/";
	String SBE_ILCD = "http://sbeilcd.se/EPD/2017";
	String OLCA_EPD = "http://openlca.org/epd_ilcd";
	String XML = "http://www.w3.org/XML/1998/namespace";

	/**
	 * Returns a common prefix for the given namespace if such a prefix exists.
	 */
	static Optional<String> prefixOf(String namespace) {
		if (namespace == null)
			return Optional.empty();
		return switch (namespace) {
			case EPD_2013 -> Optional.of("epd");
			case EPD_2019 -> Optional.of("epd2");
			case OLCA_EPD -> Optional.of("olca");
			case SBE_ILCD -> Optional.of("sbe");
			case XML -> Optional.of("xml");
			default -> Optional.empty();
		};
	}

}
