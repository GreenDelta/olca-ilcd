package org.openlca.ilcd.io;

import org.junit.Before;
import org.junit.Test;
import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

import static org.junit.Assert.*;

public class XmlTest {

	private final List<IDataSet> dataSets = new ArrayList<>();

	@Before
	public void makeInstances() {
		dataSets.add(makeProcess());
		dataSets.add(new Flow());
		dataSets.add(new FlowProperty());
		dataSets.add(new UnitGroup());
		dataSets.add(new Source());
		dataSets.add(new Contact());
	}

	@Test
	public void testFileIO() {
		runTests((dataSet, file) -> {
			Xml.write(dataSet, file);
			return Xml.read(dataSet.getClass(), file);
		});
	}

	@Test
	public void testStreamIO() {
		runTests((dataSet, file) -> {
			try (var os = new FileOutputStream(file)) {
				Xml.write(dataSet, os);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			try (var in = new FileInputStream(file)) {
				return Xml.read(dataSet.getClass(), in);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Test
	public void testReaderWriterIO() {
		runTests((dataSet, file) -> {
			try (var writer = new FileWriter(file)) {
				Xml.write(dataSet, writer);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			try (var reader = new FileReader(file)) {
				return Xml.read(dataSet.getClass(), reader);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void runTests(BiFunction<IDataSet, File, IDataSet> fun) {
		try {
			for (var original : dataSets) {
				var file = Files.createTempFile("ilcd_", ".xml").toFile();
				Object copy = fun.apply(original, file);
				assertEquals(original.getClass(), copy.getClass());
				assertTrue(file.delete());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Process makeProcess() {
		var process = new Process();
		process.withProcessInfo()
			.withDataSetInfo()
			.withUUID(UUID.randomUUID().toString());
		return process;
	}
}
