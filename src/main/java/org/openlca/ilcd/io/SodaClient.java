package org.openlca.ilcd.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.descriptors.CategorySystemList;
import org.openlca.ilcd.descriptors.DataStockList;
import org.openlca.ilcd.descriptors.Descriptor;
import org.openlca.ilcd.descriptors.DescriptorList;
import org.openlca.ilcd.lists.CategorySystem;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.util.DataSets;
import org.openlca.ilcd.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.Status.Family;


/**
 * A client interface of a Soda4LCA service end-point.
 */
public class SodaClient implements DataStore {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String url;
	private final Client client;
	private String dataStockId;

	private final List<Cookie> cookies = new ArrayList<>();

	private SodaClient(String url) {
		this.url = url;
		this.client = ClientBuilder.newClient()
			.register(MultiPartFeature.class);
	}

	public static SodaClient of(String url) {
		return new SodaClient(url);
	}

	public static SodaClient of(SodaConnection con) {
		var client = SodaClient.of(con.url);
		if (Strings.notEmpty(con.user) && Strings.notEmpty(con.password)) {
			client.login(con.user, con.password);
		}
		client.useDataStock(con.dataStockId);
		return client;
	}

	public SodaClient login(String user, String password) {
		log.info("login user: {}", user);
		var response = client.target(url)
			.path("authenticate/login")
			.queryParam("userName", user)
			.queryParam("password", password)
			.request()
			.get();
		eval(response);
		response.getCookies().forEach((key, value) -> cookies.add(value.toCookie()));
		return this;
	}

	public void logout() {
		if (cookies.isEmpty())
			return;
		try {
			var info = getAuthInfo();
			if (info.isAuthenticated()) {
				var message = new Req().p("authenticate/logout").get()
					.readEntity(String.class);
				log.trace(message);
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
		var resp = new Req().p("authenticate/status").get();
		eval(resp);
		return resp.readEntity(AuthInfo.class);
	}

	public DataStockList getDataStockList() {
		log.trace("get data stock list: /datastocks");
		return new Req().p("datastocks").get(DataStockList.class);
	}

	public CategorySystemList getCategorySystemList() {
		log.trace("get category system list: /categorySystems");
		return new Req().p("categorySystems").get(CategorySystemList.class);
	}

	public CategorySystem getCategorySystem(String name) {
		log.trace("get category system list: /categorySystems/{}", name);
		return new Req().p("categorySystems").p(name).get(CategorySystem.class);
	}

	@Override
	public <T extends IDataSet> T get(Class<T> type, String id) {
		var resp = new Req().onStock().p(type).p(id).q("format", "xml").get();
		eval(resp);
		try (var stream = resp.readEntity(InputStream.class)) {
			return Xml.read(type, stream);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load resource " + id
				+ " of type " + type, e);
		}
	}

	@Override
	public void put(IDataSet ds) {
		var uid = DataSets.getUUID(ds);
		if (uid == null) {
			log.error("failed to read UUID from dataset {}", ds);
			return;
		}
		var req = new Req().p(ds.getClass())
			.build()
			.accept(MediaType.APPLICATION_XML);
		try {
			byte[] bytes = Xml.toBytes(ds);
			if (Strings.notEmpty(dataStockId)) {
				req = req.header("stock", dataStockId);
			}
			var response = req.post(Entity.xml(bytes));
			eval(response);
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload data set + " + ds, e);
		}
	}

	@Override
	public void put(Source source, File[] files) {
		log.info("Publish source with files {}", source);
		try {
			var formData = new FormDataMultiPart();
			if (Strings.notEmpty(dataStockId)) {
				log.trace("post to data stock {}", dataStockId);
				formData.field("stock", dataStockId);
			}

			// add the XML as `file` parameter
			byte[] bytes = Xml.toBytes(source);
			var xmlStream = new ByteArrayInputStream(bytes);
			var xmlPart = new FormDataBodyPart("file", xmlStream,
				MediaType.MULTIPART_FORM_DATA_TYPE);
			formData.bodyPart(xmlPart);

			// add the other files
			if (files != null) {
				for (File file : files) {
					if (file == null)
						continue;
					var is = new FileInputStream(file);
					var part = new FormDataBodyPart(file.getName(),
						is, MediaType.MULTIPART_FORM_DATA_TYPE);
					formData.bodyPart(part);
				}
			}

			var req = new Req().p("sources/withBinaries").build();
			if (Strings.notEmpty(dataStockId)) {
				req = req.header("stock", dataStockId);
			}
			var resp = req.post(Entity.entity(
				formData, MediaType.MULTIPART_FORM_DATA_TYPE));
			eval(resp);
			log.trace("Server response: {}", fetchMessage(resp));
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload source with file", e);
		}
	}

	@Override
	public InputStream getExternalDocument(String sourceId, String fileName) {
		var r = new Req().onStock().p("sources").p(sourceId).p(fileName)
			.build()
			.accept(MediaType.APPLICATION_OCTET_STREAM)
			.get();
		eval(r);
		return r.readEntity(InputStream.class);
	}

	@Override
	public <T extends IDataSet> boolean delete(Class<T> type, String id) {
		// not supported by the soda4LCA client API
		return false;
	}

	@Override
	public <T extends IDataSet> Iterator<T> iterator(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends IDataSet> boolean contains(Class<T> type, String id) {
		var req = new Req().onStock().p(type).p(id).q("format", "xml");
		try (var resp = req.build().head()) {
			return resp.getStatus() == Status.OK.getStatusCode();
		}
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
			req.q("version", ref.getVersion());
		}
		try (var resp = req.build().head()) {
			return resp.getStatus() == Status.OK.getStatusCode();
		}
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
		return new Req().onStock().p(type).q(q).get(DescriptorList.class);
	}

	public <T extends IDataSet> List<Descriptor<?>> getDescriptors(Class<T> type) {
		try {
			var q = new SodaQuery().withPageSize(1000);
			var list = new ArrayList<Descriptor<?>>();
			do {
				var page = new Req().onStock().p(type).q(q).get(DescriptorList.class);
				list.addAll(page.getDescriptors());
				q = q.next(page).orElse(null);
			} while (q != null);
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public InputStream exportDataStock(String idOrName) {
		if (Strings.nullOrEmpty(idOrName))
			throw new IllegalArgumentException("no ID or name of data-stock provided");

		// find the data-stock
		String id = null;
		for (var stock : getDataStockList().getDataStocks()) {
			if (Strings.nullOrEqual(stock.getUUID(), idOrName)
				|| Strings.nullOrEqual(stock.getShortName(), idOrName)) {
				id = stock.getUUID();
				break;
			}
		}
		if (id == null) {
			throw new IllegalArgumentException(
				"data-stock " + idOrName + " does not exist on server");
		}

		var resp = new Req().p("datastocks").p( id).p( "export").get();
		eval(resp);
		return resp.readEntity(InputStream.class);
	}

	private void eval(Response resp) {
		if (resp == null)
			throw new IllegalArgumentException("Client response is NULL.");
		var status = Status.fromStatusCode(resp.getStatus());
		var family = status.getFamily();
		if (family == Family.CLIENT_ERROR || family == Family.SERVER_ERROR) {
			throw new RuntimeException(
				status.getReasonPhrase() + ": " + fetchMessage(resp));
		}
	}

	private String fetchMessage(Response response) {
		if (response.hasEntity())
			return response.readEntity(String.class);
		return "";
	}

	@Override
	public void close() {
		logout();
		client.close();
	}

	private class Req {

		private WebTarget target;

		Req() {
			target = client.target(url);
		}

		Req onStock() {
			if (dataStockId != null) {
				p("datastocks").p(dataStockId);
			}
			return this;
		}

		/**
		 * Adds a path segment to the request.
		 */
		Req p(String path) {
			target = target.path(path);
			return this;
		}

		<T extends IDataSet> Req p(Class<T> type) {
			return p(Dir.get(type));
		}

		/**
		 * Adds a query parameter to the request
		 */
		Req q(String param, String value) {
			target = target.queryParam(param, value);
			return this;
		}

		Req q(SodaQuery q) {
			if (q != null) {
				target = q.applyOn(target);
			}
			return this;
		}

		Invocation.Builder build() {
			log.trace("create request: {}", target.getUri());
			var builder = target.request();
			for (var c : cookies) {
				builder.cookie(c);
			}
			return builder;
		}

		Response get() {
			return build().get();
		}

		<T> T get(Class<T> type) {
			return build().get(type);
		}
	}
}
