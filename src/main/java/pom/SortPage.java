package pom;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Parameterization;

public class SortPage extends BaseClass{

	@FindBy(xpath = "//select[@id='sortByFilter']") private WebElement Sort;
	@FindBy(xpath = "//select[@id='sortByFilter']//option") private List<WebElement> SortOptionsList;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> PriceList;
	@FindBy(xpath = "//span[@class='flag flag_New']")private List<WebElement> NewFlag;
	@FindBy(xpath = "//div[@class='item_title']") private List <WebElement> ProductNameList;
	public SortPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void clickOnSort()
	{
		Sort.click();
	}
	
	
	public void getSelectedOption(int row) throws EncryptedDocumentException, IOException
	{
		Select s = new Select(Sort);
		s.selectByVisibleText(Parameterization.getStringData("Sheet2", row, 0));
	}
	
	public double[] getProducPriceList()
	{
		double[] priceList = new double[PriceList.size()];	
		
		for(int i=0;i<PriceList.size();i++)
		{
			String[] price =  PriceList.get(i).getText().split(" ");
			priceList[i] = Double.parseDouble(removeComaFromString(price[0]));
		}
		return priceList;
	}
	
	public boolean isNewFlagDisplayed()
	{
		String[] New = new String [NewFlag.size()];
		for(int i=0;i<NewFlag.size();i++)
		{
			if(NewFlag.get(i).isDisplayed())
			{
				return true;
			}
			
		}
		return false;
		
	}
	
	public String[] getProductsNameList() {
		String[] nameList = new String[ProductNameList.size()];
		
		for(int i=0;i<ProductNameList.size();i++) {
			nameList[i] =ProductNameList.get(i).getText();
		}
		
		return nameList;
		
	}
	
		
}
