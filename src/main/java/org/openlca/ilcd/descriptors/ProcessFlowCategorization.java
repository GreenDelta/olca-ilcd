
package org.openlca.ilcd.descriptors;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"category"
})
public class ProcessFlowCategorization {

	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI", required = true)
	public final List<Category> category = new ArrayList<>();

}
