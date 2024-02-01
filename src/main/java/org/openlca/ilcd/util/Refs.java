package org.openlca.ilcd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Ref;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Refs {

	private Refs() {
	}

	/**
	 * Returns the data set reference of the ILCD format.
	 */
	public static Ref ilcd() {
		Ref ref = new Ref()
			.withType(DataSetType.SOURCE)
			.withUri("../sources/a97a0155-0234-4b87-b4ce-a45da52f2a40_01.01.000.xml")
			.withUUID("a97a0155-0234-4b87-b4ce-a45da52f2a40")
			.withVersion("01.01.000");
		ref.withName()
			.add(LangString.of("ILCD format", "en"));
		return ref;
	}

	public static Ref fetch(File file) {
		if (file == null || !file.exists())
			return null;
		try (FileInputStream is = new FileInputStream(file)) {
			return fetch(is);
		} catch (Exception e) {
			Logger log = LoggerFactory.getLogger(Refs.class);
			log.error("Failed to read ref from " + file, e);
			return null;
		}
	}

	public static Ref fetch(InputStream is) {
		try {
			return new RefFetch().fetch(is);
		} catch (Exception e) {
			Logger log = LoggerFactory.getLogger(Refs.class);
			log.error("Failed to read ref", e);
			return null;
		}
	}

	private static class RefFetch {

		Ref ref;

		XMLStreamReader reader;
		StringBuilder sb;
		String lang;

		Ref fetch(InputStream is) throws Exception {
			ref = new Ref();
			reader = XMLInputFactory.newFactory()
				.createXMLStreamReader(is);
			boolean root = true;
			boolean stop = false;
			while (reader.hasNext()) {
				reader.next();
				switch (reader.getEventType()) {
					case XMLStreamConstants.START_ELEMENT:
						if (root) {
							root = false;
							ref.withType(getType());
						} else {
							start();
						}
						break;
					case XMLStreamConstants.CHARACTERS:
						text();
						break;
					case XMLStreamConstants.END_ELEMENT:
						stop = end();
						break;
				}
				if (stop)
					break;
			}
			return ref;
		}

		DataSetType getType() {
			String element = reader.getLocalName();
			if (element == null)
				return null;
			return switch (element) {
				case "LCIAMethodDataSet" -> DataSetType.IMPACT_METHOD;
				case "processDataSet" -> DataSetType.PROCESS;
				case "contactDataSet" -> DataSetType.CONTACT;
				case "sourceDataSet" -> DataSetType.SOURCE;
				case "flowDataSet" -> DataSetType.FLOW;
				case "flowPropertyDataSet" -> DataSetType.FLOW_PROPERTY;
				case "unitGroupDataSet" -> DataSetType.UNIT_GROUP;
				default -> null;
			};
		}

		void start() {
			String element = reader.getLocalName();
			if (element == null)
				return;
			switch (element) {
				case "UUID":
				case "dataSetVersion":
				case "permanentDataSetURI":
					sb = new StringBuilder();
					return;
			}
			if (matchName(element)) {
				lang = reader.getAttributeValue(
					"http://www.w3.org/XML/1998/namespace", "lang");
				sb = new StringBuilder();
			}
		}

		void text() {
			if (sb == null)
				return;
			int pos = reader.getTextStart();
			int len = reader.getTextLength();
			sb.append(reader.getTextCharacters(), pos, len);
		}

		/**
		 * Handles an element-ends-event and returns true if we can stop parsing
		 * the document.
		 */
		boolean end() {
			if (sb == null)
				return false;
			String t = sb.toString().trim();
			sb = null;
			String element = reader.getLocalName();
			if (element == null)
				return false;
			switch (element) {
				case "UUID":
					ref.withUUID(t);
					return false;
				case "dataSetVersion":
					ref.withVersion(t);
					return false;
				case "permanentDataSetURI":
					ref.withUri(t);
					return true;
			}
			if (matchName(element)) {
				ref.withName().add(LangString.of(t, lang));
			}
			return false;
		}

		private boolean matchName(String name) {
			var type = ref.getType();
			return switch (name) {
				case "name" -> type == DataSetType.CONTACT
					|| type == DataSetType.FLOW_PROPERTY
					|| type == DataSetType.UNIT_GROUP
					|| type == DataSetType.IMPACT_METHOD;
				case "baseName" -> type == DataSetType.FLOW
					|| type == DataSetType.PROCESS;
				case "shortName" -> type == DataSetType.SOURCE;
				default -> false;
			};
		}
	}
}
