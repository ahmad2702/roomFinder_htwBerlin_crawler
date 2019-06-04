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
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser.EventParser;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtils;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.HibernateUtil;

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
 
	private void parseForDay(String day, boolean isActual) throws ParseException {

        EventParser eventParser = new EventParser(day, isActual);
        eventParser.load();
    	
        List<Event> myArrayList = eventParser.getEvents();
        System.out.println("----------------");
        
        if(isActual) {
        	System.out.println("Anzahl aktueller Veranstaltungen (" + day + "): " + myArrayList.size());
        }else {
        	System.out.println("Anzahl ausgefallener Veranstaltungen (" + day + "): " + myArrayList.size());
        }
        
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
	
	
	public void pullAllEvents(List<String> dateList) throws ParseException {

		for(int i = 0; i < dateList.size(); i++) {

			parseForDay(dateList.get(i), true);
			parseForDay(dateList.get(i), false);
			
		}
		
	}
 
	public void read() {
        // code to get a book
    }
	
	
 
	public void update(List<String> date, boolean isActual) throws ParseException {
		
		/**
		
		EventParser eventParser = new EventParser(startDate, isActual);
        eventParser.load();

        //List<Event> myArrayList = eventParser.getEvents();
        
        Session session = sessionFactory.openSession();        
        
        String oldSqlQuery = "FROM com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event where is_actual=1";
        List<Event> oldList = session.createQuery(oldSqlQuery).list();
        
        
        String newSqlQuery = "FROM com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event where date='2018-04-06' and is_actual=0";
        List<Event> newList = session.createQuery(newSqlQuery).list();
        
        
        List<Event> duplikate = HibernateUtil.getDuplikate(newList, oldList);
	    System.out.println("Duplikate: " + duplikate); 
	    
	    if(duplikate.size()!=0) {
	    	newList.removeAll(duplikate);
	    	oldList.removeAll(duplikate);
	    }else {
	    	oldList = new ArrayList<Event>();
	    }
        
	    
	    System.out.println("For add: " + newList);
	    System.out.println("For Update: " + oldList); 
	    */
	    
    }
 
	public void delete() {
        // code to remove a book
    }
	
	
}
