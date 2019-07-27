package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser.EventParser;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;

public class ParserTest {
	
	private static EventParser eventParser;
	private static String tableSelector = "body > table:nth-child(3) > tbody > tr:nth-child(1) > "
			+ "td:nth-child(2) > table > tbody > tr:nth-child(2) > td > table:nth-child(5)";
	
	private static String listFromHtml = "[Event [id=0, date=2019-07-15, begin=2019-07-15 08:00:00.0, end=2019-07-15 09:30:00.0, lsfNr=7111312, name=D25 Darstellungsmethoden (PÜ), lsfId=145657.0, building=null, room=null, lecturer=WH Gebäude F Z13, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 08:00:00.0, end=2019-07-15 10:00:00.0, lsfNr=5512221, name=B2.1a Buchführung, lsfId=148218.0, building=null, room=null, lecturer=TA Gebäude A 027, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 08:00:00.0, end=2019-07-15 10:00:00.0, lsfNr=6121710, name=B2.4 Buchführung und Bilanzen (SL/BÜ), lsfId=148589.0, building=null, room=null, lecturer=TA Gebäude A 208, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 08:00:00.0, end=2019-07-15 10:00:00.0, lsfNr=6101120, name=2.1.1 - MA2 Mathematik 2 (SL), lsfId=148955.0, building=null, room=null, lecturer=WH Gebäude C 340, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 08:00:00.0, end=2019-07-15 10:30:00.0, lsfNr=4331121, name=I12 Mathematik 2 (SL), lsfId=145586.0, building=null, room=null, lecturer=WH Gebäude C 164, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 08:00:00.0, end=2019-07-15 11:00:00.0, lsfNr=9702320, name=B4.5 Steuerrecht II, lsfId=147820.0, building=null, room=null, lecturer=TA Gebäude A 130, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 08:00:00.0, end=2019-07-15 11:00:00.0, lsfNr=, name=ATD Umbau, lsfId=153992.0, building=null, room=null, lecturer=WH Gebäude G 007, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 12:00:00.0, end=2019-07-15 14:00:00.0, lsfNr=00201126, name=B24 English for Applied Computing, M3Ts (GER B2.2), lsfId=149559.0, building=null, room=null, lecturer=PBH A 5010, isActual=0], Event [id=0, date=2019-07-15, begin=2019-07-15 12:00:00.0, end=2019-07-15 14:00:00.0, lsfNr=5111620, name=B7 Nationale Rechnungslegung, lsfId=147994.0, building=null, room=null, lecturer=TA Gebäude A 220, isActual=0]]";

	
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
	}
	
	@Test
 	public void sizeTest() throws ParseException {
		List<Event> eventList = eventParser.getEvents();
		assertTrue(eventList.size()==5);
	}
	
	@Test
 	public void eventListTest() throws ParseException {
		List<Event> eventList = eventParser.getEvents();

		//assertTrue(eventList.toString().equals(listFromHtml));
	}
	
	
}
