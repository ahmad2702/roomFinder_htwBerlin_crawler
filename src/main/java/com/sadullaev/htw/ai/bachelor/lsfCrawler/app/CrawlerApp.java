package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader.LsfData;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtils;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.UrlUtils;

public class CrawlerApp 
{
	
    public static void main( String[] args ) throws ParseException{
    	
        System.out.println( "Program is started." );
        System.out.println( "--------------------" );
        
        new LsfData().load();
        
        
        // FUll load
        EventManager eventManager = new EventManager();
        eventManager.setup();
        List<String> allDate = DateUtils.getDatesBetweenTwoDates(LsfData.getStartDate(), LsfData.getEndDate());
        eventManager.pullAllEvents(allDate);
        eventManager.exit();
        
        /**
        // Update
        EventManager eventManager = new EventManager();
        eventManager.setup();
        eventManager.update("06.04.2018", false);
        eventManager.exit();
        */
        
        List<String> aaaList = DateUtils.getDatesBetweenTwoDates(LsfData.getStartDate(), LsfData.getEndDate());
        System.out.println(aaaList);
        
        List<String> aaaList111 = DateUtils.getDatesBetweenNowAndDate(LsfData.getEndDate());
        System.out.println(aaaList111);
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
