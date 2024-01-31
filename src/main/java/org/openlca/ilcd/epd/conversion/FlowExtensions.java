package org.openlca.ilcd.epd.conversion;

import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.epd.model.EpdProduct;
import org.openlca.ilcd.epd.model.MaterialPropertyValue;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.util.Flows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import java.util.List;

public class FlowExtensions {

	public static EpdProduct read(Flow flow) {
		EpdProduct product = new EpdProduct();
		if (flow == null)
			return product;
		product.flow = flow;
		readInfoExtension(product);
		readMethodExtension(product);
		return product;
	}

	private static void readInfoExtension(EpdProduct p) {
		Other extension = getInfoExtension(p.flow, false);
		if (extension == null)
			return;
		MatML matML = new MatML(extension);
		List<MaterialPropertyValue> values = matML.readValues();
		p.properties.addAll(values);
		p.genericFlow = RefExtension.readFrom(extension, "isA").orElse(null);
	}

	private static void readMethodExtension(EpdProduct p) {
		Other extension = getMethodExtension(p.flow, false);
		if (extension == null)
			return;
		Element elem = Dom.getElement(extension, "vendorSpecificProduct");
		if (elem != null) {
			try {
				p.vendorSpecific = Boolean
					.parseBoolean(elem.getTextContent());
			} catch (Exception e) {
				var log = LoggerFactory.getLogger(FlowExtensions.class);
				log.error("vendorSpecificProduct contains not a boolean", e);
			}
		}
		p.vendor = RefExtension.readFrom(
			extension, "referenceToVendor").orElse(null);
		p.documentation = RefExtension.readFrom(
			extension, "referenceToSource").orElse(null);
	}

	/**
	 * Writes the EPD extensions of the given product to the underlying ILCD
	 * flow data set.
	 */
	public static void write(EpdProduct p) {
		if (p == null || p.flow == null)
			return;
		try {
			writeInfoExtension(p);
			writeMethodExtension(p);
		} catch (Exception e) {
			Logger log = LoggerFactory.getLogger(FlowExtensions.class);
			log.error("failed to write flow properties for " + p, e);
		}
	}

	private static void writeInfoExtension(EpdProduct p) {

		Other ext = getInfoExtension(p.flow, true);
		p.flow.withFlowInfo()
			.withDataSetInfo()
			.withOther(ext);
		RefExtension.writeTo(ext, "isA", p.genericFlow);
		var matML = new MatML(ext);
		if (p.properties.isEmpty()) {
			matML.clear();
		} else {
			var flowName = Flows.getFlowName(p.flow);
			var name = flowName != null
				? LangString.getFirst(flowName.getBaseName())
				: "";
			matML.createStructure(name);
			for (MaterialPropertyValue value : p.properties) {
				matML.append(value);
			}
		}
	}

	private static void writeMethodExtension(EpdProduct p) {
		var extension = getMethodExtension(p.flow, true);
		writeVendorSpecificTag(p, extension);
		RefExtension.writeTo(extension, "referenceToVendor", p.vendor);
		RefExtension.writeTo(extension, "referenceToSource", p.documentation);
	}

	private static void writeVendorSpecificTag(EpdProduct p, Other ext) {
		String tag = "vendorSpecificProduct";
		Element e = Dom.getElement(ext, tag);
		if (e == null) {
			e = Dom.createElement(Vocab.NS_EPD, tag);
			ext.withAny().add(e);
		}
		e.setTextContent(Boolean.toString(p.vendorSpecific));
	}

	private static Other getInfoExtension(Flow flow, boolean create) {

		var flowInfo = create
			? flow.withFlowInfo()
			: flow.getFlowInfo();
		if (flowInfo == null)
			return null;

		var dataInfo = create
			? flowInfo.withDataSetInfo()
			: flowInfo.getDataSetInfo();
		if (dataInfo == null)
			return null;

		return create
			? dataInfo.withOther()
			: dataInfo.getOther();
	}

	private static Other getMethodExtension(Flow flow, boolean create) {
		var mav = create
			? flow.withModelling()
			: flow.getModelling();
		if (mav == null)
			return null;

		var method = create
			? mav.withInventoryMethod()
			: mav.getInventoryMethod();
		if (method == null)
			return null;

		return create
			? method.withOther()
			: method.getOther();
	}
}
