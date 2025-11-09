package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import org.openlca.ilcd.Vocab;
import org.openlca.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
public class EpdContentAmount implements Copyable<EpdContentAmount> {

	@XmlAttribute(name = "value", namespace = Vocab.EPD_2019)
	private Double value;

	@XmlAttribute(name = "lowerValue", namespace = Vocab.EPD_2019)
	private Double min;

	@XmlAttribute(name = "upperValue", namespace = Vocab.EPD_2019)
	private Double max;

  // region getters

  public Double getValue() {
    return value;
  }

  public Double getMin() {
    return min;
  }

  public Double getMax() {
    return max;
  }

  // endregion

  // region setters

  public EpdContentAmount withValue(Double value) {
    this.value = value;
    return this;
  }

  public EpdContentAmount withMin(Double min) {
    this.min = min;
    return this;
  }

  public EpdContentAmount withMax(Double max) {
    this.max = max;
    return this;
  }

  // endregion

  @Override
  public EpdContentAmount copy() {
    var copy = new EpdContentAmount();
    copy.withValue(value);
    copy.withMin(min);
    copy.withMax(max);
    return copy;
  }

	@Override
	public String toString() {
		var s = "Amount {";
		boolean c = false;
		if (value != null) {
			s += " value = " + value;
			c = true;
		}
		if (min != null) {
			if (c) {
				s += ",";
			}
			s += " min = " + min;
			c = true;
		}
		if (max != null) {
			if (c) {
				s += ",";
			}
			s += " max = " + max;
		}
		return s + " }";
	}

}
