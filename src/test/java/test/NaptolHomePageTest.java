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
import pom.NaptolHomePage;
import pom.NaptolProductDetailsPage;
import pom.NaptolQuickViewPage;

@Listeners(test.Listeners.class)
public class NaptolHomePageTest extends BaseTest  {

	@Parameters({"browser"})
	@BeforeMethod
	public void launchApplication(String browser) 
	{
		driver = Browser.openBrowser(browser);
	}
	
	@Test
	public void searchAValidProduct() throws EncryptedDocumentException, IOException
	{
		test = reports.createTest("searchAValidProduct");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		String searchResult = naptolHomePage.getSearchResultText();
		double searchCount = Double.parseDouble(searchResult)	;
		Assert.assertTrue(searchCount>0);				
	}
	
	
	@Test
	public void searchAInvalidProduct() throws EncryptedDocumentException, IOException
	{
		test = reports.createTest("searchAInvalidProduct");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterInvalidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		String searchResult = naptolHomePage.getSearchResultText();
		Integer searchCount = Integer.parseInt(searchResult);	
		Assert.assertTrue(searchCount==1);								
	}
	
	
	@Test
	public void verifyOnClickingShoppingCategories()
	{
		test = reports.createTest("verifyOnClickingShoppingCategories");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.moveToShoppingCategories(driver);
		Assert.assertTrue(naptolHomePage.verifyingShoppingcategoriesMenu());
	}
	
	
	
	@Test
	public void verifyIfProdutDetailsAreCorrcetIfViewInQuickView() throws EncryptedDocumentException, IOException 
	{
		test = reports.createTest("verifyIfProdutDetailsAreCorrcetIfViewInQuickView");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();				
		String ProductName =  naptolHomePage.getProductName(2);		
		naptolHomePage.moveToProduct(driver,2);		
		boolean result = naptolHomePage.clickOnQuickView(2);	
		Assert.assertTrue(result);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		String QuickViewProName = naptolQuickViewPage.getProductName();	
		Assert.assertTrue(QuickViewProName.contains(ProductName));
		Double ProductPrice = naptolHomePage.getProductPrice(2);
		Double QuickProductPrice = naptolQuickViewPage.getProductPrice(2);
		Assert.assertEquals(ProductPrice, QuickProductPrice);				
	}
	
	@Test
	public void verifyProductDetailsAreCorrectIfViewInNewTab() throws EncryptedDocumentException, IOException
	{
		test = reports.createTest("verifyProductDetailsAreCorrectIfViewInNewTab");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		String ProductName =  naptolHomePage.getProductName(2);                              
		Double ProductPrice = naptolHomePage.getProductPrice(2);
		naptolHomePage.clickOnProduct(driver);				
		NaptolProductDetailsPage naptolProductDetailsPage = new NaptolProductDetailsPage(driver);
		String ChildBrowserProductName = naptolProductDetailsPage.getProductName();							
		double ChildBrowserProductPrice = naptolProductDetailsPage.getProductPrice(1);		
		Assert.assertTrue(ChildBrowserProductName.contains(ProductName));
		Assert.assertEquals(ProductPrice, ChildBrowserProductPrice);
	}
	
	
	@Test
	public void verifySortFeature() throws EncryptedDocumentException, IOException
	{
		test = reports.createTest("verifySortFeature");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.clickOnSortby();					
	}
	
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
}
