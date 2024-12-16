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

	@FindBy(xpath = "//select[@id='sortByFilter']") private WebElement sort;
	@FindBy(xpath = "//select[@id='sortByFilter']//option") private List<WebElement> sortOptionsList;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> priceList;
	@FindBy(xpath = "//span[@class='flag flag_New']")private List<WebElement> newFlag;
	@FindBy(xpath = "//div[@class='item_title']") private List <WebElement> productNameList;
	
	public SortPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	public void clickOnSort()
	{
		sort.click();
	}
		
	public void getSelectedOption(int row) throws EncryptedDocumentException, IOException
	{
		Select s = new Select(sort);
		s.selectByVisibleText(Parameterization.getStringData("Sheet2", row, 0));
	}
	
	public double[] getProducPriceList()
	{
		double[] pricelist = new double[priceList.size()];	
		
		for(int i=0;i<priceList.size();i++)
		{
			String[] price =  priceList.get(i).getText().split(" ");
			pricelist[i] = Double.parseDouble(removeComaFromString(price[0]));
		}
		return pricelist;
	}
	
	public boolean isNewFlagDisplayed()
	{
		String[] New = new String [newFlag.size()];
		for(int i=0;i<newFlag.size();i++)
		{
			if(newFlag.get(i).isDisplayed())
			{
				return true;
			}			
		}
		return false;		
	}
	
	public String[] getProductsNameList() {
		String[] nameList = new String[productNameList.size()];
		
		for(int i=0;i<productNameList.size();i++) {
			nameList[i] =productNameList.get(i).getText();
		}		
		return nameList;		
	}
	
		
}
