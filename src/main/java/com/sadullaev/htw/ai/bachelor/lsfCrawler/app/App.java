package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import java.text.ParseException;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.UrlUtils;

public class App 
{
	
    public static void main( String[] args ) throws ParseException{
    	
        System.out.println( "Program is started." );
        
        System.out.println( "--------------------" );
        

        /** Okay
        EventManager eventManager = new EventManager();
        eventManager.setup();
        //eventManager.create();
        //eventManager.addFull();
        eventManager.exit();
		*/
        
        
        //EventManager eventManager = new EventManager();
        //eventManager.setup();
        //eventManager.create();
        //eventManager.exit();
        
        UrlUtils.getEventIdFromLink("");
        
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
