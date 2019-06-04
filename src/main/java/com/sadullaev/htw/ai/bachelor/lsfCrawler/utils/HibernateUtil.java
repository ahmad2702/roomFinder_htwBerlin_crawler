package com.sadullaev.htw.ai.bachelor.lsfCrawler.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.model.Event;
 
public class HibernateUtil {
	
	public static List<Event> getDuplikate(List<Event> newEv, List<Event> oldEv) {
		
		HashSet<Event> intersect = new HashSet<Event>(newEv);
	    intersect.retainAll(oldEv);
		
	    List<Event> duplikate = new ArrayList<Event>(intersect);
		return duplikate;
	}

	
	
	
}
