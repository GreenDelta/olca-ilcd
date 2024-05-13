package org.openlca.ilcd.io;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;

import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.sources.Source;

public interface DataStore extends Closeable {

	<T extends IDataSet> T get(Class<T> type, String id);

	InputStream getExternalDocument(String sourceId, String fileName);

	void put(IDataSet ds);

	void put(Source source, File[] files);

	<T extends IDataSet> boolean delete(Class<T> type, String id);

	<T extends IDataSet> boolean contains(Class<T> type, String id);

	<T extends IDataSet> Iterable<T> iter(Class<T> type);
}
