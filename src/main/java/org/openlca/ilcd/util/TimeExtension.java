package org.openlca.ilcd.util;

import java.util.Date;

import javax.xml.namespace.QName;

import org.openlca.ilcd.processes.Time;

public class TimeExtension {

	private final Time time;
	private final String START_DATE = "startDate";
	private final String END_DATE = "endDate";

	public TimeExtension(Time time) {
		this.time = time;
	}

	public boolean isValid() {
		return time != null && getStartDate() != null && getEndDate() != null;
	}

	public Date getStartDate() {
		return getDate(START_DATE);
	}

	public void setStartDate(Date date) {
		setDate(date, START_DATE);
	}

	public Date getEndDate() {
		return getDate(END_DATE);
	}

	public void setEndDate(Date date) {
		setDate(date, END_DATE);
	}

	private Date getDate(String attribute) {
		if (time == null)
			return null;
		QName qName = Extensions.getQName(attribute);
		String val = time.getOtherAttributes().get(qName);
		if (val == null)
			return null;
		try {
			long l = Long.parseLong(val);
			return new Date(l);
		} catch (Exception e) {
			return null;
		}
	}

	private void setDate(Date date, String attribute) {
		if (time == null || date == null)
			return;
		long l = date.getTime();
		QName qName = Extensions.getQName(attribute);
		time.withOtherAttributes().put(qName, Long.toString(l));
	}

}
