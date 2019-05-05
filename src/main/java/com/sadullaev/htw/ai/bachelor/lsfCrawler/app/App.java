package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.storage.EventManager;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ){
    	
        System.out.println( "Program is started." );
        
        System.out.println( "--------------------" );
        

        
        EventManager eventManager = new EventManager();
        //eventManager.setup();
        //eventManager.create();
        
        
        eventManager.addFull();
        
        //eventManager.exit();

        
        
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
