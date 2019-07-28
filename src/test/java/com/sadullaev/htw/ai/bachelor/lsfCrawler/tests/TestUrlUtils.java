package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sadullaev.htw.ai.bachelor.lsfCrawler.utils.UrlUtil;

public class TestUrlUtils {
	
	@Test
	public void testUrl() {
		int id = 150148;
		String url = "https://lsf.htw-berlin.de/qisserver/rds?state=wsearchv&search=2&P_VormerkenAusschalten=y&veranstaltung.veranstid="+id+"&xmlheader=&menuid=editlectures";
		
		int rusult = UrlUtil.getEventIdFromLink(url);
		assertTrue(rusult==id);
	}
	
	
}
