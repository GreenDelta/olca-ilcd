package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EpdContentElement<T extends EpdContentElement<T>>
  implements Copyable<T> {

  @XmlElement(name = "name", namespace = Vocab.EPD_2019)
  private List<LangString> name;

  @XmlElement(name = "weightPerc", namespace = Vocab.EPD_2019)
  private EpdContentAmount weightPerc;

  @XmlElement(name = "mass", namespace = Vocab.EPD_2019)
  private EpdContentAmount mass;

  @XmlElement(name = "comment", namespace=Vocab.EPD_2019)
  private List<LangString> comment;

	// region getters

  public List<LangString> getName() {
    return name != null ? name : List.of();
  }

  public EpdContentAmount getWeightPerc() {
    return weightPerc;
  }

  public EpdContentAmount getMass() {
    return mass;
  }

  public List<LangString> getComment() {
    return comment != null ? comment : List.of();
  }

  // endregion

  // region setters

	@SuppressWarnings("unchecked")
  public T withName(List<LangString> name) {
    this.name = name;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withWeightPerc(EpdContentAmount weightPerc) {
    this.weightPerc = weightPerc;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withMass(EpdContentAmount mass) {
    this.mass = mass;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withComment(List<LangString> comment) {
    this.comment = comment;
    return (T) this;
  }

  public List<LangString> withName() {
    if (name == null) {
      name = new ArrayList<>();
    }
    return name;
  }

  public EpdContentAmount withWeightPerc() {
    if (weightPerc == null) {
      weightPerc = new EpdContentAmount();
    }
    return weightPerc;
  }

  public EpdContentAmount withMass() {
    if (mass == null) {
      mass = new EpdContentAmount();
    }
    return mass;
  }

  public List<LangString> withComment() {
    if (comment == null) {
      comment = new ArrayList<>();
    }
    return comment;
  }

  // endregion

  protected void copyBase(T copy) {
    Val.copy(name, copy::withName);
    Val.copy(weightPerc, copy::withWeightPerc);
    Val.copy(mass, copy::withMass);
    Val.copy(comment, copy::withComment);
  }

}
