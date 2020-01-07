package com.generic.tests.GH.HomePage;

import com.generic.setup.SelTestCase;
import com.generic.page.HomePage;

public class GlobalFooterValidation extends SelTestCase {
	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		boolean isCountrySelectorDisplayed = HomePage.validateCountrySelectorDisplayed();
		boolean isFooterItemsDisplayed = HomePage.validateGlobalFooterItemsDisplayed();
		getCurrentFunctionName(false);
		return isCountrySelectorDisplayed && isFooterItemsDisplayed;
	}
}
