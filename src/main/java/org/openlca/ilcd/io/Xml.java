package org.openlca.ilcd.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.stream.StreamSource;

import org.openlca.ilcd.commons.XmlRoot;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public final class Xml {

	// JAXBContext is thread-safe and requires quite some resources in order
	// to create; thus, we cache it here as it can have quite some performance
	// impacts when reading and writing large amounts of data sets
	private final static Map<Class<?>, JAXBContext> ctx = new ConcurrentHashMap<>();

	private Xml() {
	}

	public static void write(XmlRoot root, File file) {
		try {
			marshallerOf(root).marshal(root.toElement(), file);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static void write(XmlRoot root, OutputStream stream) {
		try {
			marshallerOf(root).marshal(root.toElement(), stream);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static void write(XmlRoot root, Writer writer) {
		try {
			marshallerOf(root).marshal(root.toElement(), writer);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] toBytes(XmlRoot obj) {
		try (var os = new ByteArrayOutputStream()) {
			write(obj, os);
			return os.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String toString(XmlRoot obj) {
		try (var writer = new StringWriter()) {
			write(obj, writer);
			return writer.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Marshaller marshallerOf(XmlRoot root) throws JAXBException {
		var context = contextOf(root.getClass());
		var marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		return marshaller;
	}

	public static <T> T read(Class<T> clazz, String xml) {
		try (var reader = new StringReader(xml)) {
			return read(clazz, reader);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T read(Class<T> clazz, byte[] xml) {
		try (var stream = new ByteArrayInputStream(xml)) {
			return read(clazz, stream);
		} catch (IOException e) {
			throw new RuntimeException(
				"failed to parse byte array for class " + clazz, e);
		}
	}

	public static <T> T read(Class<T> clazz, File xml) {
		var source = new StreamSource(xml);
		return unmarshal(clazz, source);
	}

	public static <T> T read(Class<T> clazz, InputStream xml) {
		var source = new StreamSource(xml);
		return unmarshal(clazz, source);
	}

	public static <T> T read(Class<T> clazz, Reader xml) {
		var source = new StreamSource(xml);
		return unmarshal(clazz, source);
	}

	private static <T> T unmarshal(Class<T> clazz, StreamSource xml) {
		try {
			var context = contextOf(clazz);
			var unmarshaller = context.createUnmarshaller();
			JAXBElement<T> elem = unmarshaller.unmarshal(xml, clazz);
			return elem.getValue();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	private static JAXBContext contextOf(Class<?> clazz) {
		return ctx.computeIfAbsent(clazz, c -> {
			try {
				return JAXBContext.newInstance(c);
			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
		});
	}

}
