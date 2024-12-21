package test;

import java.io.IOException;
import java.time.Duration;
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
public class NaptolCartPageTest extends BaseTest {

	@Parameters({"browser"})
	@BeforeMethod
	public void launchApplication(String browser) 
	{
		driver = Browser.openBrowser(browser);
	}

	@Test
	public void verifyAddingMultipleProductToCart() throws EncryptedDocumentException, IOException, InterruptedException {
		test = reports.createTest("verifyAddingMultipleProductToCart");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver, 1);
		naptolHomePage.clickOnQuickView(1);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		if (naptolQuickViewPage.getProductColorList() > 0) {
			naptolQuickViewPage.selectProductColor(1);
			naptolQuickViewPage.clickOnClickHereToBuyButton();
		} else {
			naptolQuickViewPage.clickOnClickHereToBuyButton();
		}

		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);
		naptolCartPage.clickOnCloseButton();
		naptolQuickViewPage.clickOnCloseButton();
		Thread.sleep(5000);
		naptolHomePage.moveToProduct(driver, 2);
		naptolHomePage.clickOnQuickView(2);
		naptolQuickViewPage.clickOnClickHereToBuyButton();
		Thread.sleep(5000);
		int d = naptolCartPage.getCartItemCount();
		System.out.println(d);
		int itemCount = naptolCartPage.getCartProductList();
		Assert.assertEquals(itemCount, d);

	}


	@Test
	public void verifyRemovingProductFromCart() throws EncryptedDocumentException, IOException {
		test = reports.createTest("verifyRemovingProductFromCart");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver, 6);
		naptolHomePage.clickOnQuickView(6);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		naptolQuickViewPage.clickOnClickHereToBuyButton();
		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);
		int ListCount = naptolCartPage.getCartItemCount();
		naptolCartPage.clickOnRemoveBtm(0);		
		waitForElementToBeClickable(driver, Duration.ofSeconds(10), "//div[@id='cartItems']");
		int AfterRemoveListCount = naptolCartPage.getCartItemCount();
		Assert.assertTrue(ListCount > AfterRemoveListCount);
	}

	@Test
	public void verifyOnChangingProductQuantityCorrectAmountIsDisplyed() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		test = reports.createTest("verifyOnChangingProductQuantityCorrectAmountIsDisplyed");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver, 1);
		Thread.sleep(5000);
		naptolHomePage.clickOnQuickView(1);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		naptolQuickViewPage.clickOnClickHereToBuyButton();
		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);
		naptolCartPage.increaseQTY(driver);		
		double up=naptolCartPage.getUnitPrice();		  		
		double sp=naptolCartPage.getShippingPrice();					
		double tp = (up*2)+sp;			
		Thread.sleep(5000);
		double oa=naptolCartPage.getOrderAmount();					
		Assert.assertEquals(oa,tp);		
	}

	@Test
	public void verifyTotalAmountForMultipleProductsInCart() throws EncryptedDocumentException, IOException, InterruptedException
	{
		test = reports.createTest("verifyTotalAmountForMultipleProductsInCart");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver, 0);
		naptolHomePage.clickOnQuickView(0);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		naptolQuickViewPage.clickOnClickHereToBuyButton();
		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);
		naptolCartPage.clickOnCloseButton();
		naptolQuickViewPage.clickOnCloseButton();
		naptolHomePage.moveToProduct(driver, 1);
		naptolHomePage.clickOnQuickView(1);		
		naptolQuickViewPage.clickOnClickHereToBuyButton();		
		waitForElementToBeClickable(driver, Duration.ofSeconds(10), "(//li[@class='head_Amount'])[2]");			
		double AM1 = naptolCartPage.getOrderAmount();				
		waitForElementToBeClickable(driver, Duration.ofSeconds(10), "(//li[@class='head_Amount'])[3]");
		System.out.println(naptolCartPage.getOrderAmount2());
		double AM2 = naptolCartPage.getOrderAmount2();		
		double AM = AM1 + AM2;
		waitForElementToBeClickable(driver, Duration.ofSeconds(10), "//span[@id='totalPayableAmount']");		
		double TA= naptolCartPage.getTotalAmount();		
		Assert.assertEquals(AM, TA);		
	}	
	

	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
	
}



