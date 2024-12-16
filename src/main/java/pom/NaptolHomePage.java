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
	
	@FindBy(xpath = "//input[@id='header_search_text']") private WebElement searchBox;
	@FindBy(xpath = "(//a[@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement searchButton;
	@FindBy(xpath = "//span[@id='resultCountSpan']") private WebElement searchResult;
	@FindBy(xpath = "//div[@class='cate_head']")private WebElement shoppingCategories; 
	@FindBy(xpath = "//div[@id='mainMenuContent']") private WebElement shoppingCategoriesList;
	@FindBy(xpath = "//div[@class='item_title']") private List <WebElement> productNameList;
	@FindBy(xpath = "//a[@class='bt_compare icon chat quickFancyBox']") private List <WebElement> quckViewBtm;
	@FindBy(xpath = "//section[@id='quickViewBox']") private WebElement quickViewPopUp;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> productPriceList;
	@FindBy(xpath = "//div[@id='productItem3']") private WebElement product;
	@FindBy(xpath = "//select[@id='sortByFilter']") private WebElement sorbBy;
	
	public NaptolHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
		
	public void enterValidProductNameForSearch() throws EncryptedDocumentException, IOException
	{	 
		searchBox.sendKeys(Parameterization.getStringData("Sheet1", 0, 0));		
	}
	
	public void enterInvalidProductNameForSearch() throws EncryptedDocumentException, IOException
	{
			 
		searchBox.sendKeys(Parameterization.getStringData("Sheet1", 1, 0));		
	}
			
	public void clickOnSearchButton()
	{
		searchButton.click();
	}
	
	public String getSearchResultText()
	{
		return searchResult.getText();
	}
		
	public void moveToShoppingCategories(WebDriver driver)
	{			
		Actions act = new Actions(driver);
		act.moveToElement(shoppingCategories);
		act.perform();
	}
	

	public void moveToQuickView(WebDriver driver) 
	{			
		Actions act = new Actions(driver);
		act.moveToElement(quckViewBtm.get(0));
		act.perform();
	}
		
	public boolean verifyingShoppingcategoriesMenu()
	{
		boolean isShoppingCategoriesListDisplayedOrNot = shoppingCategoriesList.isDisplayed();
		return isShoppingCategoriesListDisplayedOrNot;
	}
		
	public boolean clickOnQuickView(int index)
	{
		quckViewBtm.get(index).click();
		return quickViewPopUp.isDisplayed();
		
	}
		
	public String getProductName(int index)
	{
		String productName = productNameList.get(index).getText();
		return productName;
	}
	
	public void moveToProduct(WebDriver driver, int index)
	{
		Actions act = new Actions(driver);
		act.moveToElement(productNameList.get(index));
		act.perform();
	}
	
	public double getProductPrice(int index)
	{
		String [] a = productPriceList.get(index).getText().split(" ");
		return Double.parseDouble(removeComaFromString(a[0]));	
	}
	
	public String clickOnProduct(WebDriver driver)
	{
		product.click();
		return openChildBrowser(driver);		
	}

	public void clickOnSortby()
	{
		sorbBy.click();
	}
	
	public String[] getProductsNameList() {
		String[] nameList = new String[productNameList.size()];
		
		for(int i=0;i<productNameList.size();i++) {
			nameList[i] =productNameList.get(i).getText();
		}
		
		return nameList;	
	}		
}
