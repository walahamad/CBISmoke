package com.generic.tests.FG.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class LogoValidation extends SelTestCase {

	public static void validate() throws Exception {
		getCurrentFunctionName(true);
		sassert().assertTrue(HomePage.validateLogodisplayed(), "Logo is not displayed");
		HomePage.NavigateAwayFromHomePage();
		HomePage.clickOnLogo();
		sassert().assertTrue(HomePage.validateHomePageLink(), "Logo doesnt redirect to home page");
		getCurrentFunctionName(false);

	}

}
