package org.openlca.ilcd.util;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.Other;
import org.w3c.dom.Element;

public class Val {

	private Val() {
	}

	public static boolean isEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean isEmpty(Other other) {
		return other == null || other.getAny().isEmpty();
	}

	public static <K, V> void copy(Map<K, V> source, Supplier<Map<K, V>> target) {
		if (source == null || source.isEmpty())
			return;
		target.get().putAll(source);
	}

	public static <T extends Copyable<T>> void copy(
		List<T> source, Supplier<List<T>> target) {
		if (source == null || source.isEmpty())
			return;
		var t = target.get();
		for (var s : source) {
			t.add(s.copy());
		}
	}

	public static <T extends Copyable<T>> void copy(T source, Consumer<T> target) {
		if (source == null)
			return;
		target.accept(source.copy());
	}

	public static void copy(
		XMLGregorianCalendar cal, Consumer<XMLGregorianCalendar> target) {
		if (cal == null)
			return;
		try {
			var copy = (XMLGregorianCalendar) cal.clone();
			target.accept(copy);
		} catch (Exception e) {
			throw new RuntimeException("failed to clone calendar: " + cal, e);
		}
	}

	public static void copyAny(
		List<Object> source, Supplier<List<Object>> target) {
		if (source == null || source.isEmpty())
			return;
		var t = target.get();
		for (var o : source) {
			if (o instanceof Element e) {
				t.add(e.cloneNode(true));
			} else if (o instanceof Copyable<?> cc) {
				t.add(cc.copy());
			}
		}
	}

}
