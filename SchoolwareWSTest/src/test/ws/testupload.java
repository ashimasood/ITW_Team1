/*
 * Test case to check the Application.java class. 
 * 		-- It checks if an application is uploaded
 * 		-- After successful upload the application is available in the Application list 
 */

package test.ws;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.server.handler.UploadFile;
import org.openqa.selenium.support.ui.Select;


import src.ws.Application;
import src.ws.AppDetails;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.html.HtmlFileInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.google.gson.Gson;


public class testupload {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		  driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10); // set FireFox version 10 emulation mode
		  ((HtmlUnitDriver) driver).setJavascriptEnabled(true); // enable JavaScript execution
		// driver = new HtmlUnitDriver();
		  baseUrl = "http://schoolware.cs.ucl.ac.uk/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testUpload() throws Exception 
	  {
	    driver.get(baseUrl + "/schoolware/web/");
	    driver.findElement(By.cssSelector("img.upload")).click();
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("physicsquiz");
	    new Select(driver.findElement(By.id("appCategory"))).selectByVisibleText("Physics");
	    new Select(driver.findElement(By.id("appType"))).selectByVisibleText(".exe");
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("jkhkjhk");
	    driver.findElement(By.id("developer")).clear();
	    driver.findElement(By.id("developer")).sendKeys("kjhkj");
	    driver.findElement(By.id("file")).clear();
	    
	    driver.findElement(By.id("file")).sendKeys("C:/Users/ashimasood/Downloads/test.zip");
   
	    String mwh = driver.getWindowHandle();
	    
	    driver.findElement(By.id("btnUpload")).click();
	    Set handles = driver.getWindowHandles();
	    
	    Iterator<String> it = handles.iterator();
	    while(it.hasNext())
	    {
	    	String h = it.next().toString();
	    	if(!h.contains(mwh))
	    	{
	    		driver.switchTo().window(h);
	    		String rawJson = driver.getPageSource();
	    		System.out.println(rawJson);
	    		Gson gson = new Gson();
	    	    Application file = gson.fromJson(rawJson, Application.class);

	    	    Assert.assertTrue("UploadFile match", file.getAppName().equals("physicsquiz") );
	    	    System.out.println(file.getAppName());
	    	  test2(file);
	    	  }
	    }
	  }

	    	  public void test2(Application file) throws Exception 
	    	  {
	    		  WebDriver driver = new HtmlUnitDriver();

	    	      driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/"+file.getAppId());

	    	      String rawJson = driver.getPageSource();

	    	      Gson gson = new Gson();

	    	      AppDetails appID = gson.fromJson(rawJson, AppDetails.class);

	    	   Assert.assertTrue("app id match", appID.getId() == file.getAppId() );
	    	   Assert.assertTrue("appname match", appID.getName().equals(file.getAppName()) );
	    	   Assert.assertTrue("description match", appID.getDescription().equals(file.getDescription()));
	    	   Assert.assertTrue("developer match", appID.getDeveloper().equals(file.getDeveloprName()) );
	    	  } 


	    
	

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alert.getText();
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}


