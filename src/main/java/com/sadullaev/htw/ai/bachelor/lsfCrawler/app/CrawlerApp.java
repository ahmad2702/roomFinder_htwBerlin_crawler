package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import java.text.ParseException;
import java.util.List;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader.LsfData;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtils;

public class CrawlerApp 
{
	
    public static void main( String[] args ) throws ParseException{
    	
        System.out.println( "Program is started." );
        System.out.println( "--------------------" );
        
        new LsfData();
		LsfData.load();
        
        EventManager eventManager = new EventManager();
    	eventManager.setup();
        
        
        if(LsfData.getOption()==1) {
        	// FUll load
        	List<String> allDate = DateUtils.getDatesBetweenTwoDates(LsfData.getStartDate(), LsfData.getEndDate());
        	eventManager.pullAllEvents(allDate);
        }else if(LsfData.getOption()==2) {
        	// Update
        	List<String> allDate = DateUtils.getDatesBetweenNowAndDate(LsfData.getEndDate());
        	eventManager.updateLastEvents(allDate, true);
        }
        
        eventManager.exit();
        
        

        
        
        /**
        //For Tests only
        EventManager eventManager = new EventManager();
        eventManager.setup();
        List<Event> aaaEvents = eventManager.parseAndGetEvents("09.07.2019", true);
        System.out.println(Arrays.toString(aaaEvents.toArray()));
        */
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
