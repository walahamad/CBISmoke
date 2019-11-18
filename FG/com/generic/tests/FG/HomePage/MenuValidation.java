package com.generic.tests.FG.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class MenuValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		HomePage.getNumberOfFirstLevelMenuItems();
		HomePage.validateSubMenuLists();
//		logoValidation = logoValidation && HomePage.validateLogodisplayed();
//		logoValidation = logoValidation && HomePage.NavigateAwayFromHomePage(); 
//		logoValidation = logoValidation && HomePage.clickOnLogo(); 
//		logoValidation = logoValidation && HomePage.validateHomePageLink();
		getCurrentFunctionName(false);
		// return logoValidation;
		return true;
		
	}

}

