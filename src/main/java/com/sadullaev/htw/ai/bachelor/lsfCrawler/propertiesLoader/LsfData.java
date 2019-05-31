package com.sadullaev.htw.ai.bachelor.lsfCrawler.propertiesLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LsfData {
	
	static FileInputStream fis;
	static Properties property = new Properties();
	private static String url;
	private static String otherParam;
	private static String paramActual;
	private static String paramNotActual;
	private static String paramForDate;
	private static String startYear;
	private static String startMonth;
	private static String tableSelector;
	
	public static void load() {
		
		try {
            fis = new FileInputStream("src/main/resources/lsf.properties");
            property.load(fis);

            url = property.getProperty("url");
            otherParam = property.getProperty("other_param");
            
            paramActual = property.getProperty("param_actual");
            paramNotActual = property.getProperty("param_not_actual");
            
            paramForDate = property.getProperty("param_for_date");
            startYear = property.getProperty("start_year");
            startMonth = property.getProperty("start_month");
            
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

	public static int getStartYear() {
		return Integer.parseInt(startYear);
	}

	public static int getStartMonth() {
		return Integer.parseInt(startMonth);
	}

	public static String getTableSelector() {
		return tableSelector;
	}

}
