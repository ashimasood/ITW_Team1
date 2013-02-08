/*
 * 	To check the class TypesCollection.java
 * 		--Tests the type of files to be uploaded can only be .jar, .exe or .apk
 */
package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.TypesCollection;
import com.google.gson.Gson;

public class TestgetTypes 
{
	@Test
	public void test()
	{
	WebDriver driver = new HtmlUnitDriver();
	driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/types");
	String rawJson = driver.getPageSource();
	Gson gson = new Gson();
	TypesCollection types = gson.fromJson(rawJson, TypesCollection.class);

	//typeId 1 = Jar File
	Assert.assertTrue("app type match", types.getTypes().get(0).getAppType().equals("Jar File"));
	Assert.assertTrue("app typeID match", types.getTypes().get(0).getAppExtention().equals(".jar"));

	//typeId 2 = Executable File
	Assert.assertTrue("app type match", types.getTypes().get(1).getAppType().equals("Executable File"));
	Assert.assertTrue("app typeID match", types.getTypes().get(1).getAppExtention().equals(".exe"));

	//typeId 3 = Android Application Package File
	Assert.assertTrue("app type match", types.getTypes().get(2).getAppType().equals("Android Application Package File"));
	Assert.assertTrue("app typeID match", types.getTypes().get(2).getAppExtention().equals(".apk"));
	}
}
