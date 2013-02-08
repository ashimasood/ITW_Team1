/*
 * 	To check the class UserTestCollection.java
 * 		-- Checks the testid, score,questions attended and time for a given test id
 */
package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.UserTestCollection;
import com.google.gson.Gson;

import org.junit.Test;

public class TestgetTestDetails {

	@Test
	public void test() 
	{
WebDriver driver = new HtmlUnitDriver();
		
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/test/1");
  
        String rawJson = driver.getPageSource();
        
        Gson gson = new Gson();
        
        UserTestCollection userTests = gson.fromJson(rawJson, UserTestCollection.class);
        
        System.out.println(rawJson);

        Assert.assertTrue("Test Score Matches for Record 1", userTests.getUserTests().get(0).getTestId() == 1);
        Assert.assertTrue("Test Score Matches for Record 2", userTests.getUserTests().get(0).getTime()==12.0);
        Assert.assertTrue("Test Score Matches for Record 2", userTests.getUserTests().get(0).getQuesAttented()==3);
        Assert.assertTrue("Test Score Matches for Record 2", userTests.getUserTests().get(0).getScore()==45.0);
	}

}
