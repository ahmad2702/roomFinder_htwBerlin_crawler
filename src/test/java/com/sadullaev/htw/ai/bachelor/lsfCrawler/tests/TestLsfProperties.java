package com.sadullaev.htw.ai.bachelor.lsfCrawler.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class TestLsfProperties {
	
private static File file = new File("src/main/resources/lsf.properties");
	
	@Test
	public void testFileExistsAndCanRead() {
		boolean result = false;

		if (file.exists() && file.canRead()) {
			result = true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testUrlNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("url").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testParamActualNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("param_actual").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testParamNotActualNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("param_not_actual").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testParamForDateNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("param_for_date").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testOtherParamNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("other_param").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testTableSelectorNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("table_selector").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testStartDateNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("start_date").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testEndDateNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("end_date").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
	@Test
	public void testOptionNotEmpty() throws IOException {
		boolean result = false;
		
		InputStream inputStream = new FileInputStream(file);
		Properties property = new Properties();
		property.load(inputStream);
		
		try {
			result = !property.getProperty("option").isEmpty();
		}catch (Exception e) {
			result = false;
		}
		
		
		assertTrue(result);
	}
	
}
