package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.epd.EpdContentDeclaration;
import org.openlca.ilcd.processes.epd.EpdInfoExtension;
import org.openlca.ilcd.processes.epd.EpdModuleEntry;
import org.openlca.ilcd.processes.epd.EpdSafetyMargins;
import org.openlca.ilcd.processes.epd.EpdScenario;
import org.openlca.ilcd.processes.epd.EpdSubType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collections;
import java.util.List;

public final class Epds extends Processes {

	private Epds() {
	}

	public static EpdInfoExtension getInfoExtension(Process p) {
		var info = Processes.getDataSetInfo(p);
		return info != null
			? info.getEpdExtension()
			: null;
	}

	public static EpdInfoExtension withInfoExtension(Process p) {
		return Processes.withDataSetInfo(p).withEpdExtension();
	}

	public static EpdSafetyMargins getSafetyMargins(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getSafetyMargins()
			: null;
	}

	public static EpdSafetyMargins withSafetyMargins(Process p) {
		return withInfoExtension(p).withSafetyMargins();
	}

	public static List<EpdScenario> getScenarios(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getScenarios()
			: Collections.emptyList();
	}

	public static List<EpdScenario> withScenarios(Process p) {
		return withInfoExtension(p).withScenarios();
	}

	public static List<EpdModuleEntry> getModuleEntries(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getModuleEntries()
			: Collections.emptyList();
	}

	public static List<EpdModuleEntry> withModuleEntries(Process p) {
		return withInfoExtension(p).withModuleEntries();
	}

	public static EpdContentDeclaration getContentDeclaration(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getContentDeclaration()
			: null;
	}

	public static EpdContentDeclaration withContentDeclaration(Process p) {
		return withInfoExtension(p).withContentDeclaration();
	}

	public static EpdSubType getSubType(Process p) {
		var m = Processes.getInventoryMethod(p);
		if (m == null)
			return null;
		var ext = m.getEpdExtension();
		return ext != null
			? ext.getSubType()
			: null;
	}

	public static void withSubType(Process p, EpdSubType subType) {
		if (subType != null) {
			Processes.withInventoryMethod(p)
				.withEpdExtension()
				.withSubType(subType);
			return;
		}
		var m = Processes.getInventoryMethod(p);
		if (m != null) {
			m.withEpdExtension(null);
		}
	}

	public static XMLGregorianCalendar getPublicationDate(Process p) {
		var t = Processes.getTime(p);
		if (t == null)
			return null;
		var ext = t.withEpdExtension();
		return ext != null
			? ext.getPublicationDate()
			: null;
	}

	public static void withPublicationDate(Process p, XMLGregorianCalendar date) {
		if (date != null) {
			Processes.withTime(p)
				.withEpdExtension()
				.withPublicationDate(date);
			return;
		}
		var time = Processes.withTime(p);
		if (time != null) {
			time.withEpdExtension(null);
		}
	}

	public static List<Ref> getOriginalEpds(Process p) {
		var rep = Processes.getRepresentativeness(p);
		if (rep == null)
			return Collections.emptyList();
		var ext = rep.getEpdExtension();
		return ext != null
			? ext.getOriginalEpds()
			: Collections.emptyList();
	}

	public static List<Ref> withOriginalEpds(Process p) {
		return Processes.withRepresentativeness(p)
			.withEpdExtension()
			.withOriginalEpds();
	}

	public static List<Ref> getPublishers(Process p) {
		var pub = Processes.getPublication(p);
		if (pub == null)
			return Collections.emptyList();
		var ext = pub.getEpdExtension();
		return ext != null
			? ext.getPublishers()
			: Collections.emptyList();
	}

	public static List<Ref> withPublishers(Process p) {
		return Processes.withPublication(p)
			.withEpdExtension()
			.withPublishers();
	}
}
