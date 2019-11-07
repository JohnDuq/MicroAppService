package com.johnduq.microappservice.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	public static String dateToSring(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(TypeDateFormat.COMPLETE_DATE.getCode());
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static Date stringToDate(String date, String format) {
		Date dateFormated = null;
		try {
			dateFormated = new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormated;
	}
	
	public static Date getColombianDate() {
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long gmtTime = cSchedStartCal.getTime().getTime();

		long timezoneAlteredTime = gmtTime + TimeZone.getTimeZone("America/Bogota").getRawOffset();
		Calendar cSchedStartCal1 = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
		cSchedStartCal1.setTimeInMillis(timezoneAlteredTime);
		
		return cSchedStartCal1.getTime();
	}

}
