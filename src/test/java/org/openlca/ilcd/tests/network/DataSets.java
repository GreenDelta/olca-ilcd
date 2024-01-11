package org.openlca.ilcd.tests.network;

import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.ContactBag;
import org.openlca.ilcd.util.FlowBag;
import org.openlca.ilcd.util.FlowPropertyBag;
import org.openlca.ilcd.util.ProcessBag;
import org.openlca.ilcd.util.SourceBag;
import org.openlca.ilcd.util.UnitGroupBag;

import java.util.Objects;

/** Helper class for uploading the sample data sets into the test instance. */
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
		Contact contact = load(Contact.class, "contact.xml");
		ContactBag bag = new ContactBag(contact, "en");
		if (!client.contains(Contact.class, bag.getId()))
			client.put(contact);
	}

	private static void putFlowProperty(SodaClient client) {
		FlowProperty flowProperty = load(FlowProperty.class,
				"flowproperty.xml");
		FlowPropertyBag bag = new FlowPropertyBag(flowProperty, "en");
		if (!client.contains(FlowProperty.class, bag.getId()))
			client.put(flowProperty);
	}

	private static void putFlow(SodaClient client) {
		Flow flow = load(Flow.class, "flow.xml");
		FlowBag bag = new FlowBag(flow, "en");
		if (!client.contains(Flow.class, bag.getId()))
			client.put(flow);
	}

	private static void putProcess(SodaClient client) {
		Process process = load(Process.class, "process.xml");
		ProcessBag bag = new ProcessBag(process, "en");
		if (!client.contains(Process.class, bag.getId()))
			client.put(process);
	}

	private static void putSource(SodaClient client) {
		Source source = load(Source.class, "source.xml");
		SourceBag bag = new SourceBag(source, "en");
		if (!client.contains(Source.class, bag.getId()))
			client.put(source);
	}

	private static void putUnitGroup(SodaClient client) {
		UnitGroup group = load(UnitGroup.class, "unit.xml");
		UnitGroupBag bag = new UnitGroupBag(group, "en");
		if (!client.contains(UnitGroup.class, bag.getId()))
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
