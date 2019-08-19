package com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LsfConfiguration {
	
	/**
	 * Class variables
	 */
	
	static Properties property = new Properties();
	private static String url;
	private static String otherParam;
	private static String paramActual;
	private static String paramNotActual;
	private static String paramForDate;
	private static String startDate;
	private static String endDate;
	private static String tableSelector;
	private static int option;
	
	/**
	 * Constructor
	 */
	public LsfConfiguration() {
		load();
	}
	
	/**
	 * Function to load all properties from file
	 */
	private static void load() {
		try {
            InputStream input = LsfConfiguration.class.getClassLoader().getResourceAsStream("lsf.properties");
            property.load(input);

            url = property.getProperty("url");
            otherParam = property.getProperty("other_param");
            
            paramActual = property.getProperty("param_actual");
            paramNotActual = property.getProperty("param_not_actual");
            
            paramForDate = property.getProperty("param_for_date");
            startDate = property.getProperty("start_date");
            endDate = property.getProperty("end_date");
            
            tableSelector = property.getProperty("table_selector");
            
            option = Integer.parseInt(property.getProperty("option"));

        } catch (IOException e) {
            System.err.println("Properties fuer LSF nicht gefunden!");
        }
	}
	
	/**
	 * Getter function of url
	 * @return url
	 */
	public static String getUrl() {
		return url;
	}

	/**
	 * Getter function of other necessary parameter 
	 * @return parameter
	 */
	public static String getOtherParam() {
		return otherParam;
	}

	/**
	 * Getter function of actual event
	 * @return parameter
	 */
	public static String getParamActual() {
		return paramActual;
	}

	/**
	 * Getter function of not actual event
	 * @return
	 */
	public static String getParamNotActual() {
		return paramNotActual;
	}

	/**
	 * Getter function of date parameter
	 * @return parameter
	 */
	public static String getParamForDate() {
		return paramForDate;
	}

	/**
	 * Getter function of start date
	 * @return start date
	 */
	public static String getStartDate() {
		return startDate;
	}

	/**
	 * Getter function of end date
	 * @return end date
	 */
	public static String getEndDate() {
		return endDate;
	}

	/**
	 * Getter function of table selector
	 * @return table selector in DOM
	 */
	public static String getTableSelector() {
		return tableSelector;
	}

	/**
	 * Getter function of modus 
	 * @return modus option
	 */
	public static int getOption() {
		return option;
	}

}
