package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.generic.selector.HomePageSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;
/**
 * The Class HomePage.
 */
public class HomePage extends SelTestCase {

	public static boolean validateLogodisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		logs.debug("Validate if logo exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.logo.get());
		getCurrentFunctionName(false);		
		return isDisplayed;
	}

	public static void NavigateAwayFromHomePage() throws Exception {
		getCurrentFunctionName(true);
		//TODO: we need to make this dynamic  
		getDriver().get(getURL() + "/ProductSearch2?searchTerm=test");
		getCurrentFunctionName(false);
	}

	public static void  clickOnLogo() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Clicking on Site logo");
		SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.logo.get());
		getCurrentFunctionName(false);
	}

	public static boolean validateHomePageLink() throws Exception {
		getCurrentFunctionName(true);
		String CurrentURL = getDriver().getCurrentUrl().replace("www.", "");
		logs.debug("Current URL is: " + CurrentURL);
		logs.debug("Current URL should match: " + getURL().replace("www.", "") +"/" );
		boolean results  =CurrentURL.equals(getURL().replace("www.", "")  +"/");
		getCurrentFunctionName(false);
		return results;
	}
	
	public static boolean validateAccountMenuDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		logs.debug("Validate if Account menu exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.accountMenu.get());
		getCurrentFunctionName(false);		
		return isDisplayed;
	}

	
	public static void clickOnAccountMenu(Boolean withHover) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Clicking on Account menu");
			if(withHover) {
			SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.accountMenu.get(), "ForceAction,hover");
			}else {
			SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.accountMenu.get());
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static void clickOnCloseButton() throws Exception {
	
		getCurrentFunctionName(true);
		logs.debug("Clicking on Close Button");
		SelectorUtil.initializeSelectorsAndDoActions(HomePageSelectors.navIcon.get());
		getCurrentFunctionName(false);
	
	}
	
	
	public static void clickOnRandomAccountMenuItem() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> accountMenuElements = getAccountMenuItems();
		Random random = new Random();
		int randomIndex = random.nextInt(accountMenuElements.size());
		WebElement element = accountMenuElements.get(randomIndex);
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", element);
		getCurrentFunctionName(false);
	}
	
	
	public static List<WebElement> getAccountMenuItems() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Get the account menu items.");
		List<WebElement> menuItems = new ArrayList<WebElement>();
		menuItems = getElementsList(HomePageSelectors.accountMenuItems.get());
		getCurrentFunctionName(false);
		return menuItems;
	}
	
	public static List<WebElement> getElementsList(String selector) throws Exception {
		getCurrentFunctionName(true);		
		elementsList = SelectorUtil.getAllElements(selector);
		getCurrentFunctionName(false);
		return elementsList;
	}
	
	public static boolean validateAccountMenuItemsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		List<WebElement> accountMenuElements = getAccountMenuItems();
		logs.debug("Validate account menu links." + accountMenuElements);
		boolean isDisplayed = true;
		for (WebElement element:accountMenuElements ) {
			isDisplayed = element.isDisplayed();
		}
		getCurrentFunctionName(false);
		return isDisplayed;
	}
	

	
	public static boolean validateCountrySelectorDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		logs.debug("Validate if country selector  exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.countrySelector.get());
		getCurrentFunctionName(false);		
		return isDisplayed;
	}
	
	public static boolean validateGlobalFooterItemsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed = true;
		logs.debug("Validate if global footer  exist");
		isDisplayed = SelectorUtil.isDisplayed(HomePageSelectors.globalFooter.get());		
		List<WebElement> footerItems = new ArrayList<WebElement>();
		footerItems = getElementsList(HomePageSelectors.accordionHeader.get());
		for (WebElement element :footerItems) {
			isDisplayed = element.isDisplayed();			
		}
		getCurrentFunctionName(false);		
		return isDisplayed;
	}
	
	
	

	
	
	
	
    
}
