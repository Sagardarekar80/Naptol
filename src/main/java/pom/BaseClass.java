package pom;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class BaseClass {

	public String removeComaFromString(String s)
	{
		String c = "";
		for(int i=0;i<s.length();i++)
		{
			
			if(s.charAt(i)!=',')
			{
				c = c+s.charAt(i);
			}
		}		
		
		return c;	
	}
	
	
	public String removeBracesFromString(String s)
	{
		String c = "";		
		for(int i=0;i<s.length();i++)
		{			
			if(s.charAt(i)!='(' && s.charAt(i)!=')')
			{				
				c = c+s.charAt(i);				
			}		
		}				
		return c;	
	}			
	
	public String openChildBrowser(WebDriver driver)
	{
		Set<String> handles =driver.getWindowHandles();		
		Iterator<String> i=handles.iterator();
		String title = " ";
		
		while(i.hasNext())
		{
			driver.switchTo().window(i.next());
			title = driver.getTitle();
		}
		return title;
	}
	
	
	
	
}


