package com.sadullaev.htw.ai.bachelor.lsfCrawler.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateUtils {
	
	final static DateTimeFormatter dateTimeFormatterLsf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	final static DateTimeFormatter dateTimeFormatterSql = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
	public static List<String> getDatesBetweenTwoDates(String startD, String endD) { 
		
		LocalDate startDate = LocalDate.parse(startD, dateTimeFormatterLsf);
		LocalDate endDate = LocalDate.parse(endD, dateTimeFormatterLsf);
		
		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate); 
		List<String> resultDates = IntStream.iterate(0, i -> i + 1)
			      .limit(numOfDaysBetween+1)
			      .mapToObj(i -> startDate.plusDays(i).format(dateTimeFormatterLsf))
			      .collect(Collectors.toList()); 
		
		return resultDates;
	}
	
	public static List<String> getDatesBetweenNowAndDate(String endD) { 
		LocalDate localDate = LocalDate.now();
		String now = localDate.format(dateTimeFormatterLsf);
		
		return getDatesBetweenTwoDates(now, endD);
	}
	
	public static String getDateFormatForSql(String date) {
		LocalDate dateDate = LocalDate.parse(date, dateTimeFormatterLsf);
		String formattedString = dateDate.format(dateTimeFormatterSql);
		return formattedString;
	}
	
	
}
