package test.ws;
import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.google.gson.Gson;

import src.ws.AppDetails;
import src.ws.Application;

public class TestUpload1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
	// driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10); // set FireFox version 10 emulation mode
	//((HtmlUnitDriver) driver).setJavascriptEnabled(true); // enable JavaScript execution
	baseUrl = "http://schoolware.cs.ucl.ac.uk/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test1WebDriver() throws Exception {
	// Test Code for storeApp
	WebElement elem;
    driver.get(baseUrl + "/schoolware/web/");
    driver.findElement(By.cssSelector("img.upload")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("physicsquiz");
    new Select(driver.findElement(By.id("appCategory"))).selectByVisibleText("Physics");
    new Select(driver.findElement(By.id("appType"))).selectByVisibleText(".jar");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("fnkjsdfnsdjk");
    driver.findElement(By.id("developer")).clear();
    driver.findElement(By.id("developer")).sendKeys("ayyapan");
    		
    //String path = "/Applications/AppFolder/Science /BiologyApplicationNeApp/Biology_1.jar";
    String path = "C:/Users/ashimasood/Downloads/test.zip";
    File abspath = new File(path).getAbsoluteFile();
    driver.findElement(By.id("file")).click();
    driver.findElement(By.id("file")).sendKeys(abspath.toString());
    //Thread.sleep(5000);
    driver.findElement(By.id("btnUpload")).click();
    
    // Get the result from new Window
    String schoolwareWindowId = driver.getWindowHandle();
    //System.out.println(driver.getPageSource());
    // Wait the opening the windows
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver d) {
            return d.getWindowHandles().size() > 1;
        }
    });
    // Get the new WindowId
    String resultWindowId = null;
    for (String id : driver.getWindowHandles()) {
    	if (!id.equals(schoolwareWindowId)) {
    		resultWindowId = id;
    	}
    }
    // Change the Window
    driver.switchTo().window(resultWindowId);
    //System.out.println(driver.getPageSource());
    WebElement first = driver.findElement(By.xpath("//pre"));
    // Get the result
    String resultJson = first.getText();
    System.out.println("Result is: " + resultJson);
    Gson gson = new Gson();
   // UploadFile file = gson.fromJson(resultJson, UploadFile.class);
    Application file = gson.fromJson(resultJson, Application.class);

    Assert.assertTrue("UploadFile match", file.getAppName().equals("physicsquiz") );
    
  test2(file);
  }

  public void test2(Application file) throws Exception {
	  WebDriver driver = new HtmlUnitDriver();

      driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/"+file.getAppId());

      String rawJson = driver.getPageSource();

      Gson gson = new Gson();

      AppDetails appID = gson.fromJson(rawJson, AppDetails.class);

    //Assert.assertTrue("category title match", appID.getname().equals("physicsquiz"));
      System.out.println(appID.getId());
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
