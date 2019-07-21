package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import java.text.ParseException;
import java.util.List;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader.LsfData;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtils;

public class CrawlerApp 
{
	
	/**
	 * Launching LSF-Crawler
	 */
    public static void main( String[] args ) throws ParseException{
    	
        System.out.println( "LSF-Crawler is running..." );
        System.out.println( "--------------------" );
        
        
        
        // load the properties/configs
        new LsfData();
		LsfData.load();
        
		// running and configuring hibernate for transaction
        EventManager eventManager = new EventManager();
    	eventManager.setup();
        
        // selecting operation
        if(LsfData.getOption()==1) {
        	// load all events
        	List<String> allDate = DateUtils.getDatesBetweenTwoDates(LsfData.getStartDate(), LsfData.getEndDate());
        	eventManager.pullAllEvents(allDate);
        	
        }else if(LsfData.getOption()==2) {
        	// update existing events
        	List<String> allDate = DateUtils.getDatesBetweenNowAndDate(LsfData.getEndDate());
        	eventManager.updateLastEvents(allDate, true);
        	
        }
        
        // stopping hibernate
        eventManager.exit();
        
        
        
        
        System.out.println( "--------------------" );
        System.out.println( "LSF-Crawler is finished." );
        
    }
    
}
