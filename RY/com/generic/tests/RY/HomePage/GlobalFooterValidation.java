package com.generic.tests.RY.HomePage;

import com.generic.setup.SelTestCase;
import com.generic.page.HomePage;
import com.generic.setup.GlobalVariables.browsers;

public class GlobalFooterValidation extends SelTestCase {
	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		boolean isFooterItemsDisplayed = HomePage.validateGlobalFooterItemsDisplayed();
		getCurrentFunctionName(false);
		return  isFooterItemsDisplayed;
	}
}
