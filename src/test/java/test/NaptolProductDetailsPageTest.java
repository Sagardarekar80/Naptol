package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolCartPage;
import pom.NaptolHomePage;
import pom.NaptolProductDetailsPage;
@Listeners(test.Listeners.class)
public class NaptolProductDetailsPageTest extends BaseTest {

	@BeforeMethod
	public void launchApplication()
	{
		driver = Browser.openBrowser();				
	}
	
	@Test
	public void verifyAddProductToCartUsingProductDetailPage() throws EncryptedDocumentException, IOException
	{
		test = reports.createTest("verifyAddProductToCartUsingProductDetailPage");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		
		String title = naptolHomePage.clickOnProduct(driver);
		
		NaptolProductDetailsPage naptolProductDetailsPage = new NaptolProductDetailsPage(driver);
		String ProductName =  naptolProductDetailsPage.getProductName();
		
		
		if(naptolProductDetailsPage.getProductColorList()>0)
		{
			naptolProductDetailsPage.SelectProductColor(1);
						
			naptolProductDetailsPage.clickOnClickHereToBuyButton();			
		}
		else
		{
			naptolProductDetailsPage.clickOnClickHereToBuyButton();
		}
		
		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);
		
		String CartProductName = naptolCartPage.getCartProductname();
		
		Assert.assertEquals(ProductName,CartProductName);					
		
	}
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
	
}
