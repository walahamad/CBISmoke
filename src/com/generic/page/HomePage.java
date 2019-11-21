package com.generic.page;

import java.text.MessageFormat;
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
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if logo exist");
		subStrArr.add(HomePageSelectors.logo.get());
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}

	public static void NavigateAwayFromHomePage() throws Exception {
		getCurrentFunctionName(true);
		//TODO: we need to make this dynamic  
		getDriver().get(getURL() + getCONFIG().getProperty("searchURL"));
		getCurrentFunctionName(false);
	}

	public static void  clickOnLogo() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Clicking on Site logo");
		subStrArr.add(HomePageSelectors.logo.get());
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
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
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug("Clicking on Mini Cart");
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhoneX)) {
			subStrArr.add(HomePageSelectors.miniCartMob);	
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		}
		else if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPad))
		{
			subStrArr.add(HomePageSelectors.miniCartDeskt);	
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		}
		else
		{
			subStrArr.add(HomePageSelectors.miniCartDeskt);
			valuesArr.add("ForceAction,hover");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		}
		
		getCurrentFunctionName(false);
	}
	
	public static String getMiniCartText() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(HomePageSelectors.miniCartText.get());
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			logs.debug("The cart text is:" + SelectorUtil.textValue.get());
			String cartText = SelectorUtil.textValue.get();
			getCurrentFunctionName(false);
			return cartText;
	}
	
	public static void  clickOnMiniCartCloseBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Clicking on Mini Cart clsoe icon");
		subStrArr.add(HomePageSelectors.miniCartClose);
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
		//TODO: we need to make this dynamic  
		getDriver().get(getURL() + getCONFIG().getProperty("FG_PDP"));
		getCurrentFunctionName(false);
	}

	public static void  selectPDPSwatches() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Clicking on Mini Cart");
		subStrArr.add(HomePageSelectors.miniCartMob);	
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		getCurrentFunctionName(false);
	}
	
	public static void  addProductToCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Selecting swatches for the product");
		selectPDPSwatches();
		logs.debug("Click add to cart");
		subStrArr.add(HomePageSelectors.miniCartMob);	
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
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
