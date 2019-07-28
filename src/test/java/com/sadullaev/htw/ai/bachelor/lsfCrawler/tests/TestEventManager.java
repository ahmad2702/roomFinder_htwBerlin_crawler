package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.testModel.TestEvent;

@SuppressWarnings("unchecked")
public class TestEventManager {

	private static SessionFactory sessionFactory;
	private static EventManager eventManager = new EventManager();
	
	private static List<TestEvent> eventList;
	
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
	}
	
	@Before
	public void setUp() throws ParseException {
		eventList = new ArrayList<TestEvent>();
		
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
		
		TestEvent event5 = new TestEvent();
		event5.setDate(date);
		event5.setBegin(new Timestamp(format.parse(dateString+"17:00").getTime()));
		event5.setEnd(new Timestamp(format.parse(dateString+"18:30").getTime()));
		event5.setLsfNr("104");
		event5.setName("English1");
		event5.setLsfId(5);
		event5.setBuilding("Gebäude A");
		event5.setRoom("123");
		event5.setLecturer("Prof.Dr.Test5");
		event5.setIsActual(1);
		eventList.add(event5);
	}
	
	@After
	public void setUpAfter() {
		clearTestDB();
	}
	
	private void clearTestDB() {
		Session session = sessionFactory.openSession();    
		String deleteAllSqlQuery = "delete FROM com.sadullaev.htw.ai.bachelor.lsfCrawler.testModel.TestEvent";
		session.beginTransaction();
		session.createQuery(deleteAllSqlQuery).executeUpdate();
		session.close();
	}
	
	@Test
 	public void addFuncTest() throws UnsupportedEncodingException, IOException, ParseException {
		// Save test events 
		eventManager.add(eventList);
		
		Session session = sessionFactory.openSession();    
		
		//read test events from db
		String readSqlQuery = "FROM com.sadullaev.htw.ai.bachelor.lsfCrawler.testModel.TestEvent where date='" + "2019-07-01" + "'";
		List<Event> eventsFromDatabase = session.createQuery(readSqlQuery).list();
		
		session.close();
		
		assertTrue(eventList.equals(eventsFromDatabase));
	}
	
	@Test
 	public void readFuncTest() throws UnsupportedEncodingException, IOException, ParseException {
		// Add manual test events
		Session session = sessionFactory.openSession();  
		for (TestEvent event: eventList) {
			session.beginTransaction();
			session.save(event);
			session.getTransaction().commit();
		}
		session.close();
		
		List<Event> eventsFromDatabase = eventManager.read("01.07.2019", true, "com.sadullaev.htw.ai.bachelor.lsfCrawler.testModel.TestEvent");
		
		assertTrue(eventList.equals(eventsFromDatabase));
	}
	
	@Test
 	public void updateFuncTest() throws UnsupportedEncodingException, IOException, ParseException {
		Session session = sessionFactory.openSession();  
		for (TestEvent event: eventList) {
			session.beginTransaction();
			session.save(event);
			session.getTransaction().commit();
		}
		session.close();
		
		eventList.get(0).setIsActual(0);
		eventManager.update(eventList);

		
		session = sessionFactory.openSession();  
		String readSqlQuery = "FROM com.sadullaev.htw.ai.bachelor.lsfCrawler.testModel.TestEvent where date='" + "2019-07-01" + "'";
		List<Event> eventsFromDatabase = session.createQuery(readSqlQuery).list();
		
		session.close();
		
		assertTrue(eventList.equals(eventsFromDatabase));
	}
	
	
}
