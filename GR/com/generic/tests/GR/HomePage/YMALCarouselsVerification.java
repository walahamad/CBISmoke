package com.generic.tests.GR.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class YMALCarouselsVerification extends SelTestCase {

	public static void validate() throws Exception {
		getCurrentFunctionName(true);
		sassert().assertTrue(HomePage.validateYMALCarouselsDisplayed(), "YMAL Carousels is not displayed");
		getCurrentFunctionName(false);

	}

}
