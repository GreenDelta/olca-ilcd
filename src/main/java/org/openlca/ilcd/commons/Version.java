package org.openlca.ilcd.commons;

public record Version(int major, int minor, int update)
	implements Comparable<Version> {

	public static Version of(String v) {
		if (v == null)
			return new Version(0, 0, 0);
		var parts = v.split("\\.");
		return new Version(
			parsePart(parts, 0),
			parsePart(parts, 1),
			parsePart(parts, 2));
	}

	private static int parsePart(String[] parts, int i) {
		if (i >= parts.length)
			return 0;
		var part = parts[0];
		if (part == null)
			return 0;
		try {
			return Integer.parseInt(part.strip(), 10);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public String toString() {
		return String.format("%02d.%02d.%03d", major, minor, update);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Version other))
			return false;
		return this.major == other.major
			&& this.minor == other.minor
			&& this.update == other.update;
	}

	@Override
	public int compareTo(Version other) {
		if (this.major != other.major)
			return this.major - other.major;
		return this.minor != other.minor
			? this.minor - other.minor
			: this.update - other.update;
	}

	public boolean isHigherThan(Version other) {
		return this.compareTo(other) > 0;
	}

	public boolean isLowerThan(Version other) {
		return this.compareTo(other) < 0;
	}
}
