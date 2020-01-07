package com.generic.tests.GR.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class MainHomeCarouselsVerification extends SelTestCase {

	public static void validate() throws Exception {
		getCurrentFunctionName(true);
		sassert().assertTrue(HomePage.validateMainHomeCarouselsDisplayed(), "Main home Carousels is not displayed");
		getCurrentFunctionName(false);

	}

}
