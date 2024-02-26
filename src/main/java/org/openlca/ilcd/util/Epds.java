package org.openlca.ilcd.util;

import org.openlca.ilcd.commons.Ref;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.processes.epd.EpdContentDeclaration;
import org.openlca.ilcd.processes.epd.EpdInfoExtension;
import org.openlca.ilcd.processes.epd.EpdModule;
import org.openlca.ilcd.processes.epd.EpdSafetyMargins;
import org.openlca.ilcd.processes.epd.EpdScenario;
import org.openlca.ilcd.processes.epd.EpdSubType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collections;
import java.util.List;

public final class Epds {

	private Epds() {
	}

	public static EpdInfoExtension getInfoExtension(Process p) {
		var info = Processes.getDataSetInfo(p);
		return info != null
			? info.getEpdExtension()
			: null;
	}

	public static EpdSafetyMargins getSafetyMargins(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getSafetyMargins()
			: null;
	}

	public static List<EpdScenario> getScenarios(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getScenarios()
			: Collections.emptyList();
	}

	public static List<EpdModule> getModules(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getModules()
			: Collections.emptyList();
	}

	public static EpdContentDeclaration getContentDeclaration(Process p) {
		var ext = getInfoExtension(p);
		return ext != null
			? ext.getContentDeclaration()
			: null;
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

	public static XMLGregorianCalendar getPublicationDate(Process p) {
		var t = Processes.getTime(p);
		if (t == null)
			return null;
		var ext = t.withEpdExtension();
		return ext != null
			? ext.getPublicationDate()
			: null;
	}

	public static Ref getOriginalEpd(Process p) {
		var rep = Processes.getRepresentativeness(p);
		if (rep == null)
			return null;
		var ext = rep.getEpdExtension();
		return ext != null
			? ext.getOriginalEpd()
			: null;
	}
}
