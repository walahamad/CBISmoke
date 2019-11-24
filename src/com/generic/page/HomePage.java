package com.generic.page;

import java.util.ArrayList;
import java.util.List;
import com.generic.selector.HomePageSelectors;
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
	public static boolean validateYamalCarouselsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed; 
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("Validate if yamal carousels exist");
		subStrArr.add(HomePageSelectors.yamalCarousels.get());
		isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		getCurrentFunctionName(false);		
		return isDisplayed;
	}
    
}
