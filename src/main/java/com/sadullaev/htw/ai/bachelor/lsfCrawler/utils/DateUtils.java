package com.sadullaev.htw.ai.bachelor.lsfCrawler.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateUtils {
	
	final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		
	public static List<String> getDatesBetweenTwoDates(String startD, String endD) { 
		
		LocalDate startDate = LocalDate.parse(startD, dateTimeFormatter);
		LocalDate endDate = LocalDate.parse(endD, dateTimeFormatter);
		
		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate); 
		List<String> resultDates = IntStream.iterate(0, i -> i + 1)
			      .limit(numOfDaysBetween+1)
			      .mapToObj(i -> startDate.plusDays(i).format(dateTimeFormatter))
			      .collect(Collectors.toList()); 
		
		return resultDates;
	}
	
	public static List<String> getDatesBetweenNowAndDate(String endD) { 
		LocalDate localDate = LocalDate.now();
		String now = localDate.format(dateTimeFormatter);
		
		return getDatesBetweenTwoDates(now, endD);
	}
	
	
}
