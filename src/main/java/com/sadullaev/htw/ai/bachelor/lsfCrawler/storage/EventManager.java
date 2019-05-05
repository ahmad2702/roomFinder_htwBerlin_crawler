package com.sadullaev.htw.ai.bachelor.lsfCrawler.storage;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;

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
		}
    }
	
	public void exit() {
		sessionFactory.close();
    }
 
	public void create() {
        
    	Event event = new Event();
        event.setDate(new Date(0));
        event.setBegin(1);
        event.setEnd(9);
        event.setLsfNr(123123123);
        event.setName("Test_Name_1");
        event.setLsfId(666);
        event.setBuilding("Aris");
        event.setRoom("zimmer");
        event.setLecturer("Zigi");
    	
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(event);
     
        session.getTransaction().commit();
        session.close();
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
