package org.openlca.ilcd.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openlca.ilcd.sources.FileRef;

public class SourcesFileNameTest {

	@Test
	public void testRelativePath() {
		FileRef ref = new FileRef();
		ref.withUri("../external_docs/mydoc.pdf");
		assertEquals("mydoc.pdf", Sources.getFileName(ref));
		ref.withUri("..\\external_docs\\mydoc.pdf");
		assertEquals("mydoc.pdf", Sources.getFileName(ref));
	}

	@Test
	public void testWindowsPath() {
		FileRef ref = new FileRef();
		ref.withUri("C:/external_docs/mydoc.pdf");
		assertEquals("mydoc.pdf", Sources.getFileName(ref));
		ref.withUri("C:\\external_docs\\mydoc.pdf");
		assertEquals("mydoc.pdf", Sources.getFileName(ref));
	}

	@Test
	public void testWebPath() {
		FileRef ref = new FileRef();
		ref.withUri("http://mysoda.com/external_docs/mydoc.pdf");
		assertEquals("mydoc.pdf", Sources.getFileName(ref));
	}

	@Test
	public void testUnescapePath() {
		FileRef ref = new FileRef();
		ref.withUri("http://mysoda.com/external_docs/my%20doc.pdf");
		assertEquals("my doc.pdf", Sources.getFileName(ref));
	}

}
