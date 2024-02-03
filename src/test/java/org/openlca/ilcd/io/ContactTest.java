package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.Tests;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.contacts.DataSetInfo;
import org.openlca.ilcd.util.Categories;
import org.openlca.ilcd.util.Contacts;

import static org.junit.Assert.*;

public class ContactTest {

	private Contact contact;
	private DataSetInfo info;

	@Before
	public void setup() throws Exception {
		contact = Tests.read(Contact.class, "contact.xml");
		info = Contacts.getDataSetInfo(contact);
		assertNotNull(info);
	}

	@Test
	public void testUUID() {
		assertEquals("177ca340-ffa2-11da-92e3-0800200c9a66", info.getUUID());
	}

	@Test
	public void testGetShortName() {
		assertEquals(
			"IISI Review panel 2000", LangString.getFirst(info.getShortName()));
	}

	@Test
	public void testGetName() {
		assertEquals(
			"B. P. Weidema, A. Inaba, G. A. Keoleian",
			LangString.getFirst(info.getName()));
	}

	@Test
	public void testCategoryPath() {
		String[] path = Categories.getPath(contact);
		assertEquals(path.length, 1);
		assertEquals("Working groups within organisation", path[0]);
	}

	@Test
	public void testGetContactAddress() {
		assertEquals(
			"Rue Colonel Bourg 120 B-1140 Brussels, Belgium",
			LangString.getFirst(info.getContactAddress()));
	}

	@Test
	public void testGetTelephone() {
		assertEquals("+32 2 702 89 00", info.getTelephone());
	}

	@Test
	public void testGetTelefax() {
		assertEquals("+32 2 702 88 99", info.getTelefax());
	}

	@Test
	public void testGetWebSite() {
		assertEquals("www.worldsteel.org", info.getWebSite());
	}

	@Test
	public void testGetCentralContactPoint() {
		assertEquals(
			"Rue Colonel Bourg 120 B-1140 Brussels, Belgium",
			LangString.getFirst(info.getCentralContactPoint()));
	}

}
