package com.iscard.club.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static String getTime(long dateTime, String type) {

		SimpleDateFormat sdf = new SimpleDateFormat(type);

		Date date = new Date(dateTime);

		return sdf.format(date);
	}
	
	public static long stringToDate(String dateString, String type) {

		SimpleDateFormat df = new SimpleDateFormat(type);

		Date date = null;

		try {

			date = df.parse(dateString);

		} catch (Exception ex) {

			System.out.println(ex.getMessage());

		}

		if (date == null) {

			return 0;

		}

		return date.getTime();

	}

}
