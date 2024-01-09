package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Ref;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataGeneratorType", propOrder = {
		"dataGenerators"
})
public class DataGenerator {

	@XmlElement(name = "referenceToPersonOrEntityGeneratingTheDataSet", namespace = "http://lca.jrc.it/ILCD/Common", required = true)
	public final List<Ref> dataGenerators = new ArrayList<>();

}
