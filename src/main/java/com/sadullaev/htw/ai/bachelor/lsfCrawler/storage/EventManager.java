package com.sadullaev.htw.ai.bachelor.lsfCrawler.storage;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
	
	public List<Event> parseAndGetEvents(String day, boolean isActual) throws ParseException{
		EventParser eventParser = new EventParser(day, isActual);
        eventParser.load();
    	
        List<Event> myArrayList = eventParser.getEvents();
		return myArrayList;
	}
	
	// Add new Element into DB
	private void add(List<Event> myArrayList) {
        
        if(myArrayList.size() != 0) {
        	
        	Session session = sessionFactory.openSession();
	        System.out.println("Save to database...");
	        
	        for(Event event : myArrayList) {
	        	session.beginTransaction();
	        	//System.out.println("-> Name: " + event.getName());
	        	session.save(event);
	        	session.getTransaction().commit();
	        }
	        System.out.println("Done.");
	        
	        session.close();

        }

    }
	
	
	public void pullAllEvents(List<String> dateList) throws ParseException {

		for(int i = 0; i < dateList.size(); i++) {
			
			System.out.println("----------------");
			List<Event> aktuelle = parseAndGetEvents(dateList.get(i), true);
			System.out.println("Anzahl aktueller Veranstaltungen (" + dateList.get(i) + "): " + aktuelle.size());
	        add(aktuelle);
	        System.out.println("----------------");
			
	        System.out.println("----------------");
			List<Event> ausgefallene = parseAndGetEvents(dateList.get(i), false);
			System.out.println("Anzahl ausgefallener Veranstaltungen (" + dateList.get(i) + "): " + ausgefallene.size());
			add(ausgefallene);
			System.out.println("----------------");
			
		}
		
	}
 
	private List<Event> read(String day, boolean isActual) {
		String dayFormatted = DateUtils.getDateFormatForSql(day);

		Session session = sessionFactory.openSession();    
		
		String oldSqlQuery = "";
		if(isActual) {
			oldSqlQuery = "FROM com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event where date='"+dayFormatted+"' and is_actual=1";
		} else {
			oldSqlQuery = "FROM com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event where date='"+dayFormatted+"' and is_actual=0";
		}
        
        List<Event> oldList = session.createQuery(oldSqlQuery).list();
        
        session.close();
        
        return oldList;
    }
	
	// Update old Element into DB
	private void update(List<Event> myArrayList) {
        
        if(myArrayList.size() != 0) {
        	
        	Session session = sessionFactory.openSession();
	        System.out.println("Update...");
	        
	        for(Event event : myArrayList) {
	        	session.beginTransaction();
	        	System.out.println("-> Id: " + event.getId());
	        	session.update(event);
	        	session.getTransaction().commit();
	        }
	        System.out.println("Done.");
	
	        session.close();

        }

    }	
 
	public void updateLastEvents(List<String> date, boolean isActual) throws ParseException {
		
		for(int i = 0; i < date.size(); i++) {
			List<Event> oldList = read(date.get(i), isActual);
	        List<Event> newList = parseAndGetEvents(date.get(i), isActual);
	        
	        List<Event> duplikate = HibernateUtil.getDuplikate(newList, oldList);
		    //System.out.println("Duplikate: " + duplikate); 
		    
		    if(duplikate.size()!=0) {
		    	newList.removeAll(duplikate);
		    	oldList.removeAll(duplikate);
		    }
	        oldList.forEach(x-> x.setIsActual(0));
		    
	        System.out.println("-----------------------------------------");
		    System.out.println("Neue Veranstaltungen ("+date.get(i)+"): " + newList.size());
		    add(newList);
		    System.out.println("-----------------------------------------");
		    
		    System.out.println("-----------------------------------------");
		    System.out.println("Ausgefallene Veranstaltungen ("+date.get(i)+"): " + oldList.size()); 
		    update(oldList);
		    System.out.println("-----------------------------------------");
		}
        
	    
	    
    }
 
	public void delete() {
        // code to remove a book
    }
	
	
}
