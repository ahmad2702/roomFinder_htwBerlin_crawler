package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	private static SessionFactory sessionFactory;
	private static EventManager eventManager = new EventManager();
	
	private static List<TestEvent> eventList = new ArrayList<TestEvent>();
	
	private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	
	@BeforeClass
	public static void initTest() throws ParseException {
		
		Configuration configuration = new Configuration();
        ServiceRegistry serviceRegistry
            = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        configuration.addAnnotatedClass(TestEvent.class);
		
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        eventManager.setSessionFactory(sessionFactory);
        
        // Test objects        
        String dateString = "01.07.2019 ";
        Date date = new Date(new Timestamp(format.parse(dateString+"00:00").getTime()).getTime());
		
		TestEvent event1 = new TestEvent();
		event1.setDate(date);
		event1.setBegin(new Timestamp(format.parse(dateString+"08:00").getTime()));
		event1.setEnd(new Timestamp(format.parse(dateString+"09:30").getTime()));
		event1.setLsfNr("123");
		event1.setName("Mathe1");
		event1.setLsfId(1);
		event1.setBuilding("Gebäude C");
		event1.setRoom("624");
		event1.setLecturer("Prof.Dr.Test1");
		event1.setIsActual(1);
		eventList.add(event1);

		TestEvent event2 = new TestEvent();
		event2.setDate(date);
		event2.setBegin(new Timestamp(format.parse(dateString+"09:45").getTime()));
		event2.setEnd(new Timestamp(format.parse(dateString+"11:15").getTime()));
		event2.setLsfNr("234");
		event2.setName("Mathe2");
		event2.setLsfId(2);
		event2.setBuilding("Gebäude G");
		event2.setRoom("345");
		event2.setLecturer("Prof.Dr.Test2");
		event2.setIsActual(1);
		eventList.add(event2);
		
		TestEvent event3 = new TestEvent();
		event3.setDate(date);
		event3.setBegin(new Timestamp(format.parse(dateString+"12:15").getTime()));
		event3.setEnd(new Timestamp(format.parse(dateString+"13:45").getTime()));
		event3.setLsfNr("345");
		event3.setName("Mathe3");
		event3.setLsfId(3);
		event3.setBuilding("Gebäude H");
		event3.setRoom("456");
		event3.setLecturer("Prof.Dr.Test3");
		event3.setIsActual(1);
		eventList.add(event3);
		
		TestEvent event4 = new TestEvent();
		event4.setDate(date);
		event4.setBegin(new Timestamp(format.parse(dateString+"14:00").getTime()));
		event4.setEnd(new Timestamp(format.parse(dateString+"15:30").getTime()));
		event4.setLsfNr("456");
		event4.setName("Mathe4");
		event4.setLsfId(4);
		event4.setBuilding("Gebäude Z");
		event4.setRoom("567");
		event4.setLecturer("Prof.Dr.Test4");
		event4.setIsActual(1);
		eventList.add(event4);
		
	}
	
	@Test
 	public void saveEventsTest() throws UnsupportedEncodingException, IOException, ParseException {
		eventManager.add(eventList);
		
	}
	
	
}
