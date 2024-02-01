package org.openlca.ilcd.io;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;
import org.openlca.ilcd.processes.Exchange;
import org.openlca.ilcd.util.ExchangeExtension;

import jakarta.xml.bind.JAXB;

import static org.junit.Assert.*;

public class ExchangeExtensionTest {

	@Test
	public void testEmpty() {
		Exchange raw = new Exchange().withId(1);
		Exchange exchange = io(raw);
		assertNotSame(exchange, raw);
		ExchangeExtension extension = new ExchangeExtension(exchange);
		assertNull(extension.getAmount());
		assertNull(extension.getFormula());
		assertNull(extension.getPropertyId());
		assertNull(extension.getUnitId());
		assertFalse(extension.isValid());
	}

	@Test
	public void testFormula() {
		assertEquals("2 * Pi * sqr(r)", extension().getFormula());
	}

	@Test
	public void testUnitId() {
		assertEquals("unit-id", extension().getUnitId());
	}

	@Test
	public void testPropertyId() {
		assertEquals("property-id", extension().getPropertyId());
	}

	@Test
	public void testAmount() {
		assertEquals(42d, extension().getAmount(), 1e-15);
	}

	@Test
	public void testAvoidedProduct() {
		assertTrue(extension().isAvoidedProduct());
	}

	@Test
	public void testDefaultProvider() {
		assertEquals("abc-def", extension().getDefaultProvider());
	}

	private ExchangeExtension extension() {
		Exchange exchange = io(createExchange());
		return new ExchangeExtension(exchange);
	}

	private Exchange createExchange() {
		Exchange exchange = new Exchange()
			.withId(1)
			.withMeanAmount(500);
		ExchangeExtension extension = new ExchangeExtension(exchange);
		extension.setAmount(42);
		extension.setFormula("2 * Pi * sqr(r)");
		extension.setPropertyId("property-id");
		extension.setUnitId("unit-id");
		extension.setDefaultProvider("abc-def");
		extension.setAvoidedProduct(true);
		return exchange;
	}

	private Exchange io(Exchange exchange) {
		StringWriter writer = new StringWriter();
		JAXB.marshal(exchange, writer);
		writer.flush();
		String xml = writer.toString();
		StringReader reader = new StringReader(xml);
		return JAXB.unmarshal(reader, Exchange.class);
	}

}
