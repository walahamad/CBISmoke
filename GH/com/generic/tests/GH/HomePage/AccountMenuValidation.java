package com.generic.tests.GH.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.GlobalVariables.browsers;
import com.generic.setup.SelTestCase;

public class AccountMenuValidation extends SelTestCase {
	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		boolean accountMenuValidation = true; 
		if (getBrowserName().toLowerCase().equals(browsers.iPhone.toLowerCase())) {
			accountMenuValidation = validateMobile();
		}else {
			accountMenuValidation = validateDesktopAndTablet();
		}		
		getCurrentFunctionName(false);
	    return accountMenuValidation;
	}
	
	public static boolean validateDesktopAndTablet() throws Exception{
		getCurrentFunctionName(true);
		boolean accountMenuValidation = true; 
		accountMenuValidation =  HomePage.validateAccountMenuDisplayed();
		HomePage.clickOnAccountMenu(true);
		HomePage.validateAccountMenuItemsDisplayed();
		HomePage.clickOnRandomAccountMenuItem();
		getCurrentFunctionName(false);
		return accountMenuValidation;
	}
	
	
	public static boolean validateMobile() throws Exception{
		getCurrentFunctionName(true);
		boolean accountMenuValidation = true; 
		accountMenuValidation =  HomePage.validateAccountMenuDisplayed();
		HomePage.clickOnAccountMenu(false);
		HomePage.validateAccountMenuItemsDisplayed();
		HomePage.clickOnCloseButton();
		getCurrentFunctionName(false);
		return accountMenuValidation;
	}

	
}
