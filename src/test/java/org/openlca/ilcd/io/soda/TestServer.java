package org.openlca.ilcd.io.soda;

import java.net.HttpURLConnection;
import java.net.URL;

import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.SodaConnection;
import org.slf4j.LoggerFactory;

/**
 * In order to run the tests for the soda4LCA REST client API, you
 * need to have a local soda4LCA instance running at port 8080 with
 * the default administrator account (user: admin, password: default).
 * Also, the tests will select a data stock that starts with "test" to
 * run the tests against. As it is not possible to delete datasets
 * with the client API, the tests will produce test data in this data
 * stock. Thus, it is a good idea to just create and delete such
 * "test*" data stocks in the instance (do not forget to give the
 * admin read and write access to these stocks).
 */
class TestServer {

	static final String ENDPOINT = "http://localhost:8080/resource";
	static final String USER = "admin";
	static final String PASSWORD = "default";

	private static boolean available;

	static {
		var log = LoggerFactory.getLogger(TestServer.class);
		var url = ENDPOINT + "/authenticate/status";
		try {
			var con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("HEAD");
			int responseCode = con.getResponseCode();
			if (responseCode == 200) {
				log.info("can run tests against {}", ENDPOINT);
				available = true;
			} else {
				log.info("no tests against {} possible", ENDPOINT);
				available = false;
			}
		} catch (Exception e) {
			log.error("no tests against {} possible: {}", url, e.getMessage());
			available = false;
		}
	}

	/**
	 * Returns true if the soda4LCA instance is accessible for the tests.
	 */
	public static boolean isAvailable() {
		return available;
	}

	/**
	 * Creates a new client connection.
	 */
	public static SodaClient newClient() {
		var con = new SodaConnection();
		con.url = ENDPOINT;
		con.user = USER;
		con.password = PASSWORD;
		var client = SodaClient.of(con);
		for (var stock : client.getDataStockList().getDataStocks()) {
			if (stock.getShortName() == null || stock.getUUID() == null)
				continue;
			var name = stock.getShortName().strip().toLowerCase();
			if (name.startsWith("test")) {
				client.useDataStock(stock.getUUID());
			}
		}
		return client;
	}
}
