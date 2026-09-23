package org.openlca.ilcd.io;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.openlca.commons.Strings;

/// A minimal in-memory store for the cookies of a single soda4LCA session.
/// Note that the attributes of a cookie (`Path`, `Domain`, `Secure`, `Expires`)
/// are ignored and that cookie names are handled case-insensitively.
class HttpCookieList {

	private final List<Cookie> cookies = new CopyOnWriteArrayList<>();

	boolean isEmpty() {
		return cookies.isEmpty();
	}

	void clear() {
		cookies.clear();
	}

	String value() {
		var b = new StringBuilder();
		for (var c : cookies) {
			if (!b.isEmpty()) {
				b.append("; ");
			}
			b.append(c.value);
		}
		return b.toString();
	}

	void addAllOf(HttpResponse<?> resp) {
		if (resp == null)
			return;
		for (var value : resp.headers().allValues("Set-Cookie")) {
			var cookie = Cookie.parse(value);
			if (cookie == null)
				continue;
			// a cookie with the same name replaces an existing one; cookie
			// names are case-insensitive
			cookies.removeIf(c -> Strings.equalsIgnoreCase(c.name, cookie.name));
			cookies.add(cookie);
		}
	}

	private record Cookie(String name, String value) {

		static Cookie parse(String headerValue) {
			if (headerValue == null)
				return null;
			int idx = headerValue.indexOf(';');
			var value = (idx < 0
				? headerValue
				: headerValue.substring(0, idx)
			).trim();

			return value.contains("=")
				? new Cookie(nameOf(value), value)
				: null;
		}

		/// Extracts the name of a cookie, i.e. the part before the first {@code =}.
		private static String nameOf(String value) {
			var idx = value.indexOf('=');
			return idx < 0 ? "" : value.substring(0, idx);
		}
	}
}
