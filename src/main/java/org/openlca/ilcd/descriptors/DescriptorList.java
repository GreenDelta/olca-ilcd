
package org.openlca.ilcd.descriptors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.commons.XmlRoot;

import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
	name = "DataSetListType",
	namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI",
	propOrder = {"descriptors"}
)
public class DescriptorList implements XmlRoot, Copyable<DescriptorList> {

	@XmlElements({
		@XmlElement(name = "LCIAMethod", type = ImpactMethodDescriptor.class,
			namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/LCIAMethod"),
		@XmlElement(name = "process", type = ProcessDescriptor.class,
			namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Process"),
		@XmlElement(name = "contact", type = ContactDescriptor.class,
			namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Contact"),
		@XmlElement(name = "source", type = SourceDescriptor.class,
			namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source"),
		@XmlElement(name = "flow", type = FlowDescriptor.class,
			namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Flow"),
		@XmlElement(name = "unitGroup", type = UnitGroupDescriptor.class,
			namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/UnitGroup"),
		@XmlElement(name = "flowProperty", type = FlowPropertyDescriptor.class,
			namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/FlowProperty")
	})
	private List<Descriptor<?>> descriptors;

	@XmlAttribute(
		name = "totalSize",
		namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private int totalSize;

	@XmlAttribute(
		name = "startIndex",
		namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private int startIndex;

	@XmlAttribute(
		name = "pageSize",
		namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private int pageSize;

	// region getters

	public List<Descriptor<?>> getDescriptors() {
		return descriptors != null ? descriptors : Collections.emptyList();
	}

	public int getTotalSize() {
		return totalSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	// endregion

	// region setters

	public DescriptorList withDescriptors(List<Descriptor<?>> descriptors) {
		this.descriptors = descriptors;
		return this;
	}

	public DescriptorList withTotalSize(int totalSize) {
		this.totalSize = totalSize;
		return this;
	}

	public DescriptorList withStartIndex(int startIndex) {
		this.startIndex = startIndex;
		return this;
	}

	public DescriptorList withPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public List<Descriptor<?>> withDescriptors() {
		if (descriptors == null) {
			descriptors = new ArrayList<>();
		}
		return descriptors;
	}

	// endregion

	@Override
	public DescriptorList copy() {
		var copy = new DescriptorList();
		if (descriptors != null) {
			var target = copy.withDescriptors();
			for (var d : descriptors) {
				target.add(d.copy());
			}
		}
		copy.withTotalSize(totalSize);
		copy.withStartIndex(startIndex);
		copy.withPageSize(pageSize);
		return copy;
	}

	@Override
	public JAXBElement<DescriptorList> toElement() {
		var qname = new QName(
			"http://www.ilcd-network.org/ILCD/ServiceAPI", "dataSetList");
		return new JAXBElement<>(qname, DescriptorList.class, null, this);
	}
}
