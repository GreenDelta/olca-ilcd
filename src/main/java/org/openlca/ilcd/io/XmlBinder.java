package org.openlca.ilcd.io;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

/**
 * A helper class for reading and writing ILCD types from / to XML. Uses the
 * standard JAXB mechanisms but in combination with some ILCD specific things.
 * The binder can be used for multiple IO-operations, the marshalers and
 * un-marshalers for the class types are cached.
 */
public class XmlBinder {

	private final HashMap<Class<?>, Marshaller> marshallers = new HashMap<>();
	private final HashMap<Class<?>, Unmarshaller> unmarshallers = new HashMap<>();

	/**
	 * Writes the given ILCD object to a file.
	 */
	public void toFile(Object ilcdObject, File file) throws JAXBException {
		getMarshaller(ilcdObject).marshal(toElement(ilcdObject), file);
	}

	/**
	 * Writes the given ILCD object to a output stream. The stream is flushed
	 * and closed within this method.
	 */
	public void toStream(Object ilcdObject, OutputStream stream)
		throws JAXBException, IOException {
		getMarshaller(ilcdObject).marshal(toElement(ilcdObject), stream);
		stream.flush();
		stream.close();
	}

	/**
	 * Writes the given ILCD object to a writer. The writer is flushed and
	 * closed within this method.
	 */
	public void toWriter(Object ilcdObject, Writer writer)
		throws JAXBException, IOException {
		getMarshaller(ilcdObject).marshal(toElement(ilcdObject), writer);
		writer.flush();
		writer.close();
	}

	public byte[] toBytes(Object obj) {
		try (var os = new ByteArrayOutputStream()) {
			toStream(obj, os);
			return os.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String toString(Object obj) {
		try (var writer = new StringWriter()) {
			toWriter(obj, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Marshaller getMarshaller(Object ilcdObject) throws JAXBException {
		Class<?> clazz = ilcdObject.getClass();
		Marshaller marshaller = marshallers.get(clazz);
		if (marshaller != null)
			return marshaller;
		marshaller = createMarshaller(ilcdObject);
		marshallers.put(clazz, marshaller);
		return marshaller;
	}

	private Marshaller createMarshaller(Object ilcdObject) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ilcdObject.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		return marshaller;
	}

	public <T> T fromBytes(Class<T> clazz, byte[] bytes) {
		try (var stream = new ByteArrayInputStream(bytes)) {
			var source = new StreamSource(stream);
			return unmarshal(clazz, source);
		} catch (Exception e) {
			throw new RuntimeException(
				"failed to parse byte array for class " + clazz, e);
		}
	}

	/**
	 * Reads an ILCD object of the given type from the given file.
	 */
	public <T> T fromFile(Class<T> clazz, File file) throws JAXBException {
		var source = new StreamSource(file);
		return unmarshal(clazz, source);
	}

	/**
	 * Reads an ILCD object of the given type from the given stream. The stream
	 * is closed within this method.
	 */
	public <T> T fromStream(Class<T> clazz, InputStream stream)
		throws JAXBException, IOException {
		StreamSource source = new StreamSource(stream);
		T obj = unmarshal(clazz, source);
		stream.close();
		return obj;
	}

	/**
	 * Reads an ILCD object of the given type from the given reader. The reader
	 * is closed within this method.
	 */
	public <T> T fromReader(Class<T> clazz, Reader reader)
		throws JAXBException, IOException {
		StreamSource source = new StreamSource(reader);
		T obj = unmarshal(clazz, source);
		reader.close();
		return obj;
	}

	private <T> T unmarshal(Class<T> clazz, StreamSource source)
		throws JAXBException {
		Unmarshaller unmarshaller = getUnmarshaller(clazz);
		JAXBElement<T> elem = unmarshaller.unmarshal(source, clazz);
		return elem.getValue();
	}

	private Unmarshaller getUnmarshaller(Class<?> clazz) throws JAXBException {
		Unmarshaller unmarshaller = unmarshallers.get(clazz);
		if (unmarshaller != null)
			return unmarshaller;
		unmarshaller = createUnmarshaller(clazz);
		unmarshallers.put(clazz, unmarshaller);
		return unmarshaller;
	}

	private Unmarshaller createUnmarshaller(Class<?> clazz)
		throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		return context.createUnmarshaller();
	}

	/**
	 * Wraps the given ILCD object into a JAXB element using the respective
	 * object factory method for the given type.
	 */
	public static JAXBElement<?> toElement(Object value) {
		if (value instanceof Process process)
			return process.toElement();
		if (value instanceof Flow flow)
			return flow.toElement();
		if (value instanceof FlowProperty prop)
			return prop.toElement();
		if (value instanceof UnitGroup group)
			return group.toElement();
		if (value instanceof Contact contact)
			return contact.toElement();
		if (value instanceof Source source)
			return source.toElement();
		if (value instanceof ImpactMethod method)
			return method.toElement();
		if (value instanceof DescriptorList list)
			return list.toElement();
		if (value instanceof Model model)
			return model.toElement();
		throw new IllegalArgumentException("Unsupported type " + value);
	}

}
