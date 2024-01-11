package org.openlca.ilcd.units;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.openlca.ilcd.commons.Copyable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitsType", propOrder = {
		"units"
})
public class UnitList implements Copyable<UnitList> {

	@XmlElement(name = "unit")
	public final List<Unit> units = new ArrayList<>();

	@Override
	public UnitList copy() {
		UnitList clone = new UnitList();
		for (Unit u : units) {
			if (u == null)
				continue;
			clone.units.add(u.copy());
		}
		return clone;
	}
}
