package org.openlca.ilcd.io;


import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.util.Processes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Objects;

/**
 * Provides a meta-data-only view on a data store. This will remove exchanges
 * from processes and characterization factors from impact categories.
 */
public class MetaDataOnlyStore implements DataStore {

	private final DataStore store;
	private boolean keepRefFlows = false;

	private MetaDataOnlyStore(DataStore store) {
		this.store = Objects.requireNonNull(store);
	}

	public static MetaDataOnlyStore of(DataStore store) {
		return new MetaDataOnlyStore(store);
	}

	public MetaDataOnlyStore keepReferenceFlows(boolean b) {
		this.keepRefFlows = b;
		return this;
	}

	@Override
	public <T extends IDataSet> T get(Class<T> type, String id) {
		return strip(store.get(type, id));
	}

	@Override
	public InputStream getExternalDocument(String sourceId, String fileName) {
		return store.getExternalDocument(sourceId, fileName);
	}

	@Override
	public void put(IDataSet ds) {
		store.put(strip(ds));
	}

	@Override
	public void put(Source source, File[] files) {
		store.put(source, files);
	}

	@Override
	public <T extends IDataSet> boolean delete(Class<T> type, String id) {
		return store.delete(type, id);
	}

	@Override
	public <T extends IDataSet> Iterator<T> iterator(Class<T> type) {
		return new Iterator<>() {

			private final Iterator<T> it = store.iterator(type);

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public T next() {
				return strip(it.next());
			}
		};
	}

	@Override
	public <T extends IDataSet> boolean contains(Class<T> type, String id) {
		return store.contains(type, id);
	}

	@Override
	public void close() throws IOException {
		store.close();
	}

	private <T extends IDataSet> T strip(T ds) {
		if (ds instanceof Process p) {
			p.withMetaDataOnly(true);
			p.withImpactResults(null);
			stripExchanges(p);
			return ds;
		}
		if (ds instanceof ImpactMethod m) {
			m.withFactors(null);
		}
		return ds;
	}

	private void stripExchanges(Process p) {
		if (p.getExchanges().isEmpty())
			return;
		if (!keepRefFlows) {
			p.withExchanges(null);
			return;
		}
		var qRefs = Processes.getReferenceFlows(p);
		if (qRefs.isEmpty()) {
			p.withExchanges(null);
			return;
		}
		p.getExchanges().removeIf(e -> !qRefs.contains(e.getId()));
	}
}
