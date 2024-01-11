package org.openlca.ilcd.io;

import java.util.Objects;

public class Tests {

	public static <T> T read(Class<T> clazz, String resource) {
		var stream = Tests.class.getResourceAsStream(resource);
		Objects.requireNonNull(stream);
		try (stream) {
			var xml = new XmlBinder();
			T t = xml.fromStream(clazz, stream);
			var bytes = xml.toBytes(t);
			t = xml.fromBytes(clazz, bytes);
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
