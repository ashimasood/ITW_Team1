/* 
 * Test case to check the CategoryCollection.java class. 
 * 		-- Number of categories is 9
 * 		-- The first category in the list is always Mathematics with id=1
 * */

package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.CategoryCollection;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.CategoryCollection;
import com.google.gson.Gson;

public class TestCategories 
{

	@Test

		public void test() 
		{

		   WebDriver driver = new HtmlUnitDriver();

	       driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/categories/");

	       String rawJson = driver.getPageSource();
	       System.out.println(rawJson);
	       Gson gson = new Gson();
	       CategoryCollection categories = gson.fromJson(rawJson, CategoryCollection.class);
	       Assert.assertTrue("category numbers", categories.getCategories().size()==9);
	       Assert.assertTrue("category title match", categories.getCategories().get(0).getCategType().equals("Mathematics"));
	       Assert.assertTrue("category id match", categories.getCategories().get(0).getCategId() == 1);
	       
}
}
