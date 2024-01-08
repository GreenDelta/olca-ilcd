package org.openlca.ilcd.io;

import java.util.Objects;
import java.util.UUID;

/**
 * Connection data of an Soda4LCA service.
 */
public class SodaConnection {

	public String uuid;

	/**
	 * The URL of the service end-point.
	 */
	public String url;

	public String user;

	public String password;

	public String dataStockId;

	/**
	 * The short name of the data stock; just used for display information.
	 */
	public String dataStockName;

	public static SodaConnection of(String url) {
		var con = new SodaConnection();
		con.url = url;
		con.uuid = UUID.randomUUID().toString();
		return con;
	}

	@Override
	public String toString() {
		String s = url;
		if (dataStockName != null && !dataStockName.isEmpty()) {
			s += ";" + dataStockName;
		}
		return user != null && !user.isEmpty()
			? user + "@" + s
			: "anonymous@" + s;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof SodaConnection other))
			return false;
		return Objects.equals(this.id(), other.id());
	}

	private String id() {
		if (uuid != null)
			return uuid;
		else
			return toString();
	}

}
