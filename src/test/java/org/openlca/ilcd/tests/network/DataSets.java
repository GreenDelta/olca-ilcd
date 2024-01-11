package org.openlca.ilcd.tests.network;

import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import java.util.Objects;

/**
 * Helper class for uploading the sample data sets into the test instance.
 */
class DataSets {

	static void upload() {
		if (!TestServer.isAvailable())
			return;
		SodaClient client = TestServer.newClient();
		putUnitGroup(client);
		putFlowProperty(client);
		putFlow(client);
		putProcess(client);
		putSource(client);
		putContact(client);
	}

	private static void putContact(SodaClient client) {
		var contact = load(Contact.class, "contact.xml");
		if (!client.contains(Contact.class, contact.getUUID()))
			client.put(contact);
	}

	private static void putFlowProperty(SodaClient client) {
		var prop = load(FlowProperty.class, "flowproperty.xml");
		if (!client.contains(FlowProperty.class, prop.getUUID()))
			client.put(prop);
	}

	private static void putFlow(SodaClient client) {
		var flow = load(Flow.class, "flow.xml");
		if (!client.contains(Flow.class, flow.getUUID()))
			client.put(flow);
	}

	private static void putProcess(SodaClient client) {
		var process = load(Process.class, "process.xml");
		if (!client.contains(Process.class, process.getUUID()))
			client.put(process);
	}

	private static void putSource(SodaClient client) {
		var source = load(Source.class, "source.xml");
		if (!client.contains(Source.class, source.getUUID()))
			client.put(source);
	}

	private static void putUnitGroup(SodaClient client) {
		var group = load(UnitGroup.class, "unit.xml");
		if (!client.contains(UnitGroup.class, group.getUUID()))
			client.put(group);
	}

	private static <T> T load(Class<T> clazz, String file) {
		var stream = DataSets.class.getResourceAsStream(file);
		Objects.requireNonNull(stream);
		try (stream) {
			return Xml.read(clazz, stream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
