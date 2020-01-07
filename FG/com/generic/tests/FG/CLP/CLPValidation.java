package com.generic.tests.FG.CLP;

import com.generic.page.CLP;

import com.generic.setup.SelTestCase;

public class CLPValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		boolean isValidCLP = false;
		boolean isMobile = isMobile() || isiPad();
		if (isMobile) {
			isValidCLP = CLP.validateMobileIpadCLP();
		} else {
			isValidCLP = CLP.validateDesktopCLP();
		}
		logs.debug("validate CLP :: " + isValidCLP);
		return isValidCLP;
	}

}
