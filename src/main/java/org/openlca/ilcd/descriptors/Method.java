
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.processes.ReviewMethod;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "method")
public class Method implements Copyable<Method> {

	@XmlAttribute(name = "name", required = true)
	private ReviewMethod name;

	// region getters

	public ReviewMethod getName() {
		return name;
	}

	// endregion

	// region setters

	public Method withName(ReviewMethod name) {
		this.name = name;
		return this;
	}

	// endregion

	@Override
	public Method copy() {
		var copy = new Method();
		copy.withName(name);
		return copy;
	}

}
