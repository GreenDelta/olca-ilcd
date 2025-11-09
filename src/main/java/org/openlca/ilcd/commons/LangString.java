package org.openlca.ilcd.commons;

import java.util.List;
import java.util.Objects;

import org.openlca.commons.Copyable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public final class LangString implements Copyable<LangString> {

	@XmlValue
	private final String value;

	@XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
	private final String lang;

	/**
	 * This private constructor is required so that JAXB can call it, see
	 * <a href="https://stackoverflow.com/a/14820037">https://stackoverflow.com/a/14820037</a>
	 */
	@SuppressWarnings("unused")
	private LangString() {
		this(null, null);
	}

	public LangString(String value, String lang) {
		this.value = value;
		this.lang = lang;
	}

	public static LangString of(String value, String lang) {
		return new LangString(value, lang);
	}

	public static LangString of(String value) {
		return new LangString(value, "en");
	}

	public String getLang() {
		return lang;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		String v = value != null ? value : "";
		if (lang == null)
			return v;
		return v + "@" + lang;
	}

	@Override
	public LangString copy() {
		return LangString.of(value, lang);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LangString other))
			return false;
		return Objects.equals(value, other.value)
			&& Objects.equals(lang, other.lang);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, lang);
	}

	/**
	 * Returns the value for the given language code from the given
	 * multi-language strings. Returns {@code null} if there is no
	 * value for that language available.
	 */
	public static String get(List<LangString> list, String lang) {
		var s = getEntry(list, lang);
		return s == null ? null : s.value;
	}

	/**
	 * Returns the default value from the given list of multi-language strings.
	 * The default value is English (language code "en") if available, otherwise
	 * the first other value. Returns {@code null} if there is no value
	 * available.
	 */
	public static String getDefault(List<LangString> list) {
		if (list == null || list.isEmpty())
			return null;
		var s = get(list, "en");
		if (s != null)
			return s;
		for (var i : list) {
			if (i.value != null)
				return i.value;
		}
		return null;
	}

	/**
	 * Get the value for the given language code from the list of
	 * multi-language strings. Returns the default value if there
	 * is no value for the given language available and {@code null}
	 * if there is even no default value in the list.
	 */
	public static String getOrDefault(List<LangString> list, String lang) {
		if (list == null || list.isEmpty())
			return null;
		var s = get(list, lang);
		return s != null ? s : getDefault(list);
	}


	private static LangString getEntry(List<LangString> list, String lang) {
		if (list == null)
			return null;
		for (LangString s : list) {
			if (equal(s.lang, lang))
				return s;
		}
		return null;
	}

	/**
	 * If there is already a language string with the given language code in the
	 * list update this language string, otherwise create a new language string
	 * and add it to the list.
	 */
	public static void set(List<LangString> list, String value, String lang) {
		if (list == null)
			return;
		list.removeIf(s -> equal(s.lang, lang));
		list.add(LangString.of(value, lang));
	}

	public static boolean remove(List<LangString> list, String lang) {
		var e = getEntry(list, lang);
		if (e != null) {
			return list.remove(e);
		}
		return false;
	}

	private static boolean equal(String s1, String s2) {
		if (empty(s1) && empty(s2))
			return true;
		if (empty(s1) || empty(s2))
			return false;
		return s1.trim().equalsIgnoreCase(s2.trim());
	}

	private static boolean empty(String s) {
		if (s == null)
			return true;
		return s.trim().isEmpty();
	}

}
