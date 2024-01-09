
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class ClassType {

	@XmlValue
	public String content;

	@XmlAttribute(name = "level", required = true)
	public int level;

}
