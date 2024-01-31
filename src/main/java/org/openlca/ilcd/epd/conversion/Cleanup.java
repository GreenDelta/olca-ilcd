package org.openlca.ilcd.epd.conversion;

import org.openlca.ilcd.commons.Other;
import org.openlca.ilcd.commons.Time;
import org.openlca.ilcd.epd.model.EpdDataSet;
import org.openlca.ilcd.processes.DataGenerator;
import org.openlca.ilcd.processes.Geography;
import org.openlca.ilcd.processes.Location;
import org.openlca.ilcd.processes.Technology;
import org.openlca.ilcd.util.Processes;
import org.openlca.ilcd.util.Strings;

/**
 * Remove empty elements so that the data set validation is happy.
 */
class Cleanup {

	static void on(EpdDataSet epd) {
		if (epd == null)
			return;

		var info = Processes.getProcessInfo(epd.process);
		if (info != null && isEmpty(info.getTime())) {
			info.withTime(null);
		}
		if (info != null && isEmpty(info.getGeography())) {
			info.withGeography(null);
		}
		if (info != null && isEmpty(info.getTechnology())) {
			info.withTechnology(null);
		}

		var adminInfo = Processes.getAdminInfo(epd.process);

		// bug #59, remove empty commissioner and goal types
		if (adminInfo != null && adminInfo.getCommissionerAndGoal() != null) {
			var comGoal = adminInfo.getCommissionerAndGoal();
			comGoal.trim();
			if (comGoal.isEmpty()) {
				adminInfo.withCommissionerAndGoal(null);
			}
		}

		if (adminInfo != null && isEmpty(adminInfo.getDataGenerator())) {
			adminInfo.withDataGenerator(null);
		}
	}

	private static boolean isEmpty(Time time) {
		if (time == null)
			return true;
		return time.getDescription().isEmpty()
			&& time.getReferenceYear() == null
			&& time.getValidUntil() == null
			&& isEmpty(time.getOther());
	}

	private static boolean isEmpty(Geography geography) {
		if (geography == null)
			return true;
		return isEmpty(geography.getLocation())
			&& geography.getSubLocations().isEmpty()
			&& isEmpty(geography.getOther());
	}

	private static boolean isEmpty(Technology technology) {
		if (technology == null)
			return true;
		return technology.getApplicability().isEmpty()
			&& technology.getDescription().isEmpty()
			&& technology.getIncludedProcesses().isEmpty()
			&& technology.getPictogram() == null
			&& technology.getPictures().isEmpty();
	}

	private static boolean isEmpty(Location location) {
		if (location == null)
			return true;
		return Strings.nullOrEmpty(location.getCode())
			&& location.getDescription().isEmpty()
			&& isEmpty(location.getOther());
	}

	private static boolean isEmpty(DataGenerator generator) {
		if (generator == null)
			return true;
		return generator.getContacts().isEmpty()
			&& isEmpty(generator.getOther());
	}

	private static boolean isEmpty(Other other) {
		return other == null || other.getAny().isEmpty();
	}
}
