package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

}
