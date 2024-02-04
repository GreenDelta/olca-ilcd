package org.openlca.ilcd.processes.epd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import org.openlca.ilcd.Vocab;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EpdInnerContentElement<T extends EpdInnerContentElement<T>>
  extends EpdContentElement<T> {

	@XmlAttribute(name = "ddGUID", namespace = Vocab.EPD_2019)
	private String guid;

	@XmlAttribute(name = "CASNumber", namespace = Vocab.EPD_2019)
	private String casNumber;

	@XmlAttribute(name = "ECNumber", namespace = Vocab.EPD_2019)
	private String ecNumber;

	@XmlAttribute(name = "hazardCode", namespace = Vocab.EPD_2019)
	private String hazardCode;

	@XmlAttribute(name = "renewable", namespace = Vocab.EPD_2019)
	private Double renewable;

	@XmlAttribute(name = "recycled", namespace = Vocab.EPD_2019)
	private Double recycled;

	@XmlAttribute(name = "recyclable", namespace = Vocab.EPD_2019)
	private Double recyclable;

	@XmlAttribute(name = "packaging", namespace = Vocab.EPD_2019)
	private Boolean packaging;

  // region getters

  public String getGuid() {
    return guid;
  }

  public String getCasNumber() {
    return casNumber;
  }

  public String getEcNumber() {
    return ecNumber;
  }

  public String getHazardCode() {
    return hazardCode;
  }

  public Double getRenewable() {
    return renewable;
  }

  public Double getRecycled() {
    return recycled;
  }

  public Double getRecyclable() {
    return recyclable;
  }

  public Boolean getPackaging() {
    return packaging;
  }

  // endregion

  // region setters

	@SuppressWarnings("unchecked")
  public T withGuid(String guid) {
    this.guid = guid;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withCasNumber(String casNumber) {
    this.casNumber = casNumber;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withEcNumber(String ecNumber) {
    this.ecNumber = ecNumber;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withHazardCode(String hazardCode) {
    this.hazardCode = hazardCode;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withRenewable(Double renewable) {
    this.renewable = renewable;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withRecycled(Double recycled) {
    this.recycled = recycled;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withRecyclable(Double recyclable) {
    this.recyclable = recyclable;
    return (T) this;
  }

	@SuppressWarnings("unchecked")
  public T withPackaging(Boolean packaging) {
    this.packaging = packaging;
    return (T) this;
  }

  // endregion

  @Override
  protected void copyBase(T copy) {
		super.copyBase(copy);
    copy.withGuid(guid)
			.withCasNumber(casNumber)
			.withEcNumber(ecNumber)
			.withHazardCode(hazardCode)
			.withRenewable(renewable)
			.withRecycled(recycled)
			.withRecyclable(recyclable)
			.withPackaging(packaging);
  }
}
