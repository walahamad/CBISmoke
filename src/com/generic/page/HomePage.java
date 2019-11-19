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
		getDriver().get("https://www.frontgate.com/ProductSearch2?searchTerm=test");
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
		//TODO: we need to make this dynamic 
		String CurrentURL = getDriver().getCurrentUrl();
		boolean results  =CurrentURL.equals("https://www.frontgate.com/");
		getCurrentFunctionName(false);
		return results;
	}
    
}
