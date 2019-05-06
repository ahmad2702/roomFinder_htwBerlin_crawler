package com.sadullaev.htw.ai.bachelor.lsfCrawler.storage;

import java.sql.Date;
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
 
	public void create() {
        
		String url = "https://lsf.htw-berlin.de/qisserver/rds?state=currentLectures&type=0&next=CurrentLectures.vm&nextdir=ressourcenManager&&HISCalendar_Date=06.05.2019&asi=";
        
        EventParser eventParser = new EventParser(url);
        eventParser.load();
    	
        List<Event> myArrayList = eventParser.getEvents();
        
        Session session = sessionFactory.openSession();
        
        for(Event event : myArrayList) {
        	session.beginTransaction();
        	session.save(event);
        	session.getTransaction().commit();
        }

        session.close();
    }
	
	public void addFull() {
		
		List<String> dateList = DateUtils.getListWithDay(Month.APRIL, 2019, 1);
		
		for(int i = 0; i < dateList.size(); i++) {
			String url = "https://lsf.htw-berlin.de/qisserver/rds?"
					+ "state=currentLectures&type=0&next=CurrentLectures.vm&"
					+ "nextdir=ressourcenManager&&HISCalendar_Date="+dateList.get(i)+"&asi=";
			
			
			EventParser eventParser = new EventParser(url);
			eventParser.load();
			
			List<Event> myArrayList = eventParser.getEvents();
			if(myArrayList.size() != 0) {
				
				Session session = sessionFactory.openSession();
		        
		        for(Event event : myArrayList) {
		        	session.beginTransaction();
		        	session.save(event);
		        	session.getTransaction().commit();
		        }

		        session.close();
			}
			
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
