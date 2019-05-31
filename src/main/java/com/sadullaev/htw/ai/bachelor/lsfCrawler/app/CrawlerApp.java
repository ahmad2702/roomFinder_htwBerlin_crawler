package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import java.text.ParseException;
import java.time.Month;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.UrlUtils;

public class CrawlerApp 
{
	
    public static void main( String[] args ) throws ParseException{
    	
        System.out.println( "Program is started." );
        
        System.out.println( "--------------------" );
        
        
        
        EventManager eventManager = new EventManager();
        eventManager.setup();
        eventManager.addFullNew(Month.APRIL, 2018, 18);
        eventManager.exit();
		
        
        //Month month = Month.of(2);
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
