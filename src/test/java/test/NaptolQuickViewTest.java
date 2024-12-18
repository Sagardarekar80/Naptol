package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolCartPage;
import pom.NaptolHomePage;
import pom.NaptolQuickViewPage;

@Listeners(test.Listeners.class)
public class NaptolQuickViewTest extends BaseTest{

	@Parameters({"browser"})
	@BeforeMethod
	public void launchApplication(String browser) 
	{
		driver = Browser.openBrowser(browser);
	}
	
	@Test
	public void verifyAddProductToCartUsingQuickView() throws EncryptedDocumentException, IOException
	{
		test = reports.createTest("verifyAddProductToCartUsingQuickView");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();						
		naptolHomePage.moveToProduct(driver,1);		
		naptolHomePage.clickOnQuickView(1);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		String ProductName =  naptolQuickViewPage.getProductName();		
		if(naptolQuickViewPage.getProductColorList()>0)
		{
			 naptolQuickViewPage.selectProductColor(1);						
			naptolQuickViewPage.clickOnClickHereToBuyButton();			
		}
		else
		{
			naptolQuickViewPage.clickOnClickHereToBuyButton();
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
