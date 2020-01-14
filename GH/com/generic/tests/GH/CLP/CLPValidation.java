package com.generic.tests.GH.CLP;
import com.generic.page.CLP;
import com.generic.setup.Common;
import com.generic.setup.GlobalVariables;

import com.generic.setup.SelTestCase;

public class CLPValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		boolean isValidCLP = false;
		if (isMobile())
			Common.refreshBrowser();
		
        if (isMobile() || isiPad()){
	       isValidCLP = CLP.validateMobileIpadCLP();
        }else {
 	       isValidCLP = CLP.validateDesktopCLP();
        } 
		logs.debug("validate CLP :: " + isValidCLP);
		return isValidCLP;
	}

}
