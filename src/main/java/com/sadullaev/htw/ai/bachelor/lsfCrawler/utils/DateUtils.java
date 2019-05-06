package com.sadullaev.htw.ai.bachelor.lsfCrawler.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
	
	public static void getListWithDay(int startYear, int month) {
		
		YearMonth start = YearMonth.of( startYear , Month.APRIL );
		System.out.println("Start: " + start.getMonth() + "/" + start.getYear());

		YearMonth stop = start.plusMonths(month);
		System.out.println("End: " + stop.getMonth() + "/" + stop.getYear());
		
		//int tage = start.lengthOfMonth();
		//System.out.println("In diesem Monat: " + tage + " Tage.");
		
		for(int i = 1; i <= month; i++) {
			int tage = start.lengthOfMonth();
			
			for (int j = 1; j <= tage; j++) {
				LocalDate day = start.atDay(j);
				Date currentDate = Date.from(day.atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
				String date = DATE_FORMAT.format(currentDate);
				System.out.println(date);
			}
			start = start.plusMonths(1);
		}
		
		
		
		
	}
	
	
}
