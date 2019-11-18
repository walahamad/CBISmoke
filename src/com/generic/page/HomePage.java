package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;

import com.generic.selector.HomePageSelectors;
import com.generic.selector.MyAccount_EmailAddressSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
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

	public static boolean checkHeaderLogo() throws Exception {
		getCurrentFunctionName(true);
		boolean present = false;
		try {
			present = getDriver().findElement(By.xpath(HomePageSelectors.logo)).isDisplayed();

		} catch (NoSuchElementException e) {
			present = false;
		}
		getCurrentFunctionName(false);
		return present;
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
	
	public static void getNumberOfFirstLevelMenuItems() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		List<WebElement> menuFirstLevelElements = new ArrayList<WebElement>();

		// Get the menu items list.
		logs.debug("Validate number of menu items in first level.");
		subStrArr.add(HomePageSelectors.menuItems);
		valuesArr.add("");
		menuFirstLevelElements = SelectorUtil.getAllElements(subStrArr);
		getCurrentFunctionName(false);

		// Display the number of items in the menu first level.
		logs.debug("Number of menu items: " + menuFirstLevelElements.size());
	}
	
	public static boolean validateSubMenuLists() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		List<WebElement> menuFirstLevelElements = new ArrayList<WebElement>();

		logs.debug("Validate menu sub menus list.");
		subStrArr.add(HomePageSelectors.menuItems);
		valuesArr.add("");
		menuFirstLevelElements = SelectorUtil.getAllElements(subStrArr);
		for (WebElement element : menuFirstLevelElements) {
			element.click();
			Thread.sleep(3000);
		}
		getCurrentFunctionName(false);
		logs.debug("Number of menu items: " + menuFirstLevelElements.size());
		return true;
	}

}
