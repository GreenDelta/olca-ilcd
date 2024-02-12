package org.openlca.ilcd.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.descriptors.DataStock;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.util.Val;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"authenticated",
		"user",
		"roles",
		"dataStocks" })
@XmlRootElement(name = "authInfo", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
public class AuthInfo implements Copyable<AuthInfo> {

	@XmlElement(name = "authenticated", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private boolean authenticated;

	@XmlElement(name = "userName", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private String user;

	@XmlElement(name = "role", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<String> roles;

	@XmlElement(name = "dataStock", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<DataStock> dataStocks;

	// region getters

	public boolean isAuthenticated() {
		return authenticated;
	}

	public String getUser() {
		return user;
	}

	public List<String> getRoles() {
		return roles != null ? roles : Collections.emptyList();
	}

	public List<DataStock> getDataStocks() {
		return dataStocks != null ? dataStocks : Collections.emptyList();
	}

	// endregion

	// region setters

	public AuthInfo withAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
		return this;
	}

	public AuthInfo withUser(String user) {
		this.user = user;
		return this;
	}

	public AuthInfo withRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}

	public AuthInfo withDataStocks(List<DataStock> dataStocks) {
		this.dataStocks = dataStocks;
		return this;
	}

	public List<String> withRoles() {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		return roles;
	}

	public List<DataStock> withDataStocks() {
		if (dataStocks == null) {
			dataStocks = new ArrayList<>();
		}
		return dataStocks;
	}

	// endregion

	@Override
	public AuthInfo copy() {
		var copy = new AuthInfo();
		copy.withAuthenticated(authenticated);
		copy.withUser(user);
		if (roles != null) {
			copy.withRoles().addAll(roles);
		}
		Val.copy(dataStocks, copy::withDataStocks);
		return copy;
	}
}
