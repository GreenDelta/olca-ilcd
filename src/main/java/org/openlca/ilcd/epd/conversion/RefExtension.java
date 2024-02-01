package org.openlca.ilcd.epd.conversion;

import java.util.Optional;

import org.openlca.ilcd.commons.DataSetType;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Ref;

/**
 * Creates a data set reference element in an extension point (see the generic
 * product or vendor information in the extended product data sets).
 */
public class RefExtension {

	public static Optional<Ref> readFrom(Other other, String tagName) {
		var elem = Dom.getElement(other, tagName);
		if (elem == null)
			return Optional.empty();
		Ref ref = new Ref()
			.withType(DataSetType.fromValue(elem.getAttribute("type")).orElse(null))
			.withUUID(elem.getAttribute("refObjectId"))
			.withVersion(elem.getAttribute("version"))
			.withUri(elem.getAttribute("uri"));
		var nameElement = Dom.findChild(elem, "shortDescription");
		if (nameElement != null) {
			String lang = nameElement.getAttribute("xml:lang");
			String val = nameElement.getTextContent();
			ref.withName().add(LangString.of(val, lang));
		}
		return Optional.of(ref);
	}

	/**
	 * Stores the given data set reference under the given tag name in the
	 * extension point. If the data set reference is null, it just drops a
	 * possible old data set reference from that extension.
	 */
	public static void writeTo(Other extension, String tag, Ref ref) {
		if (tag == null || extension == null)
			return;
		Dom.clear(extension, tag);
		if (ref == null)
			return;

		var doc = Dom.createDocument();
		if (doc == null)
			return;
		var elem = doc.createElementNS(Vocab.NS_EPD, "epd:" + tag);
		elem.setAttribute("refObjectId", ref.getUUID());
		if (ref.getType() != null) {
			elem.setAttribute("type", ref.getType().value());
			elem.setAttribute("uri", "../" + folder(ref.getType()) + "/" + ref.getUUID());
		}
		for (var name : ref.getName()) {
			var nameElem = doc.createElementNS(
				"http://lca.jrc.it/ILCD/Common",
				"common:shortDescription");
			elem.appendChild(nameElem);
			elem.setAttribute("xml:lang", name.lang);
			nameElem.setTextContent(name.value);
		}
		extension.withAny().add(elem);
	}

	private static String folder(DataSetType type) {
		if (type == null)
			return "unknown";
		return switch (type) {
			case CONTACT -> "contacts";
			case SOURCE -> "sources";
			case FLOW -> "flows";
			case IMPACT_METHOD -> "lciamethods";
			case PROCESS -> "processes";
			case FLOW_PROPERTY -> "flowproperties";
			case UNIT_GROUP -> "unitgroups";
			case MODEL -> "models";
			case EXTERNAL_FILE -> "external_docs";
		};
	}
}
