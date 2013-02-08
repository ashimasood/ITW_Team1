/* 
 * Test case to check the AppDetails.java class. 
 * 		-- 
 * 		-- Compares the name, id, categoryname, description and developer 
 * 		of application id=2
 * */
package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.AppDetails;
import com.google.gson.Gson;

public class TestApplication {

	@Test
	public void test() 
	{

		WebDriver driver = new HtmlUnitDriver();

        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/2");

        String rawJson = driver.getPageSource();
        System.out.println(rawJson);
        Gson gson = new Gson();

        AppDetails appID = gson.fromJson(rawJson, AppDetails.class);

      Assert.assertTrue("application title match", appID.getName().equals("NiceMaths"));
      Assert.assertTrue("application id match", appID.getId() == 2);
      Assert.assertTrue("application description match",appID.getDescription().equals("NiceMaths_Description"));
      Assert.assertTrue("application categoryname match", appID.getCategoryName().equals("Mathematics"));
      Assert.assertTrue("application developer match", appID.getDeveloper().equals("Chirag"));
	}
	


}
