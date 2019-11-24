package com.generic.tests.FG.HomePage;

import com.generic.page.HomePage;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.GlobalVariables.browsers;
import com.generic.setup.SelTestCase;

import org.openqa.selenium.WebElement;
import java.util.List;

public class AccountMenuValidation extends SelTestCase {
	public static void validate() throws Exception {
		getCurrentFunctionName(true);
		boolean accountMenuValidation = true; 
		
		if (getBrowserName().toLowerCase().equals(browsers.iPhone) || getBrowserName().toLowerCase().equals(browsers.Nexus)) {
			accountMenuValidation = validateMobile();
		}else {
			accountMenuValidation = validateDesktopAndTablet();
		}		
		sassert().assertTrue(accountMenuValidation, "My Account menu validation has some problems");

		getCurrentFunctionName(false);

		
	}
	
	public static boolean validateDesktopAndTablet() throws Exception{
		getCurrentFunctionName(true);
		boolean accountMenuValidation = true; 
		accountMenuValidation = accountMenuValidation && HomePage.validateAccountMenuDisplayed();
		sassert().assertTrue(accountMenuValidation, "Menu is not displayed");
		HomePage.clickOnAccountMenu(HomePageSelectors.accountMenu.get(),true);
		HomePage.validateAccountMenuItemsDisplayed(HomePageSelectors.accountMenuItems.get());
		HomePage.clickOnRandomAccountMenuItem(HomePageSelectors.accountMenuItems.get());
		getCurrentFunctionName(false);
		return accountMenuValidation;
	}
	
	
	public static boolean validateMobile() throws Exception{
		getCurrentFunctionName(true);
		boolean accountMenuValidation = true; 
		accountMenuValidation = accountMenuValidation && HomePage.validateAccountMenuDisplayed();
		sassert().assertTrue(accountMenuValidation, "Menu is not displayed");
		HomePage.clickOnAccountMenu(HomePageSelectors.accountMenu.get(),false);
		HomePage.validateAccountMenuItemsDisplayed(HomePageSelectors.accountMenuItems.get());
		HomePage.clickOnCloseButton(HomePageSelectors.navIcon.get());
		getCurrentFunctionName(false);
		return accountMenuValidation;
	}

	
}
