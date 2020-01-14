package com.generic.tests.GH.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.Common;
import com.generic.setup.SelTestCase;

public class HomePageValidation extends SelTestCase {
	public static final String searchHint = "Search";
	public static void validateSearch() throws Exception {
		if(isMobile())
			Common.refreshBrowser();
		sassert().assertTrue(HomePage.validateSearchFieldPlaceHolderText(searchHint),
				"Search field place holder validation has some problems");
	}

	public static void validateCaroselAndEspot() throws Exception {
		if(isMobile())
			Common.refreshBrowser();
		sassert().assertTrue(HomePage.isDisplayAllSpots(), "Home page Espots display validation has some problems");
		sassert().assertTrue(HomePage.isLoadedAllEspots(), "Home page Espots loaded validation has some problems");

	}

}