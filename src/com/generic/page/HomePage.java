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
    
	public static void  clickOnMiniCart() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartBtn.get();
		String valuesArr = "ForceAction,hover";
		logs.debug("Clicking on Mini Cart");
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.chrome)) {
		
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		}
		else
		{
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		}
		
		getCurrentFunctionName(false);
	}
	
	public static String getMiniCartText() throws Exception {
			getCurrentFunctionName(true);
			String subStrArr = HomePageSelectors.miniCartText.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			logs.debug("The cart text is:" + SelectorUtil.textValue.get());
			String cartText = SelectorUtil.textValue.get();
			getCurrentFunctionName(false);
			return cartText;
	}
	
	public static void  clickOnMiniCartCloseBtn() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = HomePageSelectors.miniCartClose;
		logs.debug("Clicking on Mini Cart clsoe icon");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		getCurrentFunctionName(false);
	}
	
	public static boolean validateMiniCartIsClosed() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed; 
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if mini cart is closed");
		subStrArr.add(HomePageSelectors.miniCartText.get());
		isNotDisplayed = SelectorUtil.isNotDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isNotDisplayed;
	}
	
	public static void NavigateToPDP() throws Exception {
		getCurrentFunctionName(true);
		getDriver().get(getURL() + getCONFIG().getProperty("FG_PDP"));
		getCurrentFunctionName(false);
	}
	
	public static boolean validateMiniCartProductIsDsiplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if Mini cart products are displayed");
		subStrArr.add(HomePageSelectors.miniCartProductContainer.get());
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}
	
	public static boolean validateMiniCartCheckoutBtnIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if Mini Cart Checkout Btn Is Displayed");
		subStrArr.add(HomePageSelectors.miniCartCheckoutBtn.get());
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}
}
