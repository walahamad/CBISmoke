package com.generic.tests.FG.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class LogoValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		boolean logoValidation = true; 
		logoValidation = logoValidation && HomePage.validateLogoExistance(); 
//		logoValidation = logoValidation && HomePage.validateLogodisplayed();
//		logoValidation = logoValidation && HomePage.NavigateAwayFromHomePage(); 
//		logoValidation = logoValidation && HomePage.clickOnLogo(); 
//		logoValidation = logoValidation && HomePage.validateHomePageLink();
		getCurrentFunctionName(false);
		return logoValidation;
		
	}

}
