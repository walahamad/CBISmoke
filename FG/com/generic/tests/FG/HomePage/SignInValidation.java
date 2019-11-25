package com.generic.tests.FG.HomePage;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebElement;

import com.generic.page.HomePage;
import com.generic.page.Registration;
import com.generic.selector.HomePageSelectors;
import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;
import com.generic.setup.GlobalVariables;


public class SignInValidation extends SelTestCase {

	private static LinkedHashMap<String, Object> users;
	public static String userEmail;
	public static String userPassword;

	@SuppressWarnings("unchecked")
	public static boolean validate() throws Exception {
		getCurrentFunctionName(true);
		users = Common.readUsers();
		boolean validation = false;
		WebElement signInLink;

		// Get a useremail and password from excel sheet file.
		Object key = users.keySet().iterator().next();
		LinkedHashMap<String, Object> userDetails = (LinkedHashMap<String, Object>) users.get(key);
		userEmail = getSubMailAccount((String) userDetails.get(Registration.keys.email));
		userPassword = (String) userDetails.get(Registration.keys.password);

		logs.debug("The user Email: " + userEmail);
		logs.debug("The user Password: " + userPassword);

		// Check if the device is mobile(PWA site) or (desktop, tablet).
		boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);

		if (isPWAMobile) {
			// Validate the sign in for Mobile (PWA site).
			logs.debug("Validate Sign in for Mobile (PWA site)");

			// Get the sign in link or welcome message from the account menu.
			signInLink = HomePage.getSignInLinkMobilePWA();
		} else {
			// Validate the desktop and tablet sign in form.
			logs.debug("Validate Sign in desktop or tablet");

			// Get the sign in link or welcome.
			signInLink = SelectorUtil.getelement(HomePageSelectors.signInNavigation.get());
		}

		// Navigate to login/create account page and validate the sign in form.
		validation = HomePage.validateSignInForm(signInLink, isPWAMobile);

		getCurrentFunctionName(false);
		return validation;
	}

}