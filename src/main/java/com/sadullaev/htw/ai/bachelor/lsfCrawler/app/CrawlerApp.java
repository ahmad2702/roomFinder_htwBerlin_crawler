package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import java.text.ParseException;
import java.time.Month;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader.LsfData;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.UrlUtils;

public class CrawlerApp 
{
	
    public static void main( String[] args ) throws ParseException{
    	
        System.out.println( "Program is started." );
        System.out.println( "--------------------" );
        
        new LsfData().load();
        
        
        EventManager eventManager = new EventManager();
        eventManager.setup();
        eventManager.addFullNew(Month.of(LsfData.getStartMonth()), LsfData.getStartYear(), 18);
        eventManager.exit();
        
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
