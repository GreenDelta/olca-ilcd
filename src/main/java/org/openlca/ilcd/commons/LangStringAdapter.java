package org.openlca.ilcd.commons;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.openlca.ilcd.commons.LangString2.Entry;

import java.util.ArrayList;
import java.util.List;

public class LangStringAdapter extends XmlAdapter<List<LangString2.Entry>, LangString2> {

	@Override
	public LangString2 unmarshal(List<Entry> v) throws Exception {
		if (v == null || v.isEmpty())
			return null;
		var s = new LangString2();
		for (var e : v) {
			s.put(e);
		}
		return s;
	}

	@Override
	public List<Entry> marshal(LangString2 v) {
		return v != null
			? new ArrayList<>(v.getEntries())
			: null;
	}
}
