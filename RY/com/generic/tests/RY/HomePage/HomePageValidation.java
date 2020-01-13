package com.generic.tests.RY.HomePage;

import org.testng.asserts.SoftAssert;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;
import com.generic.setup.Common;
import com.generic.setup.GlobalVariables.browsers;

public class HomePageValidation extends SelTestCase {
	public static final String searchHint = "Search Ryllace";

	public static void validateSearch() throws Exception {
		if (isMobile()) {
			Common.refreshBrowser();
			HomePage.searchIconClick();
			sassert().assertTrue(HomePage.validateSearchIconFieldOpend(),
					"Search icon field opened validation has some problems");
			sassert().assertTrue(HomePage.validateSearchFieldPlaceHolderText(searchHint),
					"Search field place holder validation has some problems");
			HomePage.searchIconExitClick();
			sassert().assertTrue(HomePage.validateSearchIconFieldClosed(),
					"Search icon field colsed validation has some problems");
		}else {
		sassert().assertTrue(HomePage.validateSearchFieldPlaceHolderText(searchHint),
				"Search field place holder validation has some problems");
	}}

	public static void validateCaroselAndEspot() throws Exception {
		if (isMobile()) 
			Common.refreshBrowser();
		sassert().assertTrue(HomePage.isDisplayAllSpots(), "Home page Espots display validation has some problems");
		sassert().assertTrue(HomePage.isLoadedAllEspots(), "Home page Espots loaded validation has some problems");

	}

}