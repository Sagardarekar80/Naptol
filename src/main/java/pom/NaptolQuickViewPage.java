package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaptolQuickViewPage extends BaseClass{

	@FindBy(xpath = "//div[@id='square_Details']//h1") private WebElement ProductName;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> ProductPriceList;
	@FindBy(xpath = "//ul[@class='sizeBox clearfix']//li") private List<WebElement> ProductColorList;
	@FindBy(xpath = "//a[@id='cart-panel-button-0']") private WebElement ClickHereToBuyButton;
	@FindBy(xpath = "(//button[@title='Close'])[2]") private WebElement CloseButton;
	
	public NaptolQuickViewPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
	
	public String getProductName()
	{
		return ProductName.getText();
	}
	public double getProductPrice(int index)
	{
		String [] a = ProductPriceList.get(index).getText().split(" ");
		return Double.parseDouble(removeComaFromString(a[0]));	
	}
	
	public void SelectProductColor(int index)
	{				
		ProductColorList.get(index).click();	
	}
	
	public int  getProductColorList()
	{
		return ProductColorList.size();
	}
				
	public void clickOnClickHereToBuyButton()
	{
		
		if(ProductColorList.size()>0)	
		{
			ProductColorList.get(1).click();
			ClickHereToBuyButton.click();
		}
		else
			ClickHereToBuyButton.click();
	}
	
	public void clickOnCloseButton()
	{
		CloseButton.click();
	}
	
	
	
}
