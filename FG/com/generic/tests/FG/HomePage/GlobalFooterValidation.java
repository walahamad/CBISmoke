package com.generic.tests.FG.HomePage;

import com.generic.setup.SelTestCase;
import com.generic.page.HomePage;

public class GlobalFooterValidation extends SelTestCase {
	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		boolean isCountrySelectorDisplayed = HomePage.validateCountrySelectorDisplayed();
		boolean isFooterItemsDisplayed = HomePage.validateGlobalFooterItemsDisplayed();

		sassert().assertTrue(isCountrySelectorDisplayed && isFooterItemsDisplayed,
				"Global footer validation has some problems");
		getCurrentFunctionName(false);
		return isCountrySelectorDisplayed && isFooterItemsDisplayed;
	}
}
