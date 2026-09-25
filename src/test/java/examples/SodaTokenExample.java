package examples;

import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.processes.Process;

public class SodaTokenExample {

	static void main() {
		var client = SodaClient.of("http://localhost:8080/resource");
		var token = client.getAuthenticationToken("admin", "default")
			.orElseThrow();
		System.out.println(token);
		client.withAuthenticationToken(token);

		var stocks = client.getDataStockList();
		for (var stock : stocks.getDataStocks()) {
			System.out.println(stock.getName());
		}

		var ps = client.getDescriptors(Process.class);
		for (var p : ps) {
			System.out.println(LangString.getDefault(p.getName()));
		}
	}
}
