package test.ws;

import static org.junit.Assert.*;

import org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.ws.AppCollection;

import com.google.gson.Gson;


public class TestCheckApps {

	@Test
	public void test() {
		WebDriver driver = new HtmlUnitDriver();

        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/applications/1");

        String rawJson = driver.getPageSource();

        Gson gson = new Gson();
        
        AppCollection apps =gson.fromJson(rawJson, AppCollection.class);
        int count= apps.getApps().size();
       
        String tan,appname;
       
        for(int j=0;j<count;j++)
        {
        	appname= apps.getApps().get(j).getName();
       
        for(int i=j+1;i<count;i++)
        {
        	 
        	tan=apps.getApps().get(i).getName();
        	if(tan.equals(appname))
        	{
        		System.out.println("Multiple instances of the same "+tan+" file name under Mathematics category");
        		fail("Multiple instances of the same "+tan+" file name under Mathematics category");
        		
        	}
        	continue;
        		
        }
     }
     
        System.out.println("No Multiple instances found");
        
	} 

}
