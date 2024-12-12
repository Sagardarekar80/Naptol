package test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolCartPage;
import pom.NaptolHomePage;
import pom.NaptolQuickViewPage;
@Listeners(test.Listeners.class)
public class NaptolCartPageTest extends BaseTest {

	@BeforeMethod
	public void launchApplication() {
		driver = Browser.openBrowser();
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
			naptolQuickViewPage.SelectProductColor(1);
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
	public void VerifyRemovingProductFromCart() throws EncryptedDocumentException, IOException, InterruptedException {
		test = reports.createTest("VerifyRemovingProductFromCart");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver, 1);
		boolean result = naptolHomePage.clickOnQuickView(1);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		if (naptolQuickViewPage.getProductColorList() > 0) {
			naptolQuickViewPage.SelectProductColor(1);
			naptolQuickViewPage.clickOnClickHereToBuyButton();
		} else {
			naptolQuickViewPage.clickOnClickHereToBuyButton();
		}
		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);

		naptolCartPage.getCartItemCount();

		String RemovedProductname = naptolCartPage.clickOnRemoveBtm(0);

		Thread.sleep(2000);
		naptolCartPage.getCartItemCount();

		String[] AllCartProductNames = naptolCartPage.getProductNames();
		for (int i = 0; i < AllCartProductNames.length; i++) {
			if (AllCartProductNames[i] != RemovedProductname) {
				System.out.println(AllCartProductNames[i]);
			}
			Assert.assertEquals(AllCartProductNames[i], RemovedProductname);

		}
	}

//	@Test
	public void VerifyRemovingProductFromCart1() throws EncryptedDocumentException, IOException {
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver, 6);
		boolean result = naptolHomePage.clickOnQuickView(6);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		naptolQuickViewPage.clickOnClickHereToBuyButton();

		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);

		int ListCount = naptolCartPage.getCartItemCount();

		String RemovedProductname = naptolCartPage.clickOnRemoveBtm(0);
		// Wait for the product to be removed from the cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='cartItems']")));
		// Check that the cart is empty
		int AfterRemoveListCount = naptolCartPage.getCartItemCount();
		Assert.assertTrue(ListCount > AfterRemoveListCount);

	}

	@Test
	public void VerifyOnChangingProductQuantityCorrectAmountIsDisplyed() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		test = reports.createTest("VerifyOnChangingProductQuantityCorrectAmountIsDisplyed");
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
	public void VerifyTotalAmountForMultipleProductsInCart() throws EncryptedDocumentException, IOException, InterruptedException
	{
		test = reports.createTest("VerifyTotalAmountForMultipleProductsInCart");
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
		Thread.sleep(5000);
		double AM1 = naptolCartPage.getOrderAmount();			
		Thread.sleep(5000);
		double AM2 = naptolCartPage.getOrderAmount2();		
		double AM = AM1 + AM2;
		Thread.sleep(5000);		
		double TA= naptolCartPage.getTotalAmount();		
		Assert.assertEquals(AM, TA);
		
	}	
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
	
}



