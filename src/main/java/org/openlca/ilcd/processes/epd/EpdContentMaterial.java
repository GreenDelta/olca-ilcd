package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdContentMaterial extends
  EpdInnerContentElement<EpdContentMaterial> {

	@XmlElement(name = "substance", namespace = Vocab.EPD_2019)
	private List<EpdContentSubstance> substances;

	  // region getters

  public List<EpdContentSubstance> getSubstances() {
    return substances != null ? substances : List.of();
  }

  // endregion

  // region setters

  public EpdContentMaterial withSubstances(List<EpdContentSubstance> substances) {
    this.substances = substances;
    return this;
  }

  public List<EpdContentSubstance> withSubstances() {
    if (substances == null) {
      substances = new ArrayList<>();
    }
    return substances;
  }

  // endregion

  @Override
  public EpdContentMaterial copy() {
    var copy = new EpdContentMaterial();
		copyBase(copy);
    Val.copy(substances, copy::withSubstances);
    return copy;
  }

}
