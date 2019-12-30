package com.generic.page;

import java.net.URI;
import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;

import com.generic.selector.LoginSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;
import com.generic.setup.GlobalVariables;

public class Login extends SelTestCase {

	public static String logoffhref = "/Logoff";
	public static String myAccountPageLink = "AccountOverView";

	// done-ocm
	public static void logIn(String userName, String Password) throws Exception {
		try {
			getCurrentFunctionName(true);
			fillLoginFormAndClickSubmit(userName, Password);
			Thread.sleep(1000);
			if (!checkUserAccount()) {
				throw new Exception("Login failed");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Fill the login form and click submit button.
	 *
	 * @param email
	 * @param Password
	 * @throws Exception
	 */
	public static void fillLoginFormAndClickSubmit(String email, String Password) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.GETTING_TEXT,
					"Navigating to registration page..." + getCONFIG().getProperty("RegistrationPage")));
			getDriver().get(new URI(getDriver().getCurrentUrl()).resolve(getCONFIG().getProperty("RegistrationPage"))
					.toString());
			typeEmail(email);
			typePassword(Password);
			clickLogin();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Click Sign in button.
	 *
	 * @throws Exception
	 */
	public static void clickLogin() throws Exception {
		try {
			getCurrentFunctionName(true);
			// Select the sign in button and Navigate to the Sign in/Create account page..
			SelectorUtil.initializeSelectorsAndDoActions(LoginSelectors.signInButton.get());
			SelectorUtil.waitingLoadingButton(LoginSelectors.loadingButton);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	/**
	 * Type user password.
	 *
	 * @param Password
	 * @throws Exception
	 */
	public static void typePassword(String password) throws Exception {
		try {
			getCurrentFunctionName(true);
			// Select the password input and Enter the password.
			SelectorUtil.initializeSelectorsAndDoActions(LoginSelectors.signInEmailPasswordInput.get(), password);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Enter user password.
	 *
	 * @param email
	 * @throws Exception
	 */
	public static void typeEmail(String email) throws Exception {
		try {
			getCurrentFunctionName(true);
			// Select the email input and Enter the email.
			SelectorUtil.initializeSelectorsAndDoActions(LoginSelectors.signInEmailInput.get(), email);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Get Email error message.
	 *
	 * @throws Exception
	 */
	public static String getMailErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		try {
			String errorMessage = SelectorUtil.getElement(LoginSelectors.emailError.get()).getText();
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, errorMessage));
			getCurrentFunctionName(false);
			return errorMessage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Get login error message.
	 *
	 * @throws Exception
	 */
	public static String getErrologinMessage() throws Exception {
		getCurrentFunctionName(true);
		try {
			Thread.sleep(1000);
			String errorMessage = SelectorUtil.getElement(LoginSelectors.errorMessage.get()).getText();
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, errorMessage));
			getCurrentFunctionName(false);
			return errorMessage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Get password error message.
	 *
	 * @throws Exception
	 */
	public static String getPasswrdErrorMsg() throws Exception {
		getCurrentFunctionName(true);
		try {
			String errorMessage = SelectorUtil.getElement(LoginSelectors.passwordError.get()).getText();
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, errorMessage));
			getCurrentFunctionName(false);
			return errorMessage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Check user account is logged in.
	 *
	 * @throws Exception
	 */
	public static boolean checkUserAccount() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isUserLogedIn = false;

			// Check if the device is mobile(PWA site) or (desktop, tablet).
			boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);

			// Validate the welcome message if it is exist.
			if (isPWAMobile) {
				Thread.sleep(1000);
				SelectorUtil.waitGWTLoadedEventPWA();
				WebElement welcomeMessageElement = SelectorUtil.getMenuLinkMobilePWA(logoffhref);
				String itemHref = welcomeMessageElement.getAttribute("href");
				if (itemHref.contains(logoffhref)) {
					isUserLogedIn = true;
				}
			} else {
				WebElement welcomeMessage = SelectorUtil.getElement(LoginSelectors.welcomeMessage.get());
				logs.debug("welcomeMessage: " + welcomeMessage.getAttribute("innerText").trim());
				if (welcomeMessage.getAttribute("innerText").trim().toLowerCase().contains("welcome")) {
					isUserLogedIn = true;
				}
			}

			getCurrentFunctionName(false);

			return isUserLogedIn;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Check user account link.
	 * 
	 * @param boolean
	 * @throws Exception
	 */
	public static boolean checkExistenceOfAccountLink() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isUserLogedIn = false;

			// Check if the device is mobile(PWA site) or (desktop, tablet).
			boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);
			WebElement myAccountLink;

			// Get my account link.
			if (isPWAMobile) {
				myAccountLink = SelectorUtil.getMenuLinkMobilePWA(myAccountPageLink);
			} else {
				myAccountLink = SelectorUtil.getElement(LoginSelectors.myAccountLink);
			}

			// Check if link is for my account page.
			String itemHref = myAccountLink.getAttribute("href");
			if (itemHref.contains(myAccountPageLink)) {
				isUserLogedIn = true;
			}

			// Go to my account page.
			SelectorUtil.openMobileAccountMenu();
			SelectorUtil.clickOnWebElement(myAccountLink);

			getCurrentFunctionName(false);

			return isUserLogedIn;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	/**
	 * Check current page if it is my account.
	 *
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean checkMyAccountPage() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isMyAccountPage = false;

			String currentPageUrl = SelectorUtil.getCurrentPageUrl();

			// Validate the current page is my account page by URL.
			if (currentPageUrl.contains(myAccountPageLink)) {
				isMyAccountPage = true;
			}

			getCurrentFunctionName(false);

			return isMyAccountPage;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void registerNewUser(String userMail, String userPassword) throws Exception {
		try {
			getCurrentFunctionName(true);
			registerNewUser(userMail, userPassword, true);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static void registerNewUser(String userMail, String userPassword, boolean logOut) throws Exception {
		try {
			getCurrentFunctionName(true);

			// Run the registration test case before sign in.
			Registration.registerFreshUser(userMail, userPassword);

			if (logOut) {
				boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);
				if (isPWAMobile) {
					WebElement logoffLink = SelectorUtil.getMenuLinkMobilePWA(logoffhref);
					SelectorUtil.clickOnWebElement(logoffLink);
					Thread.sleep(1500);
				} else {
					SelectorUtil.initializeSelectorsAndDoActions(LoginSelectors.logoffLink);
				}
			}

			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
}
