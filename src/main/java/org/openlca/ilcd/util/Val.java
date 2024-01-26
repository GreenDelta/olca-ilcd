package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Other;

import java.util.List;
import java.util.Map;

public class Val {

	private Val() {
	}

	public static boolean isEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

	public static boolean isEmpty(Map<? , ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean isEmpty(Other other) {
		return other == null || other.any.isEmpty();
	}

}
