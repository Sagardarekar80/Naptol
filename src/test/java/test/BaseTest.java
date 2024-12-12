package test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {

	public static WebDriver driver;
	public static ExtentReports reports;
	public static ExtentTest test;
	
	
	
	public String openChildBrowser(WebDriver driver)
	{
		Set<String> handles =driver.getWindowHandles();		
		Iterator<String> i=handles.iterator();
		String title = " ";
		
		while(i.hasNext())
		{
			driver.switchTo().window(i.next());
			title = driver.getTitle();
		}
		return title;
	}
}

