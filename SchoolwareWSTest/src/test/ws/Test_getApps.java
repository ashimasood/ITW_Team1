/* To check that the AppCollection.java class
 * 		-- Check all the applications name, id, categoryname and developer 
 * 		for a Specific Category
 */

package test.ws;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.AppCollection;
import com.google.gson.Gson;


public class Test_getApps {
	@Test
	public void test() {
	WebDriver driver = new HtmlUnitDriver();
	driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/applications/8/");
	String rawJson = driver.getPageSource();
	Gson gson = new Gson();
	System.out.println(rawJson);
	AppCollection apps = gson.fromJson(rawJson, AppCollection.class);
	Assert.assertTrue("app title match", apps.getApps().get(0).getName().equals("marios_priktis"));
	Assert.assertTrue("app id match", apps.getApps().get(0).getId() == 23);
	Assert.assertTrue("app categoryname match", apps.getApps().get(0).getCategoryName().equals("Religious Education"));
	Assert.assertTrue("app developer match", apps.getApps().get(0).getDeveloper().equals("marios_priktis"));
	}
	
}
