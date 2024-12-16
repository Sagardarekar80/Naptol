package test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	public void waitForElementToBeClickable(WebDriver driver ,Duration i , String xpath)
	{		
		WebDriverWait wait = new WebDriverWait(driver,i);		
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));				
	}
	
	

	
	
}

