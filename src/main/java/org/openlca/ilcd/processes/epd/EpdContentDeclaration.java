package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdContentDeclaration implements Copyable<EpdContentDeclaration> {

	@Override
	public EpdContentDeclaration copy() {
		return new EpdContentDeclaration();
	}
}
