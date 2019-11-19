package com.generic.tests.FG.HomePage;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.generic.page.HomePage;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.SelTestCase;

public class MenuValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);

		boolean validation = false;

		// Validate the desktop menu.
		if (!getBrowserName().toLowerCase().contains("mobile")) {
			validation = validateDesktop();
		} else {
			validation = validateTablet();
		}
		getCurrentFunctionName(false);
		return validation;
	}

	public static boolean validateDesktop() throws Exception {
		getCurrentFunctionName(true);

		// Get the menu items list in first level.
		List<WebElement> menuFirstLevelElements = HomePage.getFirstLevelMenuItems(HomePageSelectors.menuItems);

		// Check if there's an item in the menu.
		boolean menuValidationExist = HomePage.menuValidation(menuFirstLevelElements);
		boolean validateNavigateMenu = false;

		if (menuValidationExist) {
			// Log the number of items at first level.
			HomePage.logNumberOfFirstLevelMenuItems(menuFirstLevelElements);
			// Validate the sub menu items navigation and target page.
			validateNavigateMenu = HomePage.validateSubMenuLists(menuFirstLevelElements);
		}

		getCurrentFunctionName(false);
		return (menuValidationExist && validateNavigateMenu);
	}
	
	public static boolean validateTablet() throws Exception {
		getCurrentFunctionName(true);
		boolean validateNavigateMenu = false;
		validateNavigateMenu = HomePage.validateModalMenuSecondLevel();
		getCurrentFunctionName(false);
		return true;
	}
}

