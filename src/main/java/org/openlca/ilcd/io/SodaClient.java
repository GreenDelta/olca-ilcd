package org.openlca.ilcd.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openlca.commons.Res;
import org.openlca.commons.Strings;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.descriptors.CategorySystemList;
import org.openlca.ilcd.descriptors.DataStockList;
import org.openlca.ilcd.descriptors.Descriptor;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.lists.CategorySystem;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.util.DataSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A client interface of a Soda4LCA service end-point.
 */
public class SodaClient implements DataStore {


	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final HttpCookieStore cookies = new HttpCookieStore();
	private final String url;
	private final HttpClient client;
	private String dataStockId;
	private String authToken;

	private SodaClient(String url) {
		this.url = Http.trimTrailingSlash(url);
		this.client = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.followRedirects(HttpClient.Redirect.NORMAL)
			.build();
	}

	public static SodaClient of(String url) {
		return new SodaClient(url);
	}

	public static SodaClient of(SodaConnection con) {
		var client = SodaClient.of(con.url);
		if (Strings.isNotBlank(con.user) && Strings.isNotBlank(con.password)) {
			client.login(con.user, con.password);
		}
		client.useDataStock(con.dataStockId);
		return client;
	}

	/**
	 * Performs a session based login. A session cookie is stored and used for
	 * all requests until logout. Note that this method throws an exception
	 * when the login failed.
	 */
	public SodaClient login(String user, String password) {
		log.info("login user: {}", user);
		var request = new Req()
			.p("authenticate/login")
			.q("userName", user)
			.q("password", password)
			.get();
		var response = send(request, HttpResponse.BodyHandlers.ofString());
		eval(response);
		cookies.addAllOf(response);
		return this;
	}

	/**
	 * Get an authentication token for the given user and password from the API.
	 */
	public Res<String> getAuthenticationToken(String user, String password) {
		try {
			var request = new Req()
				.p("authenticate/getToken")
				.q("userName", user)
				.q("password", password)
				.get();
			var response = send(request, HttpResponse.BodyHandlers.ofString());
			var token = response.body();
			return response.statusCode() == 200
				? Res.ok(token)
				: Res.error("failed to get token: " + token);
		} catch (Exception e) {
			return Res.error("failed to get authentication token", e);
		}
	}

	public SodaClient withAuthenticationToken(String token) {
		this.authToken = token;
		return this;
	}

	public void logout() {
		if (cookies.isEmpty())
			return;
		try {
			var info = getAuthInfo();
			if (info.isAuthenticated()) {
				var response = send(
					new Req().p("authenticate/logout").get(),
					HttpResponse.BodyHandlers.ofString());
				log.trace(response.body());
			}
		} catch (Exception e) {
			log.error("logout failed", e);
		} finally {
			cookies.clear();
		}
	}

	public SodaClient useDataStock(String dataStockId) {
		this.dataStockId = dataStockId;
		return this;
	}

	public AuthInfo getAuthInfo() {
		log.trace("get authentication status: /authenticate/status");
		var body = getBytes(new Req().p("authenticate/status").get());
		return Xml.read(AuthInfo.class, body);
	}

	public DataStockList getDataStockList() {
		log.trace("get data stock list: /datastocks");
		var body = getBytes(new Req().p("datastocks").get());
		return Xml.read(DataStockList.class, body);
	}

	public CategorySystemList getCategorySystemList() {
		log.trace("get category system list: /categorySystems");
		var body = getBytes(new Req().p("categorySystems").get());
		return Xml.read(CategorySystemList.class, body);
	}

	public CategorySystem getCategorySystem(String name) {
		log.trace("get category system list: /categorySystems/{}", name);
		var body = getBytes(new Req().p("categorySystems").p(name).get());
		return Xml.read(CategorySystem.class, body);
	}

	@Override
	public <T extends IDataSet> T get(Class<T> type, String id) {
		var request = new Req().onStock().p(type).p(id).q("format", "xml").get();
		var response = getStream(request);
		try (var stream = response.body()) {
			return Xml.read(type, stream);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load resource " + id
				+ " of type " + type, e);
		}
	}

	public int count(Class<? extends IDataSet> type) {
		var body = getBytes(new Req().onStock().p(type)
			.q(new SodaQuery().withCountOnly(true))
			.get());
		return Xml.read(DescriptorList.class, body).getTotalSize();
	}

	@Override
	public void put(IDataSet ds) {
		var uid = DataSets.getUUID(ds);
		if (uid == null) {
			log.error("failed to read UUID from dataset {}", ds);
			return;
		}
		try {
			byte[] bytes = Xml.toBytes(ds);
			var req = new Req().p(ds.getClass()).accept("application/xml");
			if (Strings.isNotBlank(dataStockId)) {
				req = req.header("stock", dataStockId);
			}
			var response = send(
				req.postXml(bytes),
				HttpResponse.BodyHandlers.ofString());
			eval(response);
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload data set " + ds, e);
		}
	}

	@Override
	public void put(Source source, File[] files) {
		log.info("Publish source with files {}", source);
		try {
			var multipart = new HttpMultipart();
			if (Strings.isNotBlank(dataStockId)) {
				log.trace("post to data stock {}", dataStockId);
				multipart.addText("stock", dataStockId);
			}

			// add the XML as `file` parameter
			byte[] bytes = Xml.toBytes(source);
			multipart.addPart("file", "multipart/form-data", bytes);

			// add the other files
			if (files != null) {
				for (var file : files) {
					if (file == null)
						continue;
					multipart.addFile(file.getName(), "multipart/form-data", file);
				}
			}

			var payload = multipart.build();
			var req = new Req().p("sources").p("withBinaries");
			if (Strings.isNotBlank(dataStockId)) {
				req = req.header("stock", dataStockId);
			}
			var response = send(
				req.post(payload.publisher(), payload.contentType()),
				HttpResponse.BodyHandlers.ofString());
			eval(response);
			log.trace("Server response: {}", response.body());
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload source with file", e);
		}
	}

	/// Opens the external document with the given name of the source with the
	/// given ID. The returned stream must be closed by the caller, otherwise the
	/// underlying HTTP connection is not released.
	@Override
	public InputStream getExternalDocument(String sourceId, String fileName) {
		var request = new Req()
			.onStock()
			.p("sources")
			.p(sourceId)
			.p(fileName)
			.accept("application/octet-stream")
			.get();
		return getStream(request).body();
	}

	@Override
	public <T extends IDataSet> boolean delete(Class<T> type, String id) {
		// not supported by the soda4LCA client API
		return false;
	}

	@Override
	public <T extends IDataSet> Iterable<T> iter(Class<T> type) {
		return () -> new Iterator<>() {
			final List<Descriptor<?>> ds = getDescriptors(type);
			int pos = 0;

			@Override
			public boolean hasNext() {
				return pos < ds.size();
			}

			@Override
			public T next() {
				var d = ds.get(pos);
				pos++;
				return get(type, d.getUUID());
			}
		};
	}

	@Override
	public <T extends IDataSet> boolean contains(Class<T> type, String id) {
		var request = new Req().onStock().p(type).p(id).q("format", "xml").head();
		var response = send(request, HttpResponse.BodyHandlers.discarding());
		return response.statusCode() == 200;
	}

	/**
	 * Includes also the version in the check.
	 */
	public boolean contains(Ref ref) {
		if (ref == null || ref.getType() == null || ref.getUUID() == null)
			return false;
		var req = new Req().onStock()
			.p(ref.getDataSetClass())
			.p(ref.getUUID())
			.q("format", "xml");
		if (ref.getVersion() != null) {
			req = req.q("version", ref.getVersion());
		}
		var response = send(req.head(), HttpResponse.BodyHandlers.discarding());
		return response.statusCode() == 200;
	}

	public <T extends IDataSet> DescriptorList search(Class<T> type, String name) {
		var q = new SodaQuery()
			.withSearch(true)
			.withName(name == null ? "" : name.trim());
		return query(type, q);
	}

	public <T extends IDataSet> DescriptorList query(Class<T> type, SodaQuery q) {
		if (q == null) {
			return new DescriptorList();
		}
		var body = getBytes(new Req().onStock().p(type).q(q).get());
		return Xml.read(DescriptorList.class, body);
	}

	public <T extends IDataSet> List<Descriptor<?>> getDescriptors(Class<T> type) {
		try {
			var q = new SodaQuery().withPageSize(1000);
			var list = new ArrayList<Descriptor<?>>();
			do {
				var body = getBytes(new Req().onStock().p(type).q(q).get());
				var page = Xml.read(DescriptorList.class, body);
				list.addAll(page.getDescriptors());
				q = q.next(page).orElse(null);
			} while (q != null);
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public InputStream exportDataStock(String idOrName) {
		if (Strings.isBlank(idOrName))
			throw new IllegalArgumentException("no ID or name of data-stock provided");

		// find the data-stock
		String id = null;
		for (var stock : getDataStockList().getDataStocks()) {
			if (idOrName.equals(stock.getUUID())
				|| idOrName.equals(stock.getShortName())) {
				id = stock.getUUID();
				break;
			}
		}
		if (id == null) {
			throw new IllegalArgumentException(
				"data-stock " + idOrName + " does not exist on server");
		}

		var response = getStream(
			new Req().p("datastocks").p(id).p("export").get());
		return response.body();
	}

	@Override
	public void close() {
		logout();
		client.close();
	}

	private <T> HttpResponse<T> send(
		HttpRequest request, HttpResponse.BodyHandler<T> handler) {
		try {
			return client.send(request, handler);
		} catch (IOException e) {
			throw new RuntimeException("HTTP request failed: "
				+ request.method() + " " + request.uri(), e);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("HTTP request interrupted: "
				+ request.method() + " " + request.uri(), e);
		}
	}

	private byte[] getBytes(HttpRequest request) {
		var response = send(request, HttpResponse.BodyHandlers.ofByteArray());
		eval(response);
		return response.body();
	}

	/// Sends the given request and returns the response with a body stream when
	/// the status is below {@code 400}. For an error response, the body is read
	/// completely and the stream is closed so that the underlying connection can
	/// be returned to the connection pool.
	///
	/// Note: for a successful response the caller is responsible for closing the
	/// body stream. Otherwise, the connection stays in use and is not returned to
	/// the pool.
	private HttpResponse<InputStream> getStream(HttpRequest req) {
		var resp = send(req, HttpResponse.BodyHandlers.ofInputStream());
		int status = resp.statusCode();
		if (status >= 400) {
			// closes the stream after reading it completely; reading
			// the body completely is required so that the HttpClient can reuse
			// the underlying connection
			var message = "";
			try (var stream = resp.body()) {
				message = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
			} catch (Exception _) {
			}
			throw new RuntimeException(errorMessage(status, message));
		}
		return resp;
	}

	private void eval(HttpResponse<?> resp) {
		int status = resp.statusCode();
		if (status < 400)
			return;
		String message = "";
		var body = resp.body();
		if (body instanceof byte[] bytes) {
			message = new String(bytes, StandardCharsets.UTF_8);
		} else if (body instanceof String text) {
			message = text;
		}
		throw new RuntimeException(errorMessage(status, message));
	}

	private static String errorMessage(int status, String body) {
		var message = "HTTP status: " + status;
		if (Strings.isNotBlank(body)) {
			var text = body.trim();
			if (text.length() > 500) {
				text = text.substring(0, 500) + "...";
			}
			message += " - " + text;
		}
		return message;
	}




	/**
	 * Builds the requests of this client.
	 */
	private class Req {

		private final List<String> segments = new ArrayList<>();
		private final Map<String, String> params = new LinkedHashMap<>();
		private final Map<String, String> headers = new LinkedHashMap<>();

		/**
		 * Adds one or more path segments to the request. A value that contains
		 * {@code /} is split into multiple segments.
		 */
		Req p(String segment) {
			if (segment == null)
				return this;
			for (var part : segment.split("/")) {
				if (!part.isEmpty()) {
					segments.add(part);
				}
			}
			return this;
		}

		Req p(Class<? extends IDataSet> type) {
			return p(Dir.get(type));
		}

		/**
		 * Adds a query parameter to the request.
		 */
		Req q(String param, String value) {
			if (param != null && value != null) {
				params.put(param, value);
			}
			return this;
		}

		Req q(SodaQuery query) {
			if (query != null) {
				query.applyOn(params::put);
			}
			return this;
		}

		Req accept(String contentType) {
			return header("Accept", contentType);
		}

		Req header(String name, String value) {
			if (name != null && value != null) {
				headers.put(name, value);
			}
			return this;
		}

		Req onStock() {
			if (dataStockId != null) {
				p("datastocks").p(dataStockId);
			}
			return this;
		}

		HttpRequest get() {
			return request("GET", HttpRequest.BodyPublishers.noBody(), null);
		}

		HttpRequest head() {
			return request("HEAD", HttpRequest.BodyPublishers.noBody(), null);
		}

		HttpRequest postXml(byte[] body) {
			return request(
				"POST", HttpRequest.BodyPublishers.ofByteArray(body), "application/xml");
		}

		HttpRequest post(HttpRequest.BodyPublisher body, String contentType) {
			return request("POST", body, contentType);
		}

		private HttpRequest request(
			String method, HttpRequest.BodyPublisher body, String contentType) {
			var uri = uriOf();
			log.trace("create request: {}", uri);
			var builder = HttpRequest.newBuilder(uri);
			builder.header("Accept", headers.getOrDefault("Accept", "*/*"));
			for (var entry : headers.entrySet()) {
				if (!"Accept".equalsIgnoreCase(entry.getKey())) {
					builder.header(entry.getKey(), entry.getValue());
				}
			}
			if (!cookies.isEmpty()) {
				builder.header("Cookie", cookies.value());
			}
			if (Strings.isNotBlank(authToken)) {
				builder.header("Authorization", "Bearer " + authToken);
			}
			if (contentType != null) {
				builder.header("Content-Type", contentType);
			}
			return builder.method(method, body).build();
		}

		private URI uriOf() {
			var uri = new StringBuilder(url);
			for (var segment : segments) {
				uri.append('/').append(Http.encodePathSegment(segment));
			}
			if (!params.isEmpty()) {
				var first = true;
				for (var entry : params.entrySet()) {
					uri.append(first ? '?' : '&');
					first = false;
					uri.append(Http.encodeQuery(entry.getKey()))
						.append('=')
						.append(Http.encodeQuery(entry.getValue()));
				}
			}
			return URI.create(uri.toString());
		}
	}
}
