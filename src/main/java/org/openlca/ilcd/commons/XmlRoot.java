package org.openlca.ilcd.commons;

import jakarta.xml.bind.JAXBElement;

/**
 * A common interface for things (datasets, descriptor lists) that can be
 * serialized into a stand-alone XML document.
 */
public interface XmlRoot {

	JAXBElement<? extends XmlRoot> toElement();

}
