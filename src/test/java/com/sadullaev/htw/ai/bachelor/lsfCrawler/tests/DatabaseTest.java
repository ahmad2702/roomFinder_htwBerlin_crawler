package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.testModel.TestEvent;

public class DatabaseTest {
	
	private static EventManager eventManager = new EventManager();
	
	private static List<TestEvent> eventList = new ArrayList<TestEvent>();
	
	@BeforeClass
	public static void initTest() {
		
		Configuration configuration = new Configuration();
        ServiceRegistry serviceRegistry
            = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        configuration.addAnnotatedClass(TestEvent.class);
		
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        eventManager.setSessionFactory(sessionFactory);

	}
	
	@Test
 	public void saveEventsTest() {
		//eventManager.add(eventList);
	}
	
	
}
