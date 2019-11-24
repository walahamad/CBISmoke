package com.generic.page;

import java.util.ArrayList;
import java.util.List;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.GlobalVariables;
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
		getDriver().get(getURL() + getCONFIG().getProperty("searchURL"));
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
    
	public static void clickOnMiniCart() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartBtn.get();
		String valuesArr = "ForceAction,hover";
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.chrome)) {
			logs.debug("Clicking on Mini Cart");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		} else {
			logs.debug("Hovering on Mini Cart");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		}

		getCurrentFunctionName(false);
	}

	public static String getMiniCartText() throws Exception {
			getCurrentFunctionName(true);
			String subStrArr = HomePageSelectors.miniCartText.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			String cartText = SelectorUtil.textValue.get();
			logs.debug("The cart text is:" + cartText);
			getCurrentFunctionName(false);
			return cartText;
	}
	
	public static void  clickOnMiniCartCloseBtn() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartClose.get();
		logs.debug("Clicking on Mini Cart clsoe icon");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		getCurrentFunctionName(false);
	}
	
	public static boolean validateMiniCartIsClosed() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed; 
		String subStrArr = HomePageSelectors.miniCartText.get();
		logs.debug("Validate if mini cart is closed");
		isNotDisplayed = SelectorUtil.isNotDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isNotDisplayed;
	}
	
	public static boolean validateMiniCartProductIsDsiplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		String subStrArr = HomePageSelectors.miniCartProductContainer.get();
		logs.debug("Validate if Mini cart products are displayed");
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}
	
	public static boolean validateMiniCartCheckoutBtnIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		String subStrArr = HomePageSelectors.miniCartCheckoutBtn.get();
		logs.debug("Validate if Mini Cart Checkout Btn Is Displayed");
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}
}
