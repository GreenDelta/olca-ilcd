package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdContentSubstance extends
  EpdInnerContentElement<EpdContentSubstance> {

	@Override
	public EpdContentSubstance copy() {
		var copy = new EpdContentSubstance();
		copyBase(copy);
		return copy;
	}

}
