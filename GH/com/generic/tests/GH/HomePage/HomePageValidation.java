package com.generic.tests.GH.HomePage;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class HomePageValidation extends SelTestCase {

	public static void validateSearch() throws Exception {
		HomePage.searchIconClick();
		sassert().assertTrue(HomePage.validateSearchIconFieldOpend(),
				"Search icon field opened validation has some problems");
		sassert().assertTrue(HomePage.validateSearchFieldPlaceHolderText(),
				"Search field place holder validation has some problems");
		HomePage.searchIconExitClick();
		sassert().assertTrue(HomePage.validateSearchIconFieldClosed(),
				"Search icon field colsed validation has some problems");
	}

	public static void validateCaroselAndEspot() throws Exception {
		sassert().assertTrue(HomePage.isDisplayedModuleHeroImg(),
				"Search module mero Image displayed validation has some problems");
		sassert().assertTrue(HomePage.isLoadedModuleHeroImg(),
				"Search module mero Image loaded validation has some problems");

		sassert().assertTrue(HomePage.isDisplayAllSpots(), "Home page Espots display validation has some problems");
		sassert().assertTrue(HomePage.isLoadedAllEspots(), "Home page Espots loaded validation has some problems");

		sassert().assertTrue(HomePage.isDisplayedAllCarouselContent(),
				"Home page carousal display validation has some problems");
		sassert().assertTrue(HomePage.isLoadedAllCarouselContent(),
				"Home page carousal loaded validation has some problems");

	}

}