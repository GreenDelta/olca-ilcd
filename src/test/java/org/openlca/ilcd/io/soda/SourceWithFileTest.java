package org.openlca.ilcd.io.soda;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.SampleSource;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.sources.FileRef;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.util.Sources;

public class SourceWithFileTest {

	private SodaClient client;

	@Before
	public void setUp() {
		Assume.assumeTrue(TestServer.isAvailable());
		client = TestServer.newClient();
	}

	@Test
	public void testSimpleSourceUpload() {
		Assume.assumeTrue(TestServer.isAvailable());
		var id = UUID.randomUUID().toString();
		var source = makeSource(id);
		client.put(source);
		var fromServer = client.get(Source.class, id);
		assertEquals(id, Sources.getUUID(fromServer));
	}

	@Test
	public void testSourceWithFileUpload() throws Exception {
		Assume.assumeTrue(TestServer.isAvailable());
		var id = UUID.randomUUID().toString();
		var source = makeSource(id);
		Path tempFile = Files.createTempFile("soda_upload_test", ".txt");
		byte[] content = "Test file content".getBytes();
		Files.write(tempFile, content);
		var file = tempFile.toFile();
		addFileLink(source, file);
		client.put(source, new File[]{file});

		// try to get the file from the server
		try (var stream = client.getExternalDocument(id, file.getName())) {
			byte[] contentFromServer = new byte[content.length];
			assertEquals(content.length, stream.read(contentFromServer));
			assertArrayEquals(content, contentFromServer);
		}
	}

	@Test(expected = Exception.class)
	public void testNoFile() {
		Assume.assumeTrue(TestServer.isAvailable());
		var randomID = UUID.randomUUID().toString();
		client.getExternalDocument(randomID, "no_such_file.txt");
	}

	private Source makeSource(String id) {
		var source = SampleSource.create();
		source.withSourceInfo().withDataSetInfo().withUUID(id);
		return source;
	}

	private void addFileLink(Source source, File file) {
		source.withSourceInfo()
			.withDataSetInfo()
			.withFiles()
			.add(new FileRef()
				.withUri("../external_docs/" + file.getName()));
	}
}
