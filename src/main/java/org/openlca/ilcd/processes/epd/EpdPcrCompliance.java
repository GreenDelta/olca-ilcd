package org.openlca.ilcd.processes.epd;

import java.util.ArrayList;
import java.util.List;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdPcrCompliance implements Copyable<EpdPcrCompliance> {

	@XmlAttribute(name = "allocation", namespace = Vocab.EPD_2024)
	private boolean allocation;

	@XmlAttribute(name = "cutOffRules", namespace = Vocab.EPD_2024)
	private boolean cutOffRules;

	@XmlAttribute(name = "upstreamDataDeviatingFromAllocationPrinciples", namespace = Vocab.EPD_2024)
	private boolean upstreamDataDeviating;

	@XmlElement(name = "comment", namespace = Vocab.EPD_2024)
	private List<LangString> comments;

	// region getters

	public boolean isAllocation() {
		return allocation;
	}

	public boolean isCutOffRules() {
		return cutOffRules;
	}

	public boolean isUpstreamDataDeviating() {
		return upstreamDataDeviating;
	}

	public List<LangString> getComments() {
		return comments != null ? comments : List.of();
	}

	// endregion

	// region setters

	public EpdPcrCompliance withAllocation(boolean allocation) {
		this.allocation = allocation;
		return this;
	}

	public EpdPcrCompliance withCutOffRules(boolean cutOffRules) {
		this.cutOffRules = cutOffRules;
		return this;
	}

	public EpdPcrCompliance withUpstreamDataDeviating(boolean b) {
		this.upstreamDataDeviating = b;
		return this;
	}

	public EpdPcrCompliance withComments(List<LangString> comments) {
		this.comments = comments;
		return this;
	}

	public List<LangString> withComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	// endregion

	@Override
	public EpdPcrCompliance copy() {
		var copy = new EpdPcrCompliance();
		copy.withAllocation(allocation);
		copy.withCutOffRules(cutOffRules);
		copy.withUpstreamDataDeviating(upstreamDataDeviating);
		Val.copy(comments, copy::withComments);
		return copy;
	}
}
