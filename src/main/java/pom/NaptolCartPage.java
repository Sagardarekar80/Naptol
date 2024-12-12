package pom;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Parameterization;

public class NaptolCartPage extends BaseClass{

	
	@FindBy(xpath = "//ul[@id='cartData']//h2")private WebElement CartProductName;
	@FindBy(xpath = "//div[@id='cartItems']//h2")private List<WebElement> AddedProductToCart;
	@FindBy(xpath = "//span[@class='font-bold-imp']")private WebElement CartItemCount;
//	@FindBy(xpath = "//div[@id='cartItems']//ul")private List<WebElement> CartProductNameList;
	@FindBy(xpath = "//p[@class='chintu']//a")private List<WebElement> removeBtm;
	@FindBy(xpath = "//a[@onclick='cart.remove(596086614)']")private WebElement removeBtm1;
	@FindBy(xpath = "(//button[@title='Close'])[4]")private WebElement CloseButton;
	@FindBy(xpath = "(//ul[@id='cartData']//li//input)[1]")private WebElement QTY;
	@FindBy(xpath = "(//li[@class='head_UPrice'])[2]")private WebElement UnitPrice;
	@FindBy(xpath = "(//li[@class='head_ship'])[2]")private WebElement Shipping;
	@FindBy(xpath = "(//li[@class='head_Amount'])[2]")private WebElement OrderAmount;
	@FindBy(xpath = "(//li[@class='head_Amount'])[3]")private WebElement OrderAmount2;
	@FindBy(xpath = "//span[@id='totalPayableAmount']")private WebElement TotalAmount;
	
	public NaptolCartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getCartProductname()
	{
		return CartProductName.getText();
	}
	
	public int getCartProductList()
	{
		
		return AddedProductToCart.size();
	}
	
	public int getCartItemCount()
	{
		System.out.println(CartItemCount.getText());
		String [] count =  CartItemCount.getText().split(" ");
		System.out.println(count[0]);
		return Integer.parseInt(removeBracesFromString(count[0]));
				
	}
		
	public String [] getProductNames()
	{		
		String [] ProductNames = new String [AddedProductToCart.size()];
		for(int i=0; i<AddedProductToCart.size();i++)
		{
			ProductNames[i]=AddedProductToCart.get(i).getText();
		}
		return ProductNames;
	}
	
	public String clickOnRemoveBtm(int index)	
	{			
		  String removedProductName = AddedProductToCart.get(index).getText();
		  removeBtm.get(index).click();	  
		  return removedProductName;
	}
	
	public void clickOnCloseButton()
	{
		CloseButton.click();
	}
	
	public void increaseQTY(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		QTY.click();
		
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(2000);
		act.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		act.sendKeys(Keys.NUMPAD2);
		Thread.sleep(2000);
		act.build().perform();	
	}
	
	public double getUnitPrice()
	{		
		String a = UnitPrice.getText().substring(3);		
		return Double.parseDouble(removeComaFromString(a));				 				
	}
	
	public double getShippingPrice()
	{
		String  a = Shipping.getText().substring(3);
		return Double.parseDouble(removeComaFromString(a));					
	}
	public double getOrderAmount()
	{		
		String a = OrderAmount.getText();			
		return Double.parseDouble(removeComaFromString(a));				
	}
	public double getOrderAmount2()
	{		
		String a = OrderAmount2.getText();		
		return Double.parseDouble(removeComaFromString(a));				
	}
	
	public double getTotalAmount()
	{		
		String a = TotalAmount.getText();		
		return Double.parseDouble(removeComaFromString(a));				
	}	
	
}
