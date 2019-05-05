package com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;

public class EventParser {
	
	private String url;
	private final static String userAgent = "Chrome/4.0.249.0 Safari/532.5";
	
	private final static String tableSelector = "body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(2) > table > tbody > tr:nth-child(2) > td > table:nth-child(5)";
	
	private Elements table = null;
	private int size = 0;
	
	public EventParser(String url) {
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void load() {
		try {
			Document pageElements = Jsoup.connect(url).userAgent(userAgent).get();
        	Elements tableWithData = pageElements.select(tableSelector);
        	this.table = tableWithData.select("tr");
        	
        	this.table.remove(0);
        	this.size = table.size();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	public List<Event> getEvents() {
		List<Event> myArrayList = new ArrayList<Event>();
		for(int i = 0; i < size; i++) {
			Elements events = table.get(i).select("td");
        	Event currentEvent = new Event(); 
        	currentEvent.setDate(new Date(0));
        	currentEvent.setBegin(1);
        	currentEvent.setEnd(9);
        	currentEvent.setLsfNr(events.get(2).text());
        	currentEvent.setName(events.get(3).text());
	        currentEvent.setLsfId(666);
	        currentEvent.setBuilding(events.get(4).text());
	        currentEvent.setRoom(events.get(5).text());
	        currentEvent.setLecturer(events.get(7).text());
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