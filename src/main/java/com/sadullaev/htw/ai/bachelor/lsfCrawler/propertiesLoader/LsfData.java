package com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LsfData {
	
	static Properties property = new Properties();
	private static String url;
	private static String otherParam;
	private static String paramActual;
	private static String paramNotActual;
	private static String paramForDate;
	private static String startDate;
	private static String endDate;
	private static String tableSelector;
	
	public static void load() {
		
		try {
            InputStream input = LsfData.class.getClassLoader().getResourceAsStream("lsf.properties");
            property.load(input);

            url = property.getProperty("url");
            otherParam = property.getProperty("other_param");
            
            paramActual = property.getProperty("param_actual");
            paramNotActual = property.getProperty("param_not_actual");
            
            paramForDate = property.getProperty("param_for_date");
            startDate = property.getProperty("start_date");
            endDate = property.getProperty("end_date");
            
            tableSelector = property.getProperty("table_selector");

        } catch (IOException e) {
            System.err.println("Properties fuer LSF nicht gefunden!");
        }

	}

	public static String getUrl() {
		return url;
	}

	public static String getOtherParam() {
		return otherParam;
	}

	public static String getParamActual() {
		return paramActual;
	}

	public static String getParamNotActual() {
		return paramNotActual;
	}

	public static String getParamForDate() {
		return paramForDate;
	}

	public static String getStartDate() {
		return startDate;
	}

	public static String getEndDate() {
		return endDate;
	}

	public static String getTableSelector() {
		return tableSelector;
	}

}
