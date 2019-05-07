package com.sadullaev.htw.ai.bachelor.lsfCrawler.storage;

import java.sql.Date;
import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser.EventParser;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtils;

public class EventManager {
	
	protected SessionFactory sessionFactory;
	
	public void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
		    StandardServiceRegistryBuilder.destroy(registry);
		    System.out.println("Datenbank wurde nicht gefunden.");
		}
    }
	
	public void exit() {
		sessionFactory.close();
    }
 
	public void parseForDay(String day) throws ParseException {

        EventParser eventParser = new EventParser(day);
        eventParser.load();
    	
        List<Event> myArrayList = eventParser.getEvents();
        System.out.println("----------------");
        
        System.out.println("Anzahl (" + day + "): " + myArrayList.size());
        if(myArrayList.size() != 0) {
        	
        	Session session = sessionFactory.openSession();
	        System.out.println(day + " save to database...");
	        
	        for(Event event : myArrayList) {
	        	session.beginTransaction();
	        	session.save(event);
	        	session.getTransaction().commit();
	        }
	        System.out.println(day + " is done.");
	
	        session.close();

        }
        
        System.out.println("----------------");
    }
	
	public void addFull() throws ParseException {
		
		List<String> dateList = DateUtils.getListWithDay(Month.APRIL, 2019, 1);
		
		for(int i = 0; i < dateList.size(); i++) {

			EventParser eventParser = new EventParser(dateList.get(i));
			eventParser.load();
			
			List<Event> myArrayList = eventParser.getEvents();
			if(myArrayList.size() != 0) {
				System.out.println("---");
				Session session = sessionFactory.openSession();
		        
				System.out.println(dateList.get(i) + " is starting...");
		        for(Event event : myArrayList) {
		        	session.beginTransaction();
		        	session.save(event);
		        	session.getTransaction().commit();
		        }
		        System.out.println(dateList.get(i) + " is stopping...");

		        session.close();
		        System.out.println("---");
			}
			
		}
		
	}
	
	public void addFullNew(Month startMonth, int startYear, int monthNumber) throws ParseException {
		
		List<String> dateList = DateUtils.getListWithDay(startMonth, startYear, monthNumber);
		
		for(int i = 0; i < dateList.size(); i++) {

			parseForDay(dateList.get(i));

		}
		
	}
 
	public void read() {
        // code to get a book
    }
 
	public void update() {
        // code to modify a book
    }
 
	public void delete() {
        // code to remove a book
    }
	
	
}
