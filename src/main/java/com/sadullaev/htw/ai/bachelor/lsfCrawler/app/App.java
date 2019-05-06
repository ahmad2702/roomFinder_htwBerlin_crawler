package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;

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
        
        
        EventManager eventManager = new EventManager();
        eventManager.setup();
        eventManager.addFull();
        eventManager.exit();
        
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
