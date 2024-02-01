package org.openlca.ilcd.util;

import java.util.Map;
import java.util.function.Supplier;

import javax.xml.namespace.QName;

class Extensions {

	public static final String NAMESPACE = "http://openlca.org/ilcd-extensions";

	private Extensions() {
	}

	public static QName getQName(String attribute) {
		return new QName(NAMESPACE, attribute, "olca");
	}

	public static String getString(Map<QName, String> atts,
		String attribute) {
		if (atts == null || atts.isEmpty() || attribute == null)
			return null;
		QName qName = getQName(attribute);
		return atts.get(qName);
	}

	public static void setString(
		Supplier<Map<QName, String>> ext, String attribute, String value) {
		if (attribute == null || value == null)
			return;
		ext.get().put(getQName(attribute), value);
	}

	public static void setDouble(
		Supplier<Map<QName, String>> ext, String attribute, double value) {
		String str = Double.toString(value);
		setString(ext, attribute, str);
	}

	public static Double getDouble(Map<QName, String> atts,
		String attribute) {
		String str = getString(atts, attribute);
		if (str == null)
			return null;
		return Double.valueOf(str);
	}

	public static void setBoolean(
		Supplier<Map<QName, String>> ext, String attribute, boolean value) {
		String str = Boolean.toString(value);
		setString(ext, attribute, str);
	}

	public static boolean getBoolean(
		Map<QName, String> atts, String attribute) {
		String str = getString(atts, attribute);
		if (str == null)
			return false;
		return Boolean.parseBoolean(str);
	}

}
