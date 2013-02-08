/*
 * 	To check the number of clicks and time taken for a specific question
 */

package test.ws;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.google.gson.Gson;
import org.junit.Test;
import src.ws.UserUQuestionsCollection;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.UserUQuestionsCollection;
import com.google.gson.Gson;

public class TestgetQuestionDetails {
	@Test
	public void test() {
		WebDriver driver = new HtmlUnitDriver();
        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/question/1");    
        String rawJson = driver.getPageSource();
        System.out.println(rawJson);
        Gson gson = new Gson();
        UserUQuestionsCollection userUQuestion =gson.fromJson(rawJson, UserUQuestionsCollection.class);
        assertTrue("Time ",userUQuestion.getUserUQuestion().get(0).getTime()==0 );
        assertTrue("Clicks ",userUQuestion.getUserUQuestion().get(0).getNumberOfClicks()==1 );
	}

}
