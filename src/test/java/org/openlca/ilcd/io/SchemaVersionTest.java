package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.methods.ImpactMethod;
import org.openlca.ilcd.models.Model;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.DataSets;

import java.util.List;
import java.util.UUID;

public class SchemaVersionTest {

	@Test
	public void testFormatVersion() {
		var dataSets = List.of(
			new Contact(),
			new Source(),
			new UnitGroup(),
			new FlowProperty(),
			new Flow(),
			new Process(),
			new Model(),
			new ImpactMethod());
		for (var ds : dataSets) {
			assertEquals("failed for " + ds,
				IDataSet.SCHEMA_VERSION, ds.getSchemaVersion());
			DataSets.withUUID(ds, UUID.randomUUID().toString());
			var xml = Xml.toString(ds);
			// System.out.println(xml);
			var copy = Xml.read(ds.getClass(), xml);
			assertEquals(DataSets.getUUID(ds), DataSets.getUUID(copy));
			assertEquals(IDataSet.SCHEMA_VERSION, copy.getSchemaVersion());
		}
	}

}
