package org.openlca.ilcd;

import org.openlca.ilcd.commons.XmlRoot;
import org.openlca.ilcd.io.Xml;

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class Tests {

	public static <T extends XmlRoot> T read(Class<T> clazz, String resource) {
		var ref = new AtomicReference<T>(null);
		// always make a read-write-read cycle to check
		// everything works in both directions
		withResources(resource, stream -> {
			T t = Xml.read(clazz, stream);
			ref.set(t);
		});
		var bytes = Xml.toBytes(ref.get());
		return Xml.read(clazz, bytes);
	}

	public static void withResources(String resource, Consumer<InputStream> fn) {
		var stream = Tests.class.getResourceAsStream(resource);
		Objects.requireNonNull(stream);
		try (stream) {
			fn.accept(stream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
