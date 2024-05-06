package org.openlca.ilcd.epd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.openlca.ilcd.processes.Process;

import jakarta.xml.bind.JAXB;

public enum EpdProfiles {

	EN_15804("EN 15804"),

	/**
	 * The indicator profile for EN 15804+A2 with indicator references based
	 * on EF 3.0 for impact indicators and InData 2018 for inventory indicators.
	 */
	EN_15804_A2_EF30("EN 15804+A2 (EF 3.0)");

	private final String label;

	private static final Map<String, EpdProfile> cache = new HashMap<>();

	EpdProfiles(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static EpdProfile getDefault() {
		return EN_15804_A2_EF30.get();
	}

	public static EpdProfile of(Process epd) {
		if (epd == null)
			return null;

		// make sure to fill the cache with the default profiles
		for (var p : values()) {
			p.get();
		}

		for (var p : cache.values()) {
			if (matches(epd, p))
				return p;
		}
		return null;
	}

	/**
	 * Returns {@code true}, when the given EPD matches the indicators of the
	 * given profile. This is the case, when all indicators used in the EPD
	 * have an ID of a corresponding indicator in the profile.
	 */
	public static boolean matches(Process epd, EpdProfile profile) {
		if (epd == null || profile == null)
			return false;
		var ids = profile.getIndicators().stream()
			.filter(i -> i.getRef() != null && i.getRef().getUUID() != null)
			.map(i -> i.getRef().getUUID())
			.collect(Collectors.toSet());
		for (var r : EpdIndicatorResult.allOf(epd)) {
			var id = r.indicator() != null
				? r.indicator().getUUID()
				: null;
			if (id == null || !ids.contains(id))
				return false;
		}
		return true;
	}

	public static void add(EpdProfile profile) {
		if (profile == null || profile.getId() == null)
			return;
		cache.put(profile.getId(), profile);
	}

	public static EpdProfile get(String id) {
		return cache.get(id);
	}

	public static EpdProfile read(InputStream stream) {
		return JAXB.unmarshal(stream, EpdProfile.class);
	}

	public static EpdProfile read(File file) {
		try (var stream = new FileInputStream(file)) {
			return read(stream);
		} catch (IOException e) {
			throw new RuntimeException("failed to read profile from " + file, e);
		}
	}

	public static void write(EpdProfile profile, OutputStream stream) {
		JAXB.marshal(profile, stream);
	}

	public static void write(EpdProfile profile, File file) {
		JAXB.marshal(profile, file);
	}

	public EpdProfile get() {
		var p = cache.get(name());
		if (p != null)
			return p;
		var resource = name() + ".xml";
		try (var stream = getClass().getResourceAsStream(resource)) {
			Objects.requireNonNull(stream, resource + " is null");
			p = JAXB.unmarshal(stream, EpdProfile.class);
			cache.put(name(), p);
			return p;
		} catch (Exception e) {
			throw new RuntimeException("failed to read profile from " + resource, e);
		}
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
