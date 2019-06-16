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
import com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader.LsfData;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.UrlUtils;

public class EventParser {
	
	private String url;
	private String date;
	private boolean isActual;
	
	private final static String userAgent = "Chrome/4.0.249.0 Safari/532.5";
	private static String tableSelector = null;
	
	private Elements table = null;
	private int size = 0;
	
	public EventParser(String date, boolean isActual) {
		new LsfData().load();
		
		this.url = LsfData.getUrl()+
				"?"
				+ LsfData.getOtherParam() + "&"
				+ LsfData.getParamForDate() + date;
		
		if(isActual) {
			this.url = this.url + "&" + LsfData.getParamActual();
		}else {
			this.url = this.url + "&" + LsfData.getParamNotActual();
		}
		
		this.date = date;
		this.isActual = isActual;
		tableSelector = LsfData.getTableSelector();
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void load() {
		try {
			Document pageElements = Jsoup.connect(url).userAgent(userAgent).timeout(10 * 1000).get();
        	Elements tableWithData = pageElements.select(tableSelector);
        	this.table = tableWithData.select("tr");
        	
        	this.table.remove(0);
        	this.size = table.size();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	public List<Event> getEvents() throws ParseException {
		List<Event> myArrayList = new ArrayList<Event>();
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
        	currentEvent.setName(events.get(3).text());
        	
        	String eventLink = events.get(3).select("a").first().attr("abs:href");
        	int eventId = UrlUtils.getEventIdFromLink(eventLink);
        	
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
	        
	        myArrayList.add(currentEvent);
        }
		return myArrayList;
	}

	public Elements getDoc() {
		return table;
	}

	public int getSize() {
		return size;
	}
	
	
	
}
