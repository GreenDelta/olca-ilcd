package org.openlca.ilcd.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpTest {

	@Test
	public void testTrimTrailingSlash() {
		assertEquals("", Http.trimTrailingSlash(null));
		assertEquals("", Http.trimTrailingSlash("  "));
		assertEquals("http://host", Http.trimTrailingSlash("http://host"));
		assertEquals("http://host", Http.trimTrailingSlash("http://host/"));
		assertEquals("http://host", Http.trimTrailingSlash("http://host//"));
		assertEquals("http://host/soda",
			Http.trimTrailingSlash(" http://host/soda/ "));
		// the slashes of the scheme part must not be removed
		assertEquals("http://", Http.trimTrailingSlash("http://"));
		assertEquals("https://", Http.trimTrailingSlash("https://"));
	}

	@Test
	public void testEncodePathSegment() {
		assertEquals("abc-._~", Http.encodePathSegment("abc-._~"));
		assertEquals("my%20file%20%C3%A4.txt",
			Http.encodePathSegment("my file ä.txt"));
		assertEquals("a%2Fb", Http.encodePathSegment("a/b"));
		assertEquals("%2B%26%3D", Http.encodePathSegment("+&="));
	}

	@Test
	public void testEncodeQuery() {
		assertEquals("a+b", Http.encodeQuery("a b"));
		assertEquals("%C3%A4", Http.encodeQuery("ä"));
	}
}
