package org.openlca.ilcd.descriptors;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dataStockList", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
public class DataStockList implements Copyable<DataStockList> {

	@XmlElement(name = "dataStock", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<DataStock> dataStocks;

	// region getters

	public List<DataStock> getDataStocks() {
		return dataStocks != null ? dataStocks : List.of();
	}

	// endregion

	// region setters

	public DataStockList withDataStocks(List<DataStock> dataStocks) {
		this.dataStocks = dataStocks;
		return this;
	}

	public List<DataStock> withDataStocks() {
		if (dataStocks == null) {
			dataStocks = new ArrayList<>();
		}
		return dataStocks;
	}

	// endregion

	@Override
	public DataStockList copy() {
		var copy = new DataStockList();
		Val.copy(dataStocks, copy::withDataStocks);
		return copy;
	}

}
