package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class LangString2 implements Copyable<LangString2> {

	public static final String DEFAULT_LANG = "en";

	private final Map<String, Entry> entries;

	private static final LangString2 _empty = new LangString2(Map.of());

	private LangString2(Map<String, Entry> entries) {
		this.entries = entries;
	}

	public LangString2() {
		this.entries = new HashMap<>(1);
	}

	/**
	 * Returns an empty language string. Note that the returned object is
	 * immutable.
	 */
	public static LangString2 empty() {
		return _empty;
	}

	public boolean isEmpty() {
		return entries.isEmpty();
	}

	/**
	 * Returns a view on the entries of this language string. Note that the
	 * returned collection is immutable.
	 */
	public Collection<Entry> getEntries() {
		return Collections.unmodifiableCollection(entries.values());
	}

	/**
	 * Returns the language codes for which values are available.
	 */
	public Set<String> getLangs() {
		var s = new HashSet<String>(entries.size());
		for (var e : entries.values()) {
			if (e.lang != null && e.value != null) {
				s.add(e.lang);
			}
		}
		return s;
	}

	public String getValue(String lang) {
		var key = lang != null ? lang : DEFAULT_LANG;
		var e = entries.get(key);
		return e != null ? e.value : null;
	}

	public String getDefault() {
		return getValue(DEFAULT_LANG);
	}

	public String getOrDefault(String lang) {
		var v = getValue(lang);
		return v != null ? v : getDefault();
	}

	public String getOrAny(String lang) {
		var v = getOrDefault(lang);
		if (v != null)
			return v;
		for (var e : entries.values()) {
			if (e.value != null)
				return e.value;
		}
		return null;
	}

	public LangString2 put(String lang, String value) {
		var key = lang != null ? lang : DEFAULT_LANG;
		if (value == null) {
			entries.remove(key);
		} else {
			entries.put(key, Entry.of(key, lang));
		}
		return this;
	}

	public LangString2 put(Entry e) {
		if (e == null)
			return this;
		entries.put(e.lang, e);
		return this;
	}

	public LangString2 putDefault(String value) {
		return put(DEFAULT_LANG, value);
	}

	@Override
	public LangString2 copy() {
		var copy = new LangString2();
		copy.entries.putAll(entries);
		return copy;
	}

	public static void copy(LangString2 source, Supplier<LangString2> target) {
		if (source == null || source.isEmpty())
			return;
		var t = target.get();
		if (t == null)
			return;
		t.entries.putAll(source.entries);
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Entry {

		@XmlValue
		private final String value;

		@XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
		private final String lang;

		/**
		 * This private constructor is required so that JAXB can call it, see
		 * <a href="https://stackoverflow.com/a/14820037">https://stackoverflow.com/a/14820037</a>
		 */
		@SuppressWarnings("unused")
		private Entry() {
			this(null, null);
		}

		public Entry(String value, String lang) {
			this.value = value;
			this.lang = lang;
		}

		public static Entry of(String value, String lang) {
			return new Entry(value, lang);
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
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Entry other))
				return false;
			return Objects.equals(value, other.value)
				&& Objects.equals(lang, other.lang);
		}

		@Override
		public int hashCode() {
			return Objects.hash(value, lang);
		}

	}

}
