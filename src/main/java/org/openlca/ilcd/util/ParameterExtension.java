package org.openlca.ilcd.util;

import javax.xml.namespace.QName;

import org.openlca.ilcd.processes.Parameter;

public class ParameterExtension {

	private final String SCOPE = "scope";
	private final Parameter parameter;

	public ParameterExtension(Parameter parameter) {
		this.parameter = parameter;
	}

	public void setScope(String scope) {
		if (parameter == null || scope == null)
			return;
		QName qName = Extensions.getQName(SCOPE);
		parameter.withOtherAttributes().put(qName, scope);
	}

	public String getScope() {
		if (parameter == null)
			return null;
		QName qName = Extensions.getQName(SCOPE);
		return parameter.getOtherAttributes().get(qName);
	}

}
