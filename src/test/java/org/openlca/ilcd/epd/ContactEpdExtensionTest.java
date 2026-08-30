package org.openlca.ilcd.epd;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.contacts.EpdEntityId;
import org.openlca.ilcd.io.Xml;
import org.openlca.ilcd.util.Contacts;

public class ContactEpdExtensionTest {

	@Test
	public void testReadEntityIds() {
		var contact = Tests.read(Contact.class, "epd-contact-extension.xml");
		assertNotNull(contact);

		String[][] expected = {
			{"VATID", "DE213119920-EXT"},
			{"EC_ORG_ID", "9999999999999999999"},
			{"openEPD", "extended.woodproductssa.it"}
		};

		var ids = Contacts.getEpdEntityIds(contact);
		assertNotNull(ids);
		assertEquals(expected.length, ids.size());

		for (int i = 0; i < expected.length; i++) {
			var id = ids.get(i);
			assertEquals(expected[i][0], id.getType());
			assertEquals(expected[i][1], id.getValue());
		}
	}

	@Test
	public void testWriteEntityIds() {
		var contact = new Contact();
		Contacts.withEpdEntityIds(contact)
			.add(new EpdEntityId()
				.withType("TIN")
				.withValue("888888"));

		byte[] bytes;
		try (var bos = new ByteArrayOutputStream()) {
			Xml.write(contact, bos);
			bytes = bos.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		contact = Xml.read(Contact.class, bytes);
		var ids = Contacts.getEpdEntityIds(contact);
		assertEquals(1, ids.size());
		assertEquals("TIN", ids.getFirst().getType());
		assertEquals("888888", ids.getFirst().getValue());
	}
}

