package com.sadullaev.htw.ai.bachelor.lsfCrawler.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtils {
	
	public static List<String> getListWithDay(Month month, int startYear, int monthNumber) {
		
		List<String> dateList = new ArrayList<String>();
		
		YearMonth start = YearMonth.of(startYear , month);

		for(int i = 1; i <= monthNumber; i++) {
			int tage = start.lengthOfMonth();
			
			for (int j = 1; j <= tage; j++) {
				LocalDate day = start.atDay(j);
				Date currentDate = Date.from(day.atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
				String date = DATE_FORMAT.format(currentDate);
				dateList.add(date);
			}
			start = start.plusMonths(1);
		}
		
		
		return dateList;
		
	}
	
	
}
