package com.generic.tests.FG.HomePage;
import com.generic.page.HomePage;
import com.generic.setup.GlobalVariables;

import com.generic.setup.SelTestCase;

public class CLPValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		boolean isValidCLP = false;
		boolean isMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone) || getBrowserName().contains(GlobalVariables.browsers.iPad) || getBrowserName().contains(GlobalVariables.browsers.Nexus);
        if (isMobile){
	       isValidCLP = HomePage.validateMobileIpadCLP();
        }else {
 	       isValidCLP = HomePage.validateDesktopCLP();
        } 
		logs.debug("validate CLP :: " + isValidCLP);
		return isValidCLP;
	}

}
