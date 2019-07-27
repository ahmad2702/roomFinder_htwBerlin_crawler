package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.testModel.TestEvent;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DuplicateUtil;

public class DuplicateUtilTest {
	
	private static List<Event> list1 = new ArrayList<Event>();
	private static List<Event> list2 = new ArrayList<Event>();
	private static List<Event> list3 = new ArrayList<Event>();
	
	private static List<Event> duplicateList = new ArrayList<Event>();
	private static List<Event> duplicateListNull = new ArrayList<Event>();
	
	private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	
	@BeforeClass
	public static void initList() throws ParseException {
		String dateString = "01.07.2019 ";
        Date date = new Date(new Timestamp(format.parse(dateString+"00:00").getTime()).getTime());
		
        Event event1 = new Event();
		event1.setDate(date);
		event1.setBegin(new Timestamp(format.parse(dateString+"08:00").getTime()));
		event1.setEnd(new Timestamp(format.parse(dateString+"09:30").getTime()));
		event1.setLsfNr("123");
		event1.setName("Mathe1");
		event1.setLsfId(1);
		event1.setBuilding("Gebäude C");
		event1.setRoom("624");
		event1.setLecturer("Prof.Dr.Test1");
		event1.setIsActual(1);
		
		Event event2 = new Event();
		event2.setDate(date);
		event2.setBegin(new Timestamp(format.parse(dateString+"09:45").getTime()));
		event2.setEnd(new Timestamp(format.parse(dateString+"11:15").getTime()));
		event2.setLsfNr("234");
		event2.setName("Mathe2");
		event2.setLsfId(2);
		event2.setBuilding("Gebäude G");
		event2.setRoom("345");
		event2.setLecturer("Prof.Dr.Test2");
		event2.setIsActual(1);
		event2.setLecturer("Prof.Dr.Test2");
		
		Event event3 = new Event();
		event3.setDate(date);
		event3.setBegin(new Timestamp(format.parse(dateString+"12:15").getTime()));
		event3.setEnd(new Timestamp(format.parse(dateString+"13:45").getTime()));
		event3.setLsfNr("345");
		event3.setName("Mathe3");
		event3.setLsfId(3);
		event3.setBuilding("Gebäude H");
		event3.setRoom("456");
		event3.setLecturer("Prof.Dr.Test3");
		event3.setIsActual(1);
		
		Event event4 = new Event();
		event4.setDate(date);
		event4.setBegin(new Timestamp(format.parse(dateString+"14:00").getTime()));
		event4.setEnd(new Timestamp(format.parse(dateString+"15:30").getTime()));
		event4.setLsfNr("456");
		event4.setName("Mathe4");
		event4.setLsfId(4);
		event4.setBuilding("Gebäude Z");
		event4.setRoom("567");
		event4.setLecturer("Prof.Dr.Test4");
		event4.setIsActual(1);
		
		
		list1.add(event1);
		list1.add(event2);
		list1.add(event3);
		
		list2.add(event2);
		list2.add(event3);
		list2.add(event4);
		
		list3.add(event4);
		
		duplicateList.add(event2);
		duplicateList.add(event3);
	}
	
	@Ignore
	@Test
 	public void duplicatePositiveTest() {
		List<Event> duplicates = DuplicateUtil.getDuplikate(list2, list1);
		assertTrue(duplicates.equals(duplicateList));
	}
	
	@Ignore
	@Test
 	public void duplicateNegativeTest() {
		List<Event> duplicates = DuplicateUtil.getDuplikate(list3, list1);
		assertTrue(duplicates.equals(duplicateListNull));
	}
	
	
	
}
