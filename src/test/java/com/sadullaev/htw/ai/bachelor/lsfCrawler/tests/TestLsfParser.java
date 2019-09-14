package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser.EventParser;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;

public class TestLsfParser {
	
	private static List<Event> eventList;
	
	private static EventParser eventParser;
	
	private static String tableSelector = "body > table:nth-child(3) > tbody > tr:nth-child(1) > "
			+ "td:nth-child(2) > table > tbody > tr:nth-child(2) > td > table:nth-child(5)";
	
	private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	
	@BeforeClass
	public static void initTest() throws UnsupportedEncodingException, IOException {
		String html = new String(Files.readAllBytes(Paths.get("src/test/resources/LSF.html")), "UTF-8");
		
		Document pageElements = Jsoup.parse(html);
		Elements tableWithData = pageElements.select(tableSelector);
		Elements table = tableWithData.select("tr");
		table.remove(0);
		
		eventParser = new EventParser();
		eventParser.setTable(table);
		eventParser.setSize(table.size());
		eventParser.setDate("15.07.2019");
		eventParser.setActual(true);
	}
	
	@Before
	public void setUp() throws ParseException {
		eventList = new ArrayList<Event>();
		
		// Test objects        
        String dateString = "15.07.2019 ";
        Date date = new Date(new Timestamp(format.parse(dateString+"00:00").getTime()).getTime());
		
		Event event1 = new Event();
		event1.setDate(date);
		event1.setBegin(new Timestamp(format.parse(dateString+"08:00").getTime()));
		event1.setEnd(new Timestamp(format.parse(dateString+"09:30").getTime()));
		event1.setLsfNr("7111312");
		event1.setName("D25 Darstellungsmethoden (PÜ)");
		event1.setLsfId(145657);
		event1.setBuilding("WH Gebäude F");
		event1.setRoom("WH Gebäude F Z13");
		event1.setLecturer("Knobloch");
		event1.setIsActual(1);
		eventList.add(event1);

		Event event2 = new Event();
		event2.setDate(date);
		event2.setBegin(new Timestamp(format.parse(dateString+"09:45").getTime()));
		event2.setEnd(new Timestamp(format.parse(dateString+"11:15").getTime()));
		event2.setLsfNr("5512221");
		event2.setName("B2.1a Buchführung");
		event2.setLsfId(148218);
		event2.setBuilding("TA Gebäude A");
		event2.setRoom("TA Gebäude A 027");
		event2.setLecturer("Prof. Dr. Henschel");
		event2.setIsActual(1);
		eventList.add(event2);
		
		Event event3 = new Event();
		event3.setDate(date);
		event3.setBegin(new Timestamp(format.parse(dateString+"12:15").getTime()));
		event3.setEnd(new Timestamp(format.parse(dateString+"13:45").getTime()));
		event3.setLsfNr("6121710");
		event3.setName("B2.4 Buchführung und Bilanzen (SL/BÜ)");
		event3.setLsfId(148589);
		event3.setBuilding("TA Gebäude A");
		event3.setRoom("TA Gebäude A 208");
		event3.setLecturer("Reiche");
		event3.setIsActual(1);
		eventList.add(event3);
		
		Event event4 = new Event();
		event4.setDate(date);
		event4.setBegin(new Timestamp(format.parse(dateString+"14:00").getTime()));
		event4.setEnd(new Timestamp(format.parse(dateString+"15:30").getTime()));
		event4.setLsfNr("6101120");
		event4.setName("2.1.1 - MA2 Mathematik 2 (SL)");
		event4.setLsfId(148955);
		event4.setBuilding("WH Gebäude C");
		event4.setRoom("WH Gebäude C 340");
		event4.setLecturer("Dr. Hingst");
		event4.setIsActual(1);
		eventList.add(event4);
		
		Event event5 = new Event();
		event5.setDate(date);
		event5.setBegin(new Timestamp(format.parse(dateString+"15:45").getTime()));
		event5.setEnd(new Timestamp(format.parse(dateString+"17:15").getTime()));
		event5.setLsfNr("4331121");
		event5.setName("I12 Mathematik 2 (SL)");
		event5.setLsfId(145586);
		event5.setBuilding("WH Gebäude C");
		event5.setRoom("WH Gebäude C 164");
		event5.setLecturer("Feige");
		event5.setIsActual(1);
		eventList.add(event5);
	}
	
	@Test
 	public void sizeTest() throws ParseException {
		List<Event> eventList = eventParser.getEvents();
		assertTrue(eventList.size()==5);
	}
	
	@Test
 	public void eventListTest() throws ParseException {
		List<Event> eventListFromHtml = eventParser.getEvents();		
		assertTrue(eventListFromHtml.equals(eventList));
	}
	
	
}
