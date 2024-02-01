package org.openlca.ilcd.util;

import org.openlca.ilcd.processes.Exchange;

public class ExchangeExtension {

	private final Exchange e;

	private Boolean valid; // just for faster validation checks

	public ExchangeExtension(Exchange e) {
		this.e = e;
	}

	public boolean isValid() {
		if (valid != null)
			return valid;
		valid = getUnitId() != null
				&& getPropertyId() != null
				&& getAmount() != null;
		return valid;
	}

	public void setFormula(String formula) {
		Extensions.setString(e::withOtherAttributes, "formula", formula);
	}

	public String getFormula() {
		return Extensions.getString(e.getOtherAttributes(), "formula");
	}

	public void setUnitId(String unitId) {
		Extensions.setString(e::withOtherAttributes, "unitId", unitId);
	}

	public String getUnitId() {
		return Extensions.getString(e.getOtherAttributes(), "unitId");
	}

	public void setPropertyId(String propertyId) {
		Extensions.setString(e::withOtherAttributes, "propertyId", propertyId);
	}

	public String getPropertyId() {
		return Extensions.getString(e.getOtherAttributes(), "propertyId");
	}

	public void setAmount(double amount) {
		Extensions.setDouble(e::withOtherAttributes, "amount", amount);
	}

	public Double getAmount() {
		return Extensions.getDouble(e.getOtherAttributes(), "amount");
	}

	public void setPedigreeUncertainty(String val) {
		Extensions.setString(e::withOtherAttributes, "pedigreeUncertainty", val);
	}

	public String getPedigreeUncertainty() {
		return Extensions.getString(e.getOtherAttributes(), "pedigreeUncertainty");
	}

	public void setBaseUncertainty(Double val) {
		if (val == null)
			return;
		Extensions.setDouble(e::withOtherAttributes, "baseUncertainty", val);
	}

	public Double getBaseUncertainty() {
		return Extensions.getDouble(e.getOtherAttributes(), "baseUncertainty");
	}

	public void setMostLikelyValue(double val) {
		Extensions.setDouble(e::withOtherAttributes, "mostLikelyValue", val);
	}

	public Double getMostLikelyValue() {
		return Extensions.getDouble(e.getOtherAttributes(), "mostLikelyValue");
	}

	public String getDefaultProvider() {
		return Extensions.getString(e.getOtherAttributes(), "defaultProvider");
	}

	public void setDefaultProvider(String providerId) {
		Extensions.setString(e::withOtherAttributes, "defaultProvider", providerId);
	}

	public boolean isAvoidedProduct() {
		return Extensions.getBoolean(e.getOtherAttributes(), "avoidedProduct");
	}

	public void setAvoidedProduct(boolean b) {
		Extensions.setBoolean(e::withOtherAttributes, "avoidedProduct", b);
	}

}
