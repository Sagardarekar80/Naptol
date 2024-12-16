package test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolHomePage;
import pom.SortPage;

@Listeners(test.Listeners.class)
public class SortPageTest extends BaseTest
{
	@BeforeMethod
	public void launchApplication()
	{
		driver = Browser.openBrowser();				
	}

	@Test
	public void verifySortFeature() throws EncryptedDocumentException, IOException, InterruptedException
	{
		test = reports.createTest("verifySortFeature");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();		
		String[] beforeSortProductNameList = naptolHomePage.getProductsNameList();		
		SortPage sortPage = new SortPage(driver);
		sortPage.clickOnSort();
		//Most Expensive
		sortPage.getSelectedOption(3);    					
		try 
		{
			double priceList[] = sortPage.getProducPriceList();			
			for(int i=0;i<priceList.length;i++)
			{
				if(i<priceList.length-1)
				{	
					waitForElementToBeClickable(driver ,Duration.ofSeconds(05) , "//span[@class='offer-price']");
					Assert.assertTrue(priceList[i]>=priceList[i+1]);
				}
			}
			
		//Cheapest
		sortPage.getSelectedOption(4);			
			for(int i=priceList.length-1;i>=0;i--)
			{
				if(i>0)
					Assert.assertTrue(priceList[i]<=priceList[i-1]);
			}			
		}
		catch(IOException e)
		{
			driver.findElement(By.xpath("//span[@class='offer-price']"));			
		}			
		
		//New Arrivals
		sortPage.getSelectedOption(1);           
		boolean result =sortPage.isNewFlagDisplayed();
		Assert.assertTrue(result);	
		//Relevance
		sortPage.getSelectedOption(5);          

		String newArrivalSortProductNameList[]=sortPage.getProductsNameList();
		for(int i =0; i<newArrivalSortProductNameList.length;i++) 
		{
			if(beforeSortProductNameList[i]!=newArrivalSortProductNameList[i]) 
			{
				Assert.assertTrue(beforeSortProductNameList[i]!=newArrivalSortProductNameList[i]);
			}
		}
	}		

	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}

}


