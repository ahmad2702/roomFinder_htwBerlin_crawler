package com.sadullaev.htw.ai.bachelor.lsfCrawler.storage;

import java.text.ParseException;
import java.util.List;

public interface EventManagerInterface {
	
	/**
	 * running and configuring hibernate for transaction
	 */
	void setup();
	
	/**
	 * Start: Load all events function
	 * @param dateList: date(s) to be entered into the URL
	 */
	void pullAllEvents(List<String> dateList) throws ParseException;
	
	/**
	 * Start: Update existing events
	 * @param date: date(s) to be entered into the URL
	 * @param isActual: actual or canceled events
	 */
	void updateLastEvents(List<String> date, boolean isActual) throws ParseException;
	
	/**
	 * stopping hibernate
	 */
	void exit();
	
}
