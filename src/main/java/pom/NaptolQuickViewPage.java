package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaptolQuickViewPage extends BaseClass{

	@FindBy(xpath = "//div[@id='square_Details']//h1") private WebElement productName;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> productPriceList;
	@FindBy(xpath = "//ul[@class='sizeBox clearfix']//li") private List<WebElement> productColorList;
	@FindBy(xpath = "//a[@id='cart-panel-button-0']") private WebElement clickHereToBuyButton;
	@FindBy(xpath = "(//button[@title='Close'])[2]") private WebElement closeButton;
	
	public NaptolQuickViewPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
	
	public String getProductName()
	{
		return productName.getText();
	}
	public double getProductPrice(int index)
	{
		String [] a = productPriceList.get(index).getText().split(" ");
		return Double.parseDouble(removeComaFromString(a[0]));	
	}
	
	public void selectProductColor(int index)
	{				
		productColorList.get(index).click();	
	}
	
	public int  getProductColorList()
	{
		return productColorList.size();
	}
				
	public void clickOnClickHereToBuyButton()
	{
		
		if(productColorList.size()>0)	
		{
			productColorList.get(1).click();
			clickHereToBuyButton.click();
		}
		else
			clickHereToBuyButton.click();
	}
	
	public void clickOnCloseButton()
	{
		closeButton.click();
	}
}
