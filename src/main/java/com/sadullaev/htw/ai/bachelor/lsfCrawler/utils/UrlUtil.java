package com.sadullaev.htw.ai.bachelor.lsfCrawler.utils;

public class UrlUtil {
	
	/**
	 * Getter function for event ID from string
	 * @param string
	 * @return event id
	 */
	public static int getEventIdFromLink(String url) {
		
		int result = 0;
		
		String s = url;
		try {
			s = s.substring(s.indexOf("veranstaltung.veranstid="));
			s = s.substring(0, s.indexOf("&"));

			String segments[] = s.split("=");
			result = Integer.parseInt(segments[1]);
		}catch(Exception e) {
			System.out.println("Event id is not exists.");
		}

		return result;
	}

}
