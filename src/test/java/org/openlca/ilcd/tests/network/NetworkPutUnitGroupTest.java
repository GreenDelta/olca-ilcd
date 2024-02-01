package org.openlca.ilcd.tests.network;

import java.util.Random;
import java.util.UUID;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Publication;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.units.AdminInfo;
import org.openlca.ilcd.units.DataSetInfo;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.units.UnitGroupInfo;

public class NetworkPutUnitGroupTest {

	private SodaClient client;

	@Before
	public void setUp() throws Exception {
		if (!TestServer.isAvailable())
			return;
		client = TestServer.newClient();
	}

	@Test
	public void testPutUnitGroup() {
		Assume.assumeTrue(TestServer.isAvailable());
		var id = UUID.randomUUID().toString();
		var ug = new UnitGroup()
			.withAdminInfo(makeAdminInfo());
		ug.withUnitGroupInfo()
			.withDataSetInfo(makeDataInfo(id));
		client.put(ug);
	}

	private DataSetInfo makeDataInfo(String id) {
		var info = new DataSetInfo()			.withUUID(id);
		String name = "xtest UnitGroup - " + new Random().nextInt(1000);
		info.withName().add(LangString.of(name, "en"));
		return info;
	}

	private AdminInfo makeAdminInfo() {
		AdminInfo info = new AdminInfo();
		Publication pub = new Publication();
		info.publication = pub;
		pub.version = "01.00.000";
		return info;
	}
}
