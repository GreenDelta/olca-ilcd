package org.openlca.ilcd.methods;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.commons.Copyable;
import org.openlca.ilcd.commons.LangString;
import org.openlca.ilcd.commons.RecommendationLevel;
import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.commons.annotations.FreeText;
import org.openlca.ilcd.util.Val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecommendationType", propOrder = {
		"entities",
		"level",
		"meaning"
})
public class Recommendation implements Copyable<Recommendation> {

	@XmlElement(name = "referenceToEntity")
	private List<Ref> entities;

	private RecommendationLevel level;

	@FreeText
	private List<LangString> meaning;

	// region getters

	public List<Ref> getEntities() {
		return entities != null ? entities : Collections.emptyList();
	}

	public RecommendationLevel getLevel() {
		return level;
	}

	public List<LangString> getMeaning() {
		return meaning != null ? meaning : Collections.emptyList();
	}

	// endregion

	// region setters

	public Recommendation withEntities(List<Ref> entities) {
		this.entities = entities;
		return this;
	}

	public Recommendation withLevel(RecommendationLevel level) {
		this.level = level;
		return this;
	}

	public Recommendation withMeaning(List<LangString> meaning) {
		this.meaning = meaning;
		return this;
	}

	public List<Ref> withEntities() {
		if (entities == null) {
			entities = new ArrayList<>();
		}
		return entities;
	}

	public List<LangString> withMeaning() {
		if (meaning == null) {
			meaning = new ArrayList<>();
		}
		return meaning;
	}

	// endregion

	@Override
	public Recommendation copy() {
		var copy = new Recommendation();
		Val.copy(entities, copy::withEntities);
		copy.withLevel(level);
		Val.copy(meaning, copy::withMeaning);
		return copy;
	}

}
