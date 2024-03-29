package org.openlca.ilcd.io;

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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
		log.info("Authenticate user: {}", user);
		var response = client.target(url)
			.path("authenticate")
			.path("login")
			.queryParam("userName", user)
			.queryParam("password", password)
			.request()
			.get();
		eval(response);
		response.getCookies().forEach((key, value) -> cookies.add(value.toCookie()));
		return this;
	}

	public SodaClient useDataStock(String dataStockId) {
		this.dataStockId = dataStockId;
		return this;
	}

	public AuthInfo getAuthInfo() {
		log.trace("Get authentication information.");
		var r = resource("authenticate", "status");
		var response = cookies(r).get();
		eval(response);
		return response.readEntity(AuthInfo.class);
	}

	public DataStockList getDataStockList() {
		log.trace("get data stock list: /datastocks");
		var r = resource("datastocks");
		return cookies(r).get(DataStockList.class);
	}

	public CategorySystemList getCategorySystemList() {
		log.trace("get category system list: /categorySystems");
		var r = resource("categorySystems");
		return cookies(r).get(CategorySystemList.class);
	}

	public CategorySystem getCategorySystem(String name) {
		log.trace("get category system list: /categorySystems/{}", name);
		var r = resource("categorySystems", name);
		return cookies(r).get(CategorySystem.class);
	}

	@Override
	public <T extends IDataSet> T get(Class<T> type, String id) {
		var r = resource(Dir.get(type), id).queryParam("format", "xml");
		log.info("Get resource: {}", r.getUri());
		var response = cookies(r).get();
		eval(response);
		try (var stream = response.readEntity(InputStream.class)) {
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
		var r = resource(Dir.get(ds.getClass()));
		log.info("Publish resource: {}/{}", r.getUri(), uid);
		try {
			byte[] bytes = Xml.toBytes(ds);
			var builder = cookies(r).accept(MediaType.APPLICATION_XML);
			if (Strings.notEmpty(dataStockId)) {
				log.trace("post to data stock {}", dataStockId);
				builder = builder.header("stock", dataStockId);
			}
			var response = builder.post(Entity.xml(bytes));
			eval(response);
			log.trace("Server response: {}", fetchMessage(response));
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
			addFiles(files, formData);


			var r = resource("sources/withBinaries");
			var resp = cookies(r)
				.post(Entity.entity(formData, MediaType.MULTIPART_FORM_DATA_TYPE));
			eval(resp);
			log.trace("Server response: {}", fetchMessage(resp));
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload source with file", e);
		}
	}

	private void addFiles(File[] files, FormDataMultiPart multiPart)
		throws Exception {
		if (files == null)
			return;
		for (File file : files) {
			if (file == null)
				continue;
			var is = new FileInputStream(file);
			var part = new FormDataBodyPart(file.getName(),
				is, MediaType.MULTIPART_FORM_DATA_TYPE);
			multiPart.bodyPart(part);
		}
	}

	@Override
	public InputStream getExternalDocument(String sourceId, String fileName) {
		var r = resource("sources", sourceId, fileName);
		log.info("Get external document {} for source {}", fileName, sourceId);
		var response = cookies(r)
			.accept(MediaType.APPLICATION_OCTET_STREAM)
			.get();
		eval(response);
		return response.readEntity(InputStream.class);
	}

	@Override
	public <T extends IDataSet> boolean delete(Class<T> type, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends IDataSet> Iterator<T> iterator(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends IDataSet> boolean contains(Class<T> type, String id) {
		var r = resource(Dir.get(type), id)
			.queryParam("format", "xml");
		log.trace("Contains resource {} ?", r.getUri());
		var response = cookies(r).head();
		log.trace("Server response: {}", response);
		return response.getStatus() == Status.OK.getStatusCode();
	}

	/**
	 * Includes also the version in the check.
	 */
	public boolean contains(Ref ref) {
		if (ref == null || ref.getType() == null || ref.getUUID() == null)
			return false;
		var r = resource(Dir.get(ref.getDataSetClass()), ref.getUUID())
			.queryParam("format", "xml");
		if (ref.getVersion() != null)
			r = r.queryParam("version", ref.getVersion());
		try (var response = cookies(r).head()) {
			return response.getStatus() == Status.OK.getStatusCode();
		}
	}

	public DescriptorList search(Class<?> type, String name) {
		try {
			String term = name == null ? "" : name.trim();
			var r = Strings.nullOrEmpty(dataStockId)
				? resource(Dir.get(type))
				: resource("datastocks", dataStockId, Dir.get(type));
			r = r.queryParam("search", "true")
				.queryParam("name", term);
			log.trace("Search resources: {}", r.getUri());
			return cookies(r).get(DescriptorList.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Descriptor<?>> getDescriptors(Class<?> type) {
		log.debug("get descriptors for {}", type);
		try {
			var r = Strings.nullOrEmpty(dataStockId)
				? resource(Dir.get(type))
				: resource("datastocks", dataStockId, Dir.get(type));
			r = r.queryParam("pageSize", "1000");
			var list = new ArrayList<Descriptor<?>>();
			int total;
			int idx = 0;
			do {
				log.debug("get descriptors for {} @startIndex={}", type, idx);
				r = r.queryParam("startIndex", Integer.toString(idx));
				DescriptorList data = cookies(r).get(DescriptorList.class);
				total = data.getTotalSize();
				int fetched = data.getDescriptors().size();
				if (fetched == 0)
					break;
				list.addAll(data.getDescriptors());
				idx += fetched;
			} while (list.size() < total);
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

		var r = resource("datastocks", id, "export");
		var response = cookies(r).get();
		eval(response);
		return response.readEntity(InputStream.class);
	}

	private WebTarget resource(String... path) {
		var target = client.target(url);
		for (String p : path) {
			target = target.path(p);
		}
		return target;
	}

	private Invocation.Builder cookies(WebTarget target) {
		var builder = target.request();
		for (Cookie c : cookies) {
			builder.cookie(c);
		}
		return builder;
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
		client.close();
	}
}
