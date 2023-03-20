package org.openelisglobal.qaframework.automation.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

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

	public static String getPdfContent(String url) throws IOException {

		URL pdfURL = new URL(url);
		InputStream is = pdfURL.openStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		PDDocument doc = PDDocument.load(bis);
		int pages = doc.getNumberOfPages();

		PDFTextStripper strip = new PDFTextStripper();
		strip.setStartPage(1);
		strip.setEndPage(2);

		String stripText = strip.getText(doc);

		System.out.println(stripText);

		doc.close();
		return stripText;
	}

	public static String generateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0 : new Random()
				.nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
				+ (int) Math.pow(10, charLength - 1));
	}
}
