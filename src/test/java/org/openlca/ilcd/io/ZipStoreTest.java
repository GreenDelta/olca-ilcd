package org.openlca.ilcd.io;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openlca.ilcd.SampleSource;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.sources.Source;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.*;

public class ZipStoreTest {

	private static ZipStore store;
	private static File zipFile;

	@BeforeClass
	public static void setUpStore() throws Exception {
		String tempDir = System.getProperty("java.io.tmpdir");
		String path = tempDir + File.separator + "test_" + UUID.randomUUID()
			+ ".zip";
		zipFile = new File(path);
		store = new ZipStore(zipFile);
	}

	@AfterClass
	public static void tearDown() {
		store.close();
		boolean deleted = zipFile.delete();
		System.out.println("zip file " + zipFile + "  deleted = " + deleted);
	}

	@Test
	public void testWithSource() {
		var id = UUID.randomUUID().toString();
		var source = SampleSource.create();
		source.withSourceInfo()
			.withDataSetInfo()
			.withUUID(id);
		store.put(source);
		assertTrue(store.contains(Source.class, id));
		Source copy = store.get(Source.class, id);
		assertEquals(id, copy.getSourceInfo().getDataSetInfo().getUUID());
		assertNotNull(store.iterator(Source.class).next());
	}

	@Test
	public void testNoContact() {
		assertFalse(store.contains(Contact.class, "110_abc"));
		assertFalse(store.iterator(Contact.class).hasNext());
	}

}
