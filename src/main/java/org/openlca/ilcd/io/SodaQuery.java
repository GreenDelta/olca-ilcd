package org.openlca.ilcd.io;

import java.util.Optional;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.descriptors.DescriptorList;

import jakarta.ws.rs.client.WebTarget;

/**
 * A query for searching for datasets or iterating over the available datasets
 * of a Soda instance. See the official documentation for details about what
 * these query parameters mean.
 */
public class SodaQuery implements Copyable<SodaQuery> {

	private int startIndex = 0;
	private int pageSize = 500;

	private Boolean search;
	private Boolean distributed;
	private String name;
	private String description;
	private String classId;
	private String lang;
	private Boolean langFallback;
	private Boolean allVersions;
	private Boolean countOnly;

	public SodaQuery withStartIndex(int startIndex) {
		this.startIndex = startIndex;
		return this;
	}

	public SodaQuery withPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public SodaQuery withSearch(Boolean search) {
		this.search = search;
		return this;
	}

	public SodaQuery withDistributed(Boolean distributed) {
		this.distributed = distributed;
		return this;
	}

	public SodaQuery withName(String name) {
		this.name = name;
		return this;
	}

	public SodaQuery withDescription(String description) {
		this.description = description;
		return this;
	}

	public SodaQuery withClassId(String classId) {
		this.classId = classId;
		return this;
	}

	public SodaQuery withLang(String lang) {
		this.lang = lang;
		return this;
	}

	public SodaQuery withLangFallback(Boolean langFallback) {
		this.langFallback = langFallback;
		return this;
	}

	public SodaQuery withAllVersions(Boolean allVersions) {
		this.allVersions = allVersions;
		return this;
	}

	public SodaQuery withCountOnly(Boolean countOnly) {
		this.countOnly = countOnly;
		return this;
	}

	WebTarget applyOn(WebTarget r) {
		if (r == null)
			return r;
		var t = r.queryParam("pageSize", pageSize)
			.queryParam("startIndex", startIndex);
		if (search != null) {
			t = t.queryParam("search", search);
		}
		if (distributed != null) {
			t = t.queryParam("distributed", distributed);
		}
		if (name != null) {
			t = t.queryParam("name", name);
		}
		if (description != null) {
			t = t.queryParam("description", description);
		}
		if (classId != null) {
			t = t.queryParam("classId", classId);
		}
		if (lang != null) {
			t = t.queryParam("lang", lang);
		}
		if (langFallback != null) {
			t = t.queryParam("langFallback", langFallback);
		}
		if (allVersions != null) {
			t = t.queryParam("allVersions", allVersions);
		}
		if (countOnly != null) {
			t = t.queryParam("countOnly", countOnly);
		}
		return t;
	}

	/**
	 * Returns a query for the next page related to the given result of this
	 * query. The given result has to be retrieved from this query, otherwise
	 * a return next query may not work correctly.
	 */
	public Optional<SodaQuery> next(DescriptorList res) {
		if (res == null
			|| res.getStartIndex() != startIndex
			|| res.getPageSize() != pageSize)
			return Optional.empty();
		int nextIndex = startIndex + pageSize;
		int total = res.getTotalSize();
		if (nextIndex >= total)
			return Optional.empty();
		var q = this.copy().withStartIndex(nextIndex);
		return Optional.of(q);
	}

	@Override
	public SodaQuery copy() {
		return new SodaQuery()
			.withStartIndex(startIndex)
			.withPageSize(pageSize)
			.withSearch(search)
			.withDistributed(distributed)
			.withName(name)
			.withDescription(description)
			.withClassId(classId)
			.withLang(lang)
			.withLangFallback(langFallback)
			.withAllVersions(allVersions)
			.withCountOnly(countOnly);
	}
}
