package org.openlca.ilcd.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Iterator;

import org.openlca.ilcd.commons.IDataSet;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.sources.FileRef;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.util.DataSets;
import org.openlca.ilcd.util.Sources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStore implements DataStore {

	private final File rootDir;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public FileStore(String pathToFolder) {
		this(new File(pathToFolder));
	}

	public FileStore(File rootDir) {
		log.trace("Create file store {}", rootDir);
		this.rootDir = new File(rootDir, "ILCD");
		checkRootDir();
	}

	private void checkRootDir() {
		log.trace("Check root directory {}", rootDir);
		if (!rootDir.exists()) {
			rootDir.mkdirs();
		} else if (!rootDir.isDirectory()) {
			throw new IllegalArgumentException("The file " + rootDir
					+ " is not a directory.");
		}
	}

	public void prepareFolder() {
		log.trace("Prepare ILCD folder {}", rootDir);
		ILCDFolder folder = new ILCDFolder(rootDir);
		try {
			folder.makeFolder();
		} catch (Exception e) {
			String message = "Cannot create ILCD folder "
					+ rootDir.getAbsolutePath();
			log.error(message, e);
			throw new RuntimeException(message);
		}
	}

	public File getRootFolder() {
		return rootDir;
	}

	@Override
	public <T extends IDataSet> T get(Class<T> type, String id) {
		log.trace("Get {} for id {} from file", type, id);
		try {
			File file = getFile(type, id);
			if (file != null) {
				log.trace("Unmarshal from file {}", file);
				return Xml.read(type, file);
			}
			log.trace("No file found, return null");
			return null;
		} catch (Exception e) {
			String message = "Cannot unmarshal file.";
			log.error(message, e);
			throw new RuntimeException(message);
		}
	}

	@Override
	public InputStream getExternalDocument(String sourceId, String fileName) {
		log.trace("Get external document {} for source {}", fileName, sourceId);
		try {
			File docDir = new File(rootDir, "external_docs");
			File file = new File(docDir, fileName);
			if (!file.exists())
				return null;
			else
				return new FileInputStream(file);
		} catch (Exception e) {
			throw new RuntimeException("failed to open file " + fileName, e);
		}
	}

	/**
	 * Get the file for the given reference. The file may exist or not. Returns
	 * null if the reference does not contain a valid file location.
	 */
	public File getExternalDocument(FileRef ref) {
		String name = Sources.getFileName(ref);
		if (name == null)
			return null;
		File docDir = new File(rootDir, "external_docs");
		if (!docDir.exists())
			docDir.mkdirs();
		return new File(docDir, name);
	}

	@Override
	public void put(IDataSet ds) {
		var uid = DataSets.getUUID(ds);
		if (uid == null) {
			log.error("Could not read UUID from dataset {}", ds);
			return;
		}
		log.trace("Store {} in file.", ds);
		try {
			File file = newFile(ds.getClass(), uid);
			Xml.write(ds, file);
		} catch (Exception e) {
			String message = "Cannot store in file";
			log.error(message, e);
			throw new RuntimeException(message);
		}
	}

	public void put(Source source, File[] files) {
		log.trace("Store source {} with files", source);
		put(source);
		if (files == null || files.length == 0)
			return;
		try {
			File folder = new File(rootDir, "external_docs");
			if (!folder.exists())
				folder.mkdirs();
			for (File file : files) {
				File newFile = new File(folder, file.getName());
				Files.copy(file.toPath(), newFile.toPath());
			}
		} catch (Exception e) {
			String message = "Cannot store source files";
			log.error(message, e);
			throw new RuntimeException(message);
		}
	}

	@Override
	public <T extends IDataSet> boolean delete(Class<T> type, String id) {
		log.trace("Delete file if exists for class {} with id {}", type, id);
		File file = getFile(type, id);
		if (file == null)
			return false;
		else {
			boolean b = file.delete();
			log.trace("Deleted={}", b);
			return b;
		}
	}

	@Override
	public <T extends IDataSet> Iterable<T> iter(Class<T> type) {
		File folder = getFolder(type);
		return () -> new FileIterator<>(type, folder);
	}

	@Override
	public <T extends IDataSet> boolean contains(Class<T> type, String id) {
		log.trace("Contains file for class {} with id {}", type, id);
		File file = getFile(type, id);
		boolean contains = file != null && file.exists();
		log.trace("Contains={}", contains);
		return contains;
	}

	private File newFile(Class<?> clazz, String id) {
		log.trace("Make file for class {} with id {}", clazz, id);
		File dir = getFolder(clazz);
		File file = new File(dir, id + ".xml");
		log.trace("New file: {}", file);
		return file;
	}

	public File getFile(Ref ref) {
		if (ref == null
			|| ref.getType() == null
			|| ref.getUUID() == null)
			return null;
		return getFile(ref.getDataSetClass(), ref.getUUID());
	}

	public File getFile(Class<?> clazz, String id) {
		log.trace("Find file for class {} with id {}", clazz, id);
		if (clazz == null || id == null)
			return null;
		File dir = getFolder(clazz);
		File file = null;
		for (File f : dir.listFiles()) {
			if (f.getName().toLowerCase().contains(id.toLowerCase())) {
				file = f;
				break;
			}
		}
		log.trace("Return file: {}", file);
		return file;
	}

	public File getFolder(Class<?> clazz) {
		String name = Dir.get(clazz);
		File folder = findFolder(name, rootDir);
		if (folder == null) {
			folder = new File(rootDir, name);
			folder.mkdirs();
		}
		return folder;
	}

	private File findFolder(String name, File dir) {
		log.trace("Search folder {} in {}", name, dir);
		File folder = null;
		if (dir.getName().equalsIgnoreCase(name)) {
			folder = dir;
		} else {
			File[] files = dir.listFiles();
			int i = 0;
			while (folder == null && i < files.length) {
				if (files[i].isDirectory()) {
					folder = findFolder(name, files[i]);
				}
				i++;
			}
		}
		return folder;
	}

	@Override
	public void close() {
	}

	private static class FileIterator<T> implements Iterator<T> {

		final Class<T> type;
		int idx = 0;
		File[] files;

		FileIterator(Class<T> type, File folder) {
			this.type = type;
			if (folder != null && folder.isDirectory()) {
				files = folder.listFiles();
			}
		}

		@Override
		public boolean hasNext() {
			return files != null && idx < files.length;
		}

		@Override
		public T next() {
			try {
				File file = files[idx];
				idx++;
				return Xml.read(type, file);
			} catch (Exception e) {
				throw new RuntimeException("failed to load unmarshal XML file",
						e);
			}
		}
	}
}
