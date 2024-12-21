package pom;


import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaptolCartPage extends BaseClass
{
	@FindBy(xpath = "//ul[@id='cartData']//h2")private WebElement cartProductName;
	@FindBy(xpath = "//div[@id='cartItems']//h2")private List<WebElement> addedProductToCart;
	@FindBy(xpath = "//span[@class='font-bold-imp']")private WebElement cartItemCount;
	@FindBy(xpath = "//p[@class='chintu']//a")private List<WebElement> removeBtm;
	@FindBy(xpath = "//a[@onclick='cart.remove(596086614)']")private WebElement removeBtm1;
	@FindBy(xpath = "(//button[@title='Close'])[4]")private WebElement closeButton;
	@FindBy(xpath = "(//ul[@id='cartData']//li//input)[1]")private WebElement qTY;
	@FindBy(xpath = "(//li[@class='head_UPrice'])[2]")private WebElement unitPrice;
	@FindBy(xpath = "(//li[@class='head_ship'])[2]")private WebElement shipping;
	@FindBy(xpath = "(//li[@class='head_Amount'])[2]")private WebElement orderAmount;
	@FindBy(xpath = "(//li[@class='head_Amount'])[3]")private WebElement orderAmount2;
	@FindBy(xpath = "//span[@id='totalPayableAmount']")private WebElement totalAmount;
	
	public NaptolCartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getCartProductname()
	{
		return cartProductName.getText();
	}
	
	public int getCartProductList()
	{
		
		return addedProductToCart.size();
	}
	
	public int getCartItemCount()
	{
		System.out.println(cartItemCount.getText());
		String [] count =  cartItemCount.getText().split(" ");
		System.out.println(count[0]);
		return Integer.parseInt(removeBracesFromString(count[0]));				
	}
		
	public String [] getProductNames()
	{		
		String [] ProductNames = new String [addedProductToCart.size()];
		for(int i=0; i<addedProductToCart.size();i++)
		{
			ProductNames[i]=addedProductToCart.get(i).getText();
		}
		return ProductNames;
	}
	
	public String clickOnRemoveBtm(int index)	
	{			
		  String removedProductName = addedProductToCart.get(index).getText();
		  removeBtm.get(index).click();	  
		  return removedProductName;
	}
	
	public void clickOnCloseButton()
	{
		closeButton.click();
	}
	
	public void increaseQTY(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		qTY.click();
		
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
		String a = unitPrice.getText().substring(3);		
		return Double.parseDouble(removeComaFromString(a));				 				
	}
	
	public double getShippingPrice()
	{
		String  a = shipping.getText().substring(3);
		return Double.parseDouble(removeComaFromString(a));					
	}
	public double getOrderAmount()
	{		
		String a = orderAmount.getText();			
		return Double.parseDouble(removeComaFromString(a));				
	}
	public double getOrderAmount2()
	{		
		String a = orderAmount2.getText();		
		return Double.parseDouble(removeComaFromString(a));				
	}
	
	public double getTotalAmount()
	{		
		String a = totalAmount.getText();		
		return Double.parseDouble(removeComaFromString(a));				
	}	
	
}
