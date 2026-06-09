package org.openlca.ilcd.processes.epd;

import org.openlca.commons.Copyable;
import org.openlca.ilcd.Vocab;
import org.openlca.ilcd.util.Val;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public final class EpdEolData implements Copyable<EpdEolData> {

	@XmlAttribute(name = "scenario", namespace = Vocab.EPD_2024)
	private String scenario;

	@XmlElement(name = "collection", namespace = Vocab.EPD_2024)
	private WasteCollection collection;

	@XmlElement(name = "recovery", namespace = Vocab.EPD_2024)
	private WasteRecovery recovery;

	@XmlElement(name = "disposal", namespace = Vocab.EPD_2024)
	private WasteDisposal disposal;

	// region getters

	public String getScenario() {
		return scenario;
	}

	public WasteCollection getCollection() {
		return collection;
	}

	public WasteRecovery getRecovery() {
		return recovery;
	}

	public WasteDisposal getDisposal() {
		return disposal;
	}

	// endregion

	// region setters

	public EpdEolData withScenario(String scenario) {
		this.scenario = scenario;
		return this;
	}

	public EpdEolData withCollection(WasteCollection collection) {
		this.collection = collection;
		return this;
	}

	public WasteCollection withCollection() {
		if (collection == null) {
			collection = new WasteCollection();
		}
		return collection;
	}

	public EpdEolData withRecovery(WasteRecovery recovery) {
		this.recovery = recovery;
		return this;
	}

	public WasteRecovery withRecovery() {
		if (recovery == null) {
			recovery = new WasteRecovery();
		}
		return recovery;
	}

	public EpdEolData withDisposal(WasteDisposal disposal) {
		this.disposal = disposal;
		return this;
	}

	public WasteDisposal withDisposal() {
		if (disposal == null) {
			disposal = new WasteDisposal();
		}
		return disposal;
	}

	// endregion

	@Override
	public EpdEolData copy() {
		var copy = new EpdEolData();
		copy.withScenario(scenario);
		Val.copy(collection, copy::withCollection);
		Val.copy(recovery, copy::withRecovery);
		Val.copy(disposal, copy::withDisposal);
		return copy;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class WasteCollection implements Copyable<WasteCollection> {

		@XmlAttribute(name = "separate", namespace = Vocab.EPD_2024)
		private Double separate;

		@XmlAttribute(name = "withMixedWaste", namespace = Vocab.EPD_2024)
		private Double withMixedWaste;

		// region getters

		public Double getSeparate() {
			return separate;
		}

		public Double getWithMixedWaste() {
			return withMixedWaste;
		}

		// endregion

		// region setters

		public WasteCollection withSeparate(Double separate) {
			this.separate = separate;
			return this;
		}

		public WasteCollection withWithMixedWaste(Double withMixedWaste) {
			this.withMixedWaste = withMixedWaste;
			return this;
		}

		// endregion

		@Override
		public WasteCollection copy() {
			var copy = new WasteCollection();
			copy.withSeparate(separate);
			copy.withWithMixedWaste(withMixedWaste);
			return copy;
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class WasteRecovery implements Copyable<WasteRecovery> {

		@XmlAttribute(name = "reuse", namespace = Vocab.EPD_2024)
		private Double reuse;

		@XmlAttribute(name = "recycling", namespace = Vocab.EPD_2024)
		private Double recycling;

		@XmlAttribute(name = "energyRecovery", namespace = Vocab.EPD_2024)
		private Double energyRecovery;

		// region getters

		public Double getReuse() {
			return reuse;
		}

		public Double getRecycling() {
			return recycling;
		}

		public Double getEnergyRecovery() {
			return energyRecovery;
		}

		// endregion

		// region setters

		public WasteRecovery withReuse(Double reuse) {
			this.reuse = reuse;
			return this;
		}

		public WasteRecovery withRecycling(Double recycling) {
			this.recycling = recycling;
			return this;
		}

		public WasteRecovery withEnergyRecovery(Double energyRecovery) {
			this.energyRecovery = energyRecovery;
			return this;
		}

		// endregion

		@Override
		public WasteRecovery copy() {
			var copy = new WasteRecovery();
			copy.withReuse(reuse);
			copy.withRecycling(recycling);
			copy.withEnergyRecovery(energyRecovery);
			return copy;
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class WasteDisposal implements Copyable<WasteDisposal> {

		@XmlAttribute(name = "finalDeposition", namespace = Vocab.EPD_2024)
		private Double finalDeposition;

		// region getters

		public Double getFinalDeposition() {
			return finalDeposition;
		}

		// endregion

		// region setters

		public WasteDisposal withFinalDeposition(Double finalDeposition) {
			this.finalDeposition = finalDeposition;
			return this;
		}

		// endregion

		@Override
		public WasteDisposal copy() {
			var copy = new WasteDisposal();
			copy.withFinalDeposition(finalDeposition);
			return copy;
		}
	}
}
