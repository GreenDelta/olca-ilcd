package org.openlca.ilcd.io;

import org.openlca.ilcd.commons.XmlRoot;

import java.util.Objects;

public class Tests {

	public static <T extends XmlRoot> T read(Class<T> clazz, String resource) {
		var stream = Tests.class.getResourceAsStream(resource);
		Objects.requireNonNull(stream);
		try (stream) {
			// we make a read-write-read check here
			T t = Xml.read(clazz, stream);
			var bytes = Xml.toBytes(t);
			return Xml.read(clazz, bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
