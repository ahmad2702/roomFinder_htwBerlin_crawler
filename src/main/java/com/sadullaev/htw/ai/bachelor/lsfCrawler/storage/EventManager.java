package com.sadullaev.htw.ai.bachelor.lsfCrawler.storage;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser.EventParser;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtil;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DuplicateUtil;

@SuppressWarnings("unchecked")
public class EventManager implements EventManagerInterface{
	
	/**
	 * Factory for session objects
	 */
	protected SessionFactory sessionFactory;
	
	/**
	 * running and configuring hibernate for transaction
	 */
	public void setup() {
		Configuration configuration = new Configuration();
        ServiceRegistry serviceRegistry
            = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        configuration.addAnnotatedClass(Event.class);
        
		try {
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception ex) {
		    StandardServiceRegistryBuilder.destroy(serviceRegistry);
		    System.out.println("Datenbank wurde nicht gefunden.");
		    System.exit(1);
		}
    }
	
	/**
	 * Start: Load all events function
	 * @param dateList: date(s) to be entered into the URL
	 */
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
	
	/**
	 * Loading events for a specific day
	 * @param day
	 * @param isActual: actual or canceled events
	 * @return events as list
	 */
	private List<Event> parseAndGetEvents(String day, boolean isActual) throws ParseException{
		EventParser eventParser = new EventParser(day, isActual);
        eventParser.load();
    	
        List<Event> events = eventParser.getEvents();
		return events;
	}
	
	/**
	 * Saving events to database
	 * @param events as list
	 */
	public void add(List<?> events) {
        if(events.size() != 0) {
        	
        	Session session = sessionFactory.openSession();
	        System.out.println("Saving events to database...");
	        
	        for(Object event : events) {
	        	session.beginTransaction();
	        	session.save(event);
	        	session.getTransaction().commit();
	        }
	        
	        session.close();
	        System.out.println("Finished.");

        }
    }
	
	/**
	 * Start: Update existing events
	 * @param date: date(s) to be entered into the URL
	 * @param isActual: actual or canceled events
	 */
	public void updateLastEvents(List<String> date, boolean isActual) throws ParseException {
		String entityClass =  "com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event";
		
		
		for(int i = 0; i < date.size(); i++) {
			List<Event> oldList = read(date.get(i), isActual, entityClass);
	        List<Event> newList = parseAndGetEvents(date.get(i), isActual);
	        
	        List<Event> duplikate = DuplicateUtil.getDuplikate(newList, oldList);
		    
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
 
	/**
	 * Read events for a specific day
	 * @param day
	 * @param isActual: actual or canceled events
	 * @return events from database
	 */
	public List<Event> read(String day, boolean isActual, String entityClass) {
		String dayFormatted = DateUtil.getDateFormatForSql(day);

		Session session = sessionFactory.openSession();    
		
		String oldSqlQuery = "";
		if(isActual) {
			oldSqlQuery = "FROM "+entityClass+" where date='" + dayFormatted + "' and is_actual=1";
		} else {
			oldSqlQuery = "FROM "+entityClass+" where date='" + dayFormatted + "' and is_actual=0";
		}
        
        List<Event> eventsFromDatabase = session.createQuery(oldSqlQuery).list();
        
        session.close();
        
        return eventsFromDatabase;
    }
	
	/**
	 * Update existing events into database
	 * @param events as list
	 */
	public void update(List<?> events) {      
        if(events.size() != 0) {
        	
        	Session session = sessionFactory.openSession();
	        System.out.println("Updating...");
	        
	        for(Object event : events) {
	        	session.beginTransaction();
	        	session.update(event);
	        	session.getTransaction().commit();
	        }
	        
	        session.close();
	        System.out.println("Finished.");
        }
    }	
	
	/**
	 * Getter function of session factory
	 * @return session factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Setter function of session factory
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/**
	 * stopping hibernate
	 */
	public void exit() {
		sessionFactory.close();
    }
	
}
