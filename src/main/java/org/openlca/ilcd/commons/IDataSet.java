package org.openlca.ilcd.commons;

import java.util.List;

public interface IDataSet {

	DataSetType getDataSetType();

	String getURI();

	String getUUID();

	String getVersion();

	List<Classification> getClassifications();

	List<LangString> getName();

}
