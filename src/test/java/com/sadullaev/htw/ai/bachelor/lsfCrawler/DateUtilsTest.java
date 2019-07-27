package com.sadullaev.htw.ai.bachelor.lsfCrawler;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtil;

public class DateUtilsTest {
	
	private static final String START_DATE = "01.07.2019";
	private static final String END_DATE = "07.07.2019";
	
	private static final String LSF_DATE_FORMAT = "01.07.2019";
	private static final String SQL_DATE_FORMAT = "2019-07-01";
	
	private static List<String> dateListForFullPull = new ArrayList<String>(); 
	private static List<String> dateListForUpdate= new ArrayList<String>(); 
	
	@BeforeClass
	public static void initList() {
		dateListForFullPull.add("01.07.2019");
		dateListForFullPull.add("02.07.2019");
		dateListForFullPull.add("03.07.2019");
		dateListForFullPull.add("04.07.2019");
		dateListForFullPull.add("05.07.2019");
		dateListForFullPull.add("06.07.2019");
		dateListForFullPull.add("07.07.2019");
		
		LocalDate localDate = LocalDate.now();
		for(int i = 0; i<7; i++) {
			String day  = (localDate.getDayOfMonth() < 10)? ("0" + localDate.getDayOfMonth()) : ("" + localDate.getDayOfMonth());
			String month  = (localDate.getMonthValue() < 10)? ("0" + localDate.getMonthValue()) : ("" + localDate.getMonthValue());

			dateListForUpdate.add(day + "." + month + "." + localDate.getYear());
			localDate = localDate.plusDays(1);
		}
	}
	
	
	@Test
 	public void testDateListForFullPull() {
		List<String> result = DateUtil.getDatesBetweenTwoDates(START_DATE, END_DATE);
 		assertTrue(result.equals(dateListForFullPull));
 	}
	
	@Test
 	public void testDateListForUpdate() {
		List<String> result = DateUtil.getDatesBetweenNowAndDate(dateListForUpdate.get(dateListForUpdate.size()-1));
 		assertTrue(result.equals(dateListForUpdate));
		
 	}
	
	@Test
 	public void testFormatConvertFromLsfToSql() {
		String result = DateUtil.getDateFormatForSql(LSF_DATE_FORMAT);
 		assertTrue(result.equals(SQL_DATE_FORMAT));
 	}
	
	
}
