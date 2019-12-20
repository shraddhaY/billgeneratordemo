package com.example.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	public static final String DATE_FORMAT_STR = "dd/MM/yyyy";
	
	public static long now() {
		return new Date().getTime();
	}
	
	public static String getFormattedDate(long timeInMilliSeconds) {
		Calendar calendar = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STR);
		TimeZone tz = TimeZone.getTimeZone("IST");
		formatter.setTimeZone(tz);
		calendar.setTimeInMillis(timeInMilliSeconds);
		return formatter.format(calendar.getTime());
	}
}
