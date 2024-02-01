package org.openlca.ilcd.io;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openlca.ilcd.SampleSource;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.UnitGroups;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.junit.Assert.*;

public class FileStoreTest {

	private static FileStore fileStore;

	@BeforeClass
	public static void setup() throws Exception {
		var path = System.getProperty("java.io.tmpdir");
		var rootDir = new File(path + File.separator + "itest");
		fileStore = new FileStore(rootDir);
		fileStore.prepareFolder();
	}

	@AfterClass
	public static void cleanup() throws Exception {
		File file = fileStore.getRootFolder();
		deleteContent(file);
		Files.delete(file.toPath());
	}

	private static void deleteContent(File dir) throws IOException {
		var files = dir.listFiles();
		if (files == null)
			return;
		for (var file : files) {
			if (file.isDirectory()) {
				deleteContent(file);
			}
			Files.delete(file.toPath());
		}
	}

	@Test
	public void testGet() {
		var group = fileStore.get(
			UnitGroup.class, "93a60a57-a4c8-11da-a746-0800200c9a66");
		assertNotNull(group);
		assertEquals(
			"Units of mass",
			LangString.getFirst(UnitGroups.getName(group)));
	}

	@Test
	public void testPut() {
		var id = UUID.randomUUID().toString();
		var source = SampleSource.create();
		source.withSourceInfo()
			.withDataSetInfo()
			.withUUID(id);
		fileStore.put(source);
		assertTrue(fileStore.contains(Source.class, id));
	}

	@Test
	public void testDelete() {
		String id = "5bb337b0-9a1a-11da-a72b-0800200c9a62";
		assertTrue(fileStore.contains(Contact.class, id));
		fileStore.delete(Contact.class, id);
		assertFalse(fileStore.contains(Contact.class, id));
	}

	@Test
	@Ignore
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testContains() {
		String id = "631b917e-eb39-4d0f-aae6-98c805513b2f";
		assertTrue(fileStore.contains(Contact.class, id));
	}

}
