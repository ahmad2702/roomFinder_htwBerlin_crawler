package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;
import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.DateUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ){
    	
        System.out.println( "Program is started." );
        
        System.out.println( "--------------------" );
        

        /** Okay
        EventManager eventManager = new EventManager();
        eventManager.setup();
        //eventManager.create();
        //eventManager.addFull();
        eventManager.exit();
		*/
        
        
        DateUtils.getListWithDay(2018, 1);
        
        
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
