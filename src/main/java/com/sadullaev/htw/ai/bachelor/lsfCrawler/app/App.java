package com.sadullaev.htw.ai.bachelor.lsfCrawler.app;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.lsfParser.EventParser;
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
        
        /*
        EventManager eventManager = new EventManager();
        eventManager.setup();
        
        eventManager.create();
        
        eventManager.exit();
        */
        
        String url = "https://lsf.htw-berlin.de/qisserver/rds?state=currentLectures&type=0&next=CurrentLectures.vm&nextdir=ressourcenManager&&HISCalendar_Date=06.05.2019&asi=";
        
        EventParser eventParser = new EventParser(url);
        eventParser.load();
        
        
        System.out.println(eventParser.getSize());
        
        
        System.out.println( "--------------------" );
        eventParser.getEvents();
        
        
        
        /**
        for (Element element : listNews.select("tr")) {
        	System.out.println(element.text());
        }
        */
        
        
        
        
        
        
        
        System.out.println( "--------------------" );
        System.out.println( "Program is closed." );
        
    }
    
}
