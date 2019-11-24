package com.generic.tests.FG.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class YAMALCarouselsVerification extends SelTestCase {

	public static void validate() throws Exception {
		getCurrentFunctionName(true);
		sassert().assertTrue(HomePage.validateYamalCarouselsDisplayed(), "Yamal Carousels is not displayed");
		getCurrentFunctionName(false);
		
	}

}
