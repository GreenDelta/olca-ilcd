package org.openlca.ilcd.util;

import org.openlca.ilcd.methods.DataSetInfo;
import org.openlca.ilcd.methods.ImpactMethod;

@Deprecated
public class MethodBag implements IBag<ImpactMethod> {

	private final ImpactMethod method;

	public MethodBag(ImpactMethod method) {
		this.method = method;
	}

	@Override
	public ImpactMethod getValue() {
		return method;
	}

	@Override
	public String getId() {
		DataSetInfo info = getDataSetInformation();
		if (info != null)
			return info.uuid;
		return null;
	}

	public String getImpactIndicator() {
		DataSetInfo info = getDataSetInformation();
		if (info != null)
			return info.indicator;
		return null;
	}

	private DataSetInfo getDataSetInformation() {
		if (method.methodInfo != null)
			return method.methodInfo.dataSetInfo;
		return null;
	}

}
