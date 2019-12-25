package com.generic.tests.GR.HomePage;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.generic.page.HomePage;
import com.generic.setup.SelTestCase;

public class MenuValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);

		boolean validation = false;
		// Check if the device is mobile/tablet or desktop .
		boolean isMobile = isMobile() || isiPad();
		if (!isMobile) {
			// Validate the desktop menu.
			validation = validateDesktop();
		} else {
			// Validate the mobile menu.
			validation = validateMobile();
		}
		getCurrentFunctionName(false);
		return validation;
	}

	public static boolean validateDesktop() throws Exception {
		getCurrentFunctionName(true);

		// Get the menu items list in first level.
		List<WebElement> menuFirstLevelElements = HomePage.getFirstLevelMenuItems();

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

	public static boolean validateMobile() throws Exception {
		getCurrentFunctionName(true);
		boolean validateNavigateMenu = false;
		// Validate the mobile modal menu.
		validateNavigateMenu = HomePage.validateModalMenuSecondLevel();
		getCurrentFunctionName(false);
		return validateNavigateMenu;
	}
}