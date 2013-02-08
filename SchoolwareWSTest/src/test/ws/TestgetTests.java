/*
 * 		To check the class TestsCollection.java
 * 		-- Compares the names of the first 2 tests
 */
package test.ws;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.ws.TestsCollection;

import com.google.gson.Gson;

import org.junit.Test;

public class TestgetTests {

	@Test
	public void test() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/tests/1");
		String rawJson = driver.getPageSource();
		Gson gson = new Gson();
		System.out.println(rawJson);
		TestsCollection tests = gson.fromJson(rawJson, TestsCollection.class);
		
		assertTrue("test name match", tests.getTest().get(0).getTestName().equals("BeautifulTest1"));	
		assertTrue("test name match", tests.getTest().get(1).getTestName().equals("BeautifulTest2"));
		
		
	}

}
