package com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser;

import java.io.IOException;
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

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader.LsfConfiguration;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.UrlUtil;

public class EventParser {
	
	/**
	 * Browser User Agent
	 */
	private final static String USER_AGENT = "Chrome/4.0.249.0 Safari/532.5";
	
	/**
	 * URL of page
	 */
	private String url;
	
	/**
	 * Date to parse
	 */
	private String date;
	
	/**
	 * actual(true) or canceled events(false)
	 */
	private boolean isActual;
	
	/**
	 * Path to the table 
	 */
	private static String tableSelector = null;
	
	/**
	 * Table with events from page 
	 */
	private Elements table = null;
	
	/**
	 * Number of events
	 */
	private int size = 0;
	
	
	/**
	 * Constructor with parameters for parsing
	 * @param date to be entered into the URL
	 * @param isActual: actual(true) or canceled events(false)
	 */
	public EventParser(String date, boolean isActual) {
		this.url = LsfConfiguration.getUrl()+
				"?"
				+ LsfConfiguration.getOtherParam() + "&"
				+ LsfConfiguration.getParamForDate() + date;
		
		if(isActual) {
			this.url = this.url + "&" + LsfConfiguration.getParamActual();
		}else {
			this.url = this.url + "&" + LsfConfiguration.getParamNotActual();
		}
		
		this.date = date;
		this.isActual = isActual;
		tableSelector = LsfConfiguration.getTableSelector();
		
	}
	
	/**
	 * Default Constructor
	 */
	public EventParser() {
		
	}

	/**
	 * @return url of page
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * change url of page
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Loading the table with events from the page
	 */
	public void load() {
		try {
			Document pageElements = Jsoup.connect(url).userAgent(USER_AGENT).timeout(10 * 1000).get();
        	Elements tableWithData = pageElements.select(tableSelector);
        	this.table = tableWithData.select("tr");
        	
        	this.table.remove(0);
        	this.size = table.size();
        } catch (IOException e) {
        	System.out.println("Die Seite kann nicht geoffnet werden.");
        }
	}
	
	/**
	 * Get events as list
	 * @return event list
	 */
	public List<Event> getEvents() throws ParseException {
		List<Event> eventList = new ArrayList<Event>();
		for(int i = 0; i < size; i++) {
			Elements events = table.get(i).select("td");
        	Event currentEvent = new Event(); 
        	
        	String start = this.date + " " + events.get(0).text();
        	String end = this.date + " " + events.get(1).text();
        	
        	DateFormat format_1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        	Timestamp startDate = new Timestamp(format_1.parse(start).getTime());
        	Timestamp endDate = new Timestamp(format_1.parse(end).getTime());
        	
        	currentEvent.setDate(new Date(startDate.getTime()));
        	currentEvent.setBegin(startDate);
        	currentEvent.setEnd(endDate);
        	currentEvent.setLsfNr(events.get(2).text());
        	currentEvent.setName(events.get(3).text().replaceAll("\u0092", "").replaceAll("\u0096", ""));
        	
        	String eventLink = events.get(3).select("a").first().attr("abs:href");
        	int eventId = UrlUtil.getEventIdFromLink(eventLink);
        	
	        currentEvent.setLsfId(eventId);
	        if(isActual) {
	        	currentEvent.setBuilding(events.get(4).text());
		        currentEvent.setRoom(events.get(5).text());
		        currentEvent.setLecturer(events.get(7).text());
		        currentEvent.setIsActual(1);
	        }else {
	        	currentEvent.setLecturer(events.get(5).text());
	        	currentEvent.setIsActual(0);
	        }
	        
	        eventList.add(currentEvent);
        }
		return eventList;
	}

	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Elements getTable() {
		return table;
	}

	public void setTable(Elements table) {
		this.table = table;
	}

	/**
	 * @return table with events
	 */
	public Elements getDoc() {
		return table;
	}
	
	

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return number of events
	 */
	public int getSize() {
		return size;
	}
	
	
	
}
