package pom;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Parameterization;


public class NaptolHomePage extends BaseClass  {
	
	@FindBy(xpath = "//input[@id='header_search_text']") private WebElement SearchBox;
	@FindBy(xpath = "(//a[@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement SearchButton;
	@FindBy(xpath = "//span[@id='resultCountSpan']") private WebElement SearchResult;
	@FindBy(xpath = "//div[@class='cate_head']")private WebElement ShoppingCategories; 
	@FindBy(xpath = "//div[@id='mainMenuContent']") private WebElement ShoppingCategoriesList;
	@FindBy(xpath = "//div[@class='item_title']") private List <WebElement> ProductNameList;
	@FindBy(xpath = "//a[@class='bt_compare icon chat quickFancyBox']") private List <WebElement> QuckViewBtm;
	@FindBy(xpath = "//section[@id='quickViewBox']") private WebElement QuickViewPopUp;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> ProductPriceList;
	@FindBy(xpath = "//div[@id='productItem2']") private WebElement Product;
	@FindBy(xpath = "//select[@id='sortByFilter']") private WebElement SorbBy;
	
	public NaptolHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
		
	public void enterValidProductNameForSearch() throws EncryptedDocumentException, IOException
	{	 
		SearchBox.sendKeys(Parameterization.getStringData("Sheet1", 0, 0));		
	}
	
	public void enterInvalidProductNameForSearch() throws EncryptedDocumentException, IOException
	{
			 
		SearchBox.sendKeys(Parameterization.getStringData("Sheet1", 1, 0));		
	}
			
	public void clickOnSearchButton()
	{
		SearchButton.click();
	}
	
	public String getSearchResultText()
	{
		return SearchResult.getText();
	}
		
	public void moveToShoppingCategories(WebDriver driver)
	{			
		Actions act = new Actions(driver);
		act.moveToElement(ShoppingCategories);
		act.perform();
	}
	

	public void moveToQuickView(WebDriver driver) 
	{			
		Actions act = new Actions(driver);
		act.moveToElement(QuckViewBtm.get(0));
		act.perform();
	}
		
	public boolean verifyingShoppingcategoriesMenu()
	{
		boolean isShoppingCategoriesListDisplayedOrNot = ShoppingCategoriesList.isDisplayed();
		return isShoppingCategoriesListDisplayedOrNot;
	}
		
	public boolean clickOnQuickView(int index)
	{
		QuckViewBtm.get(index).click();
		return QuickViewPopUp.isDisplayed();
		
	}
		
	public String getProductName(int index)
	{
		String productName = ProductNameList.get(index).getText();
		return productName;
	}
	
	public void moveToProduct(WebDriver driver, int index)
	{
		Actions act = new Actions(driver);
		act.moveToElement(ProductNameList.get(index));
		act.perform();
	}
	
	public double getProductPrice(int index)
	{
		String [] a = ProductPriceList.get(index).getText().split(" ");
		return Double.parseDouble(removeComaFromString(a[0]));	
	}
	
	public String clickOnProduct(WebDriver driver)
	{
		Product.click();
		return openChildBrowser(driver);
		
	}

	public void clickOnSortby()
	{
		SorbBy.click();
	}
	
	public String[] getProductsNameList() {
		String[] nameList = new String[ProductNameList.size()];
		
		for(int i=0;i<ProductNameList.size();i++) {
			nameList[i] =ProductNameList.get(i).getText();
		}
		
		return nameList;
		
	}
	
		
}
