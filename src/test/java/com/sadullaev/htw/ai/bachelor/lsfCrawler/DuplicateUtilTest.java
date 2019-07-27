package com.sadullaev.htw.ai.bachelor.lsfCrawler;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DuplicateUtil;

public class DuplicateUtilTest {
	
	private static List<Event> list1 = new ArrayList<Event>();
	private static List<Event> list2 = new ArrayList<Event>();
	private static List<Event> list3 = new ArrayList<Event>();
	
	private static List<Event> duplicateList = new ArrayList<Event>();
	private static List<Event> duplicateListNull = new ArrayList<Event>();
	
	@BeforeClass
	public static void initList() {
		Date date = new Date(1111111111);
		
		Event event1 = new Event();
		event1.setDate(date);
		event1.setBegin(new Timestamp(1111111));
		event1.setEnd(new Timestamp(1111199));
		event1.setLsfNr("123");
		event1.setName("Mathe1");
		event1.setLsfId(1);
		event1.setBuilding("Gebäude C");
		event1.setRoom("624");
		event1.setLecturer("Prof.Dr.Test1");
		
		Event event2 = new Event();
		event2.setDate(date);
		event2.setBegin(new Timestamp(1111119));
		event2.setEnd(new Timestamp(1111999));
		event2.setLsfNr("234");
		event2.setName("Mathe2");
		event2.setLsfId(2);
		event2.setBuilding("Gebäude G");
		event2.setRoom("345");
		event2.setLecturer("Prof.Dr.Test2");
		
		Event event3 = new Event();
		event3.setDate(date);
		event3.setBegin(new Timestamp(1111199));
		event3.setEnd(new Timestamp(1119999));
		event3.setLsfNr("345");
		event3.setName("Mathe3");
		event3.setLsfId(3);
		event3.setBuilding("Gebäude H");
		event3.setRoom("456");
		event3.setLecturer("Prof.Dr.Test3");
		
		Event event4 = new Event();
		event4.setDate(date);
		event4.setBegin(new Timestamp(1111999));
		event4.setEnd(new Timestamp(1199999));
		event4.setLsfNr("456");
		event4.setName("Mathe4");
		event4.setLsfId(4);
		event4.setBuilding("Gebäude Z");
		event4.setRoom("567");
		event4.setLecturer("Prof.Dr.Test4");
		
		
		list1.add(event1);
		list1.add(event2);
		list1.add(event3);
		
		list2.add(event2);
		list2.add(event3);
		list2.add(event4);
		
		list3.add(event4);
		
		duplicateList.add(event3);
		duplicateList.add(event2);
	}
	
	@Test
 	public void duplicatePositiveTest() {
		List<Event> duplicates = DuplicateUtil.getDuplikate(list2, list1);
		assertTrue(duplicates.equals(duplicateList));
	}
	
	@Test
 	public void duplicateNegativeTest() {
		List<Event> duplicates = DuplicateUtil.getDuplikate(list3, list1);
		assertTrue(duplicates.equals(duplicateListNull));
	}
	
	
	
}
