package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.generic.selector.HomePageSelectors;
import com.generic.selector.MyAccount_EmailAddressSelectors;
import com.generic.setup.ActionDriver;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.tests.FG.HomePage.MenuValidation;
import com.generic.util.SelectorUtil;
/**
 * The Class HomePage.
 */
public class HomePage extends SelTestCase {

	public static boolean validateLogoExistance() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if logo exist");
		subStrArr.add(HomePageSelectors.logo);
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}

	public static boolean validateLogodisplayed() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, MyAccount_EmailAddressSelectors.cancelBtn));
		subStrArr.add(MyAccount_EmailAddressSelectors.cancelBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return false;
	}

	public static boolean NavigateAwayFromHomePage() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, MyAccount_EmailAddressSelectors.cancelBtn));
		subStrArr.add(MyAccount_EmailAddressSelectors.cancelBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return false;
	}

	public static boolean clickOnLogo() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, MyAccount_EmailAddressSelectors.cancelBtn));
		subStrArr.add(MyAccount_EmailAddressSelectors.cancelBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return false;
	}

	public static boolean validateHomePageLink() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, MyAccount_EmailAddressSelectors.cancelBtn));
		subStrArr.add(MyAccount_EmailAddressSelectors.cancelBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return false;
	}

	/**
	* Log the number of items at the menu first level.
	*
	* @param menuFirstLevelElements.
	* @throws Exception
	*/
	public static void logNumberOfFirstLevelMenuItems(List<WebElement> menuFirstLevelElements) throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Validate number of menu items in first level.");

		// Display the number of items in the menu first level.
		logs.debug("Number of menu items: " + menuFirstLevelElements.size());

		getCurrentFunctionName(false);
	}

	/**
	* Validate if there's a menu and it contains an item/s.
	*
	* @param menuFirstLevelElements.
	* @return boolean
	* @throws Exception
	*/
	public static boolean menuValidation(List<WebElement> menuFirstLevelElements) throws Exception {
		getCurrentFunctionName(true);

		// Get the menu items list.
		logs.debug("Validate if menu displayed.");

		// Check if there's an item in the menu by check the number of items at first level.
		if (menuFirstLevelElements.size() == 0)
			return false ;
		getCurrentFunctionName(false);

		return true;
	}

	/**
	* Validate the sub menu and the items in first level navigation and the target page.
	*
	* @param menuFirstLevelElements.
	* @return boolean
	* @throws Exception
	*/
	public static boolean validateSubMenuLists(List<WebElement> menuFirstLevelElements) throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Validate menu sub menus list." + menuFirstLevelElements);
		int menuItemIndex;
		boolean validateSubMenuNavigation = true;
		for (menuItemIndex=0; menuItemIndex < menuFirstLevelElements.size(); menuItemIndex++) {
			// The elements should be selected at each iteration because the page will navigate and lose the reference to the elements dom.
			List<WebElement> elements = getFirstLevelMenuItems(HomePageSelectors.menuItems);
			WebElement element = elements.get(menuItemIndex);

			// Save the text and href for the selected menu item.
			String href = element.getAttribute("href");
			boolean currentPageMatchNavigated = true;

			// Navigate to an item in the menu.
			((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", element);

			// Get the current page URL.
			String currentPageUrl = SelectorUtil.getCurrentPageUrl();
			logs.debug("Current page path: " + currentPageUrl);

			// Check if the current page title is the same as selected navigation title.
			if (!href.equalsIgnoreCase(currentPageUrl)) {
				currentPageMatchNavigated = false;
				validateSubMenuNavigation = false;
			}
			sassert().assertTrue(currentPageMatchNavigated, "Menu validation items navigation has some problems");
		}
		getCurrentFunctionName(false);
		return validateSubMenuNavigation;
	}

	/**
	* Get the elements list by pass the selector as a string.
	*
	* @param selector.
	* @return List<WebElement>
	* @throws Exception
	*/
	public static List<WebElement> getElementsList(String selector) throws Exception {
		getCurrentFunctionName(true);

		List<WebElement> menuFirstLevelElements = new ArrayList<WebElement>();
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(selector);
		valuesArr.add("");
		// Get the list web elements.
		menuFirstLevelElements = SelectorUtil.getAllElements(subStrArr);
		getCurrentFunctionName(false);

		return menuFirstLevelElements;
	}

	/**
	* Get the list of first level elements in the menu.
	*
	* @return List<WebElement>
	* @throws Exception
	*/
	public static List<WebElement> getFirstLevelMenuItems(String menuItemsSelector) throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Get the menu items first level.");
		List<WebElement> menuFirstLevelElements = new ArrayList<WebElement>();
		// Get the menu items list.
		menuFirstLevelElements = getElementsList(menuItemsSelector);
		getCurrentFunctionName(false);

		return menuFirstLevelElements;
	}
	
	/**
	* Open the navigation menu.
	*
	* @throws Exception
	*/
	public static void openNavigationMenu() throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Open navigation menu");
		// Click on navigation menu icon.
		// Navigate to an item in the menu.
		WebElement menuIcon = getElementsList(HomePageSelectors.navigationIcon).get(0);
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", menuIcon);
		getCurrentFunctionName(false);
	}
	
	public static boolean validateModalMenuSecondLevel() throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Validate second level menu list.");
		int menuItemIndex;
		boolean validateSubMenuNavigation = true;
		List<WebElement> menuFirstLevelElements = getFirstLevelMenuItems(HomePageSelectors.menuItemsTablet);

		for (menuItemIndex=0; menuItemIndex < menuFirstLevelElements.size(); menuItemIndex++) {
			boolean currentPageMatchNavigated = true;
			HomePage.openNavigationMenu();
			// The elements should be selected at each iteration because the page will navigate and lose the reference to the elements dom.
			List<WebElement> elements = getFirstLevelMenuItems(HomePageSelectors.menuItemsTablet);
			WebElement element = elements.get(menuItemIndex);

			// Save the text and href for the selected menu item.
			String selectedText = element.getAttribute("innerText");
			
			// Navigate to second level in the menu.
			((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", element);

			WebElement selectedMenuHeader = getElementsList(HomePageSelectors.selectedMenuHeader).get(0);
			String selectedMenuHeaderText = selectedMenuHeader.getAttribute("innerText");
			// Get the current page URL.
			String pageUrl = SelectorUtil.getCurrentPageUrl();
						
			if (!selectedMenuHeaderText.equals(selectedText)) {
				currentPageMatchNavigated = false;
				validateSubMenuNavigation = false;
			} else {
				List<WebElement> leafMenuItems = getElementsList(HomePageSelectors.leafMenuItems);
				Random rand = new Random();
				WebElement randomElement = leafMenuItems.get(rand.nextInt(leafMenuItems.size()));
				((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", randomElement);
				String currentPageUrl = SelectorUtil.getCurrentPageUrl();
				// Get the current page URL.
				logs.debug("Current page path: " + currentPageUrl);

				// Check if the current page title is the same as selected navigation title.
				if (pageUrl.equalsIgnoreCase(currentPageUrl)) {
					currentPageMatchNavigated = false;
					validateSubMenuNavigation = false;
				}
			}
			sassert().assertTrue(currentPageMatchNavigated, "Menu validation items navigation has some problems");
		}
		getCurrentFunctionName(false);
		return validateSubMenuNavigation;
	}

}
