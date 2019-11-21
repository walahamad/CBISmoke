package com.generic.tests.FG.HomePage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.generic.page.HomePage;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.SelTestCase;

public class SignInValidation extends SelTestCase {

	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		boolean validation = false;
		WebElement signInLink;

		// Check if the device is mobile(PWA site) or (desktop, tablet).
		boolean isPWAMobile = getBrowserName().contains("mobile") && !getBrowserName().contains("iPad");

		if (isPWAMobile) {
			// Validate the sign in for Mobile (PWA site).
			logs.debug("Validate Sign in for Mobile (PWA site)");

			// Get the sign in link or welcome message from the account menu.
			signInLink = HomePage.getSignInLinkMobilePWA();
		} else {
			// Validate the desktop and tablet sign in form.
			logs.debug("Validate Sign in desktop or tablet");

			// Get the sign in link or welcome.
			signInLink = HomePage.getElementsList(HomePageSelectors.signInNavigation.get()).get(0);
		}

		// Navigate to login/create account page and validate the sign in form.
		validation = HomePage.validateSignInForm(signInLink, isPWAMobile);

		getCurrentFunctionName(false);
		return validation;
	}

}
