package org.openlca.ilcd.descriptors;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.openlca.ilcd.commons.Copyable;
import org.openlca.ilcd.util.Strings;
import org.openlca.ilcd.util.Val;

/**
 * Contains the list of category systems that are available in a data
 * repository.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "categorySystems", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
public class CategorySystemList implements Copyable<CategorySystemList> {

	@XmlElement(name = "categorySystem", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	private List<Entry> systems;

	// region getters

	public List<Entry> getSystems() {
		return systems != null ? systems : List.of();
	}

	// endregion

	// region setters

	public CategorySystemList withSystems(List<Entry> systems) {
		this.systems = systems;
		return this;
	}

	public List<Entry> withSystems() {
		if (systems == null) {
			systems = new ArrayList<>();
		}
		return systems;
	}

	// endregion

	@Override
	public CategorySystemList copy() {
		var copy = new CategorySystemList();
		Val.copy(systems, copy::withSystems);
		return copy;
	}

	/**
	 * Convenience method to get the names of all category systems that are
	 * contained in this list.
	 */
	public List<String> getNames() {
		return getSystems()
			.stream()
			.map(Entry::getName)
			.filter(Strings::notEmpty)
			.toList();
	}

	public static class Entry implements Copyable<Entry> {

		@XmlAttribute
		private String name;

		// region getters

		public String getName() {
			return name;
		}

		// endregion

		// region setters

		public Entry withName(String name) {
			this.name = name;
			return this;
		}

		// endregion

		@Override
		public Entry copy() {
			var copy = new Entry();
			copy.withName(name);
			return copy;
		}

	}
}
