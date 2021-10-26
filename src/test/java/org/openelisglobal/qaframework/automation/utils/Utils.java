package org.openelisglobal.qaframework.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
	
	public static String getFutureDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 3);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(cal.getTime());
	}
	
	public static String getPastDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -3);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(cal.getTime());
	}
	
	public static String generateDobYearFromAge(String age) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int currentAge = Integer.parseInt(age.trim());
		cal.add(Calendar.YEAR, -currentAge);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String strYear = formatter.format(cal.getTime());
		return strYear;
	}
}
